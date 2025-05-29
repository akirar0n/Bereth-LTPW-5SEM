package Model;

import Controller.Usuario;
import Enuns.Acesso;
import java.sql.Date;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author thiagosilva
 */
public class ManterUsuario extends DAO {

    public boolean inserirLogin(Usuario u) {
        try {
            abrirBanco();
            String senhaCriptografada = BCrypt.hashpw(u.getSenha(), BCrypt.gensalt());
            String query = "INSERT INTO usuario (idUsuario, cpf, email, senha, rg, nome, idade, nascimento, acesso) values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, u.getCpf());
            pst.setString(2, u.getEmail());
            pst.setString(3, senhaCriptografada);
            pst.setString(4, u.getRg());
            pst.setString(5, u.getNome());
            pst.setInt(6, u.getIdade());
            pst.setDate(7, (Date) u.getNascimento()); // **Alterado para setDate**
            pst.setString(8, u.getAcesso().name());

            int linhasAfetadas = pst.executeUpdate();

            return linhasAfetadas > 0;
        } catch (Exception e) {
            System.out.println("Erro SQL ao inserir usuário: " + e.getMessage());
            e.printStackTrace();
            return false;

        } finally {
            try {
                fecharBanco();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos (inserirLogin): " + e.getMessage());
            }
        }
    }

    public Usuario validarLogin(String email, String senhaDigitada) {
        Usuario usuarioAutenticado = new Usuario();

        try {
            abrirBanco();
            String query = "SELECT idUsuario, cpf, email, senha, rg, nome, idade, nascimento, acesso FROM usuario WHERE email = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String senhaHashBanco = rs.getString("senha");

                if (BCrypt.checkpw(senhaDigitada, senhaHashBanco)) {
                    usuarioAutenticado = new Usuario();
                    usuarioAutenticado.setIdUsuario(rs.getInt("idUsuario"));
                    usuarioAutenticado.setCpf(rs.getString("cpf"));
                    usuarioAutenticado.setEmail(rs.getString("email"));
                    usuarioAutenticado.setRg(rs.getString("rg"));
                    usuarioAutenticado.setNome(rs.getString("nome"));
                    usuarioAutenticado.setIdade(rs.getInt("idade"));
                    usuarioAutenticado.setNascimento(rs.getDate("nascimento")); // **Alterado para getDate**
                    usuarioAutenticado.setAcesso(Acesso.fromString(rs.getString("acesso")));

                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                fecharBanco();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos (validarLogin): " + e.getMessage());
            }
        }
        return usuarioAutenticado;
    }

    public boolean alterarUsuario(Usuario u) {
        try {
            abrirBanco();
            String query = "UPDATE usuario set cpf = ?, email = ?, rg = ?, nome = ?, idade = ? , nascimento = ?, acesso = ? where idUsuario = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, u.getCpf());
            pst.setString(2, u.getEmail());
            //pst.setString(3, senhaCriptografada); recuperação sera em pagina diferente 
            pst.setString(3, u.getRg());
            pst.setString(4, u.getNome());
            pst.setInt(5, u.getIdade());
            pst.setDate(6, (Date) u.getNascimento()); // **Alterado para setDate**
            pst.setString(7, u.getAcesso().name());
            pst.setInt(8, u.getIdUsuario());

            int linhasAfetadas = pst.executeUpdate();

            fecharBanco();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            System.out.println("Erro ao fechar recursos (alterarUsuario): " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarIdUsuario(int idUsuario) {

        try {
            Usuario usuario = new Usuario();
            abrirBanco();
            String query = "SELECT idUsuario, nome, email, cpf, rg, idade, nascimento, acesso FROM usuario WHERE idUsuario = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setRg(rs.getString("rg"));
                usuario.setIdade(rs.getInt("idade"));
                usuario.setNascimento(rs.getDate("nascimento"));

                String acessoStrDoBanco = rs.getString("acesso");
                try {
                    usuario.setAcesso(Acesso.fromString(acessoStrDoBanco));
                } catch (IllegalArgumentException e) {
                    System.err.println("Aviso: Valor de acesso inválido no banco de dados para o usuário ID " + idUsuario + ": '" + acessoStrDoBanco + "'. Definindo acesso como Cliente.");
                    usuario.setAcesso(Acesso.Cliente);
                }
                return usuario;
            }
            fecharBanco();
        } catch (Exception e) {
            System.err.println("Erro SQL ao buscar usuário por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Execução da Query de busca por ID fechada");
        }

        return null;
    }
}
