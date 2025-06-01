/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.Veiculo;
import Enuns.CategoriaVeiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiagosilva
 */
public class ManterVeiculo extends DAO {

    public boolean inserirVeiculo(Veiculo veiculo) throws Exception {
        try {
            abrirBanco();
            String sql = "INSERT INTO veiculo (categoriaVeiculo, marca, modelo, cor, rodas, motorizacao, pesoKg, capacidadeTanque, assentos, anoFabricacao, anoModelo, placa, chassi) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, veiculo.getCategoriaVeiculo().name());
            pst.setString(2, veiculo.getMarca());
            pst.setString(3, veiculo.getModelo());
            pst.setString(4, veiculo.getCor());
            pst.setInt(5, veiculo.getRodas());
            pst.setFloat(6, veiculo.getMotorizacao());
            pst.setFloat(7, veiculo.getPesoKg());
            pst.setFloat(8, veiculo.getCapacidadeTanque());
            pst.setInt(9, veiculo.getAssentos());
            pst.setInt(10, veiculo.getAnoFabricacao());
            pst.setInt(11, veiculo.getAnoModelo());
            pst.setString(12, veiculo.getPlaca());
            pst.setString(13, veiculo.getChassi());

            pst.executeUpdate();
            fecharBanco();

            int linhasAfetadas = pst.executeUpdate();

            return linhasAfetadas > 0;
        } catch (Exception e) {
            System.out.println("Erro SQL ao inserir veiculo: " + e.getMessage());
            e.printStackTrace();
            return false;

        } finally {
            try {
                fecharBanco();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos (inserirVeiculo): " + e.getMessage());
            }
        }
    }

    public List<Veiculo> listarVeiculos() throws Exception {
        List<Veiculo> lista = new ArrayList<>();
        try {
            abrirBanco();
            String sql = "SELECT * FROM veiculo";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setIdVeiculo(rs.getInt("idVeiculo"));
                v.setCategoriaVeiculo(CategoriaVeiculo.valueOf(rs.getString("categoriaVeiculo")));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setCor(rs.getString("cor"));
                v.setRodas(rs.getInt("rodas"));
                v.setMotorizacao(rs.getFloat("motorizacao"));
                v.setPesoKg(rs.getFloat("pesoKg"));
                v.setCapacidadeTanque(rs.getFloat("capacidadeTanque"));
                v.setAssentos(rs.getInt("assentos"));
                v.setAnoFabricacao(rs.getInt("anoFabricacao"));
                v.setAnoModelo(rs.getInt("anoModelo"));
                v.setPlaca(rs.getString("placa"));
                v.setChassi(rs.getString("chassi"));
                lista.add(v);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return lista;
    }

}
