/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.Carrinho;
import Controller.ItemCarrinho;
import Controller.Veiculo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author bryan
 */
public class ManterCarrinho extends DAO{
    public void adicionarAoCarrinho(Carrinho carrinho) {
        try {
            abrirBanco();
            // Verifica se o produto já está no carrinho
            String selectSQL = "SELECT quantidade FROM carrinho WHERE idUsuario = ? AND idVeiculo = ?";
            pst = con.prepareStatement(selectSQL);
            pst.setInt(1, carrinho.getIdUsuario());
            pst.setInt(2, carrinho.getIdVeiculo());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Atualiza quantidade se já existir
                int novaQuantidade = rs.getInt("quantidade") + carrinho.getQuantidade();
                String updateSQL = "UPDATE carrinho SET quantidade = ? WHERE idUsuario = ? AND idVeiculo = ?";
                pst = con.prepareStatement(updateSQL);
                pst.setInt(1, novaQuantidade);
                pst.setInt(2, carrinho.getIdUsuario());
                pst.setInt(3, carrinho.getIdVeiculo());
                pst.executeUpdate();
            } else {
                // Insere novo item
                String insertSQL = "INSERT INTO carrinho (idUsuario, idVeiculo, quantidade) VALUES (?, ?, ?)";
                pst = con.prepareStatement(insertSQL);
                pst.setInt(1, carrinho.getIdUsuario());
                pst.setInt(2, carrinho.getIdVeiculo());
                pst.setInt(3, carrinho.getQuantidade());
                pst.executeUpdate();
            }

            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar ao carrinho: " + e.getMessage());
        }
    }
    
    public List<ItemCarrinho> listarCarrinho(Carrinho carrinho) {
        List<ItemCarrinho> itens = new ArrayList<>();

        try {
            abrirBanco();
            String sql = "SELECT c.idVeiculo, c.quantidade, v.modelo, v.preco, v.imagem "
                    + "FROM carrinho c INNER JOIN veiculo v ON c.idVeiculo = v.idVeiculo WHERE c.idUsuario = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, carrinho.getIdUsuario());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Veiculo vei = new Veiculo();
                vei.setIdVeiculo(rs.getInt("idVeiculo"));
                vei.setModelo(rs.getString("modelo"));
                vei.setPreco(rs.getDouble("preco"));
                vei.setImagem(rs.getString("imagem"));

                int quantidade = rs.getInt("quantidade");
                ItemCarrinho item = new ItemCarrinho(vei, quantidade);
                itens.add(item);
            }

            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao listar carrinho: " + e.getMessage());
        }

        return itens;
    }
    
    public void limparCarrinho(int idUsuario) {
        try {
            abrirBanco();
            String sql = "DELETE FROM carrinho WHERE idUsuario = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao limpar carrinho: " + e.getMessage());
        }
    }
    
    public void diminuirProdutoCarrinho(Carrinho carrinho){
        try {
            abrirBanco();
            String selectSQL = "SELECT quantidade FROM carrinho WHERE idUsuario = ? AND idVeiculo = ?";
            pst = con.prepareStatement(selectSQL);
            pst.setInt(1, carrinho.getIdUsuario());
            pst.setInt(2, carrinho.getIdVeiculo());
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                int novaQuantidade = rs.getInt("quantidade") - carrinho.getQuantidade();
                String updateSQL = "UPDATE carrinho SET quantidade = ? WHERE idUsuario = ? AND idVeiculo = ?";
                pst = con.prepareStatement(updateSQL);
                pst.setInt(1, novaQuantidade);
                pst.setInt(2, carrinho.getIdUsuario());
                pst.setInt(3, carrinho.getIdVeiculo());
                pst.executeUpdate();
            }
            
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao diminuir do carrinho: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void excluirProdutoCarrinho(Carrinho carrinho) {
        try {
            abrirBanco();
            String deleteSQL = "DELETE FROM carrinho WHERE idUsuario = ? AND idVeiculo = ?";
            pst = con.prepareStatement(deleteSQL);
            pst.setInt(1, carrinho.getIdUsuario());
            pst.setInt(2, carrinho.getIdVeiculo());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao remover produto do carrinho: " + e.getMessage());
        }
    }
}
