package Model;

import java.sql.*;

public class ManterPedidoVeiculo extends DAO {
    public void adicionarItem(int idPedido, int idVeiculo, int quantidade, double subtotal) throws SQLException, Exception {
        abrirBanco();
        String sql = "INSERT INTO pedido_veiculo (id_pedido, idVeiculo, quantidade, subtotal) VALUES (?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setInt(1, idPedido);
        pst.setInt(2, idVeiculo);
        pst.setInt(3, quantidade);
        pst.setDouble(4, subtotal);
        pst.executeUpdate();
        fecharBanco();
    }
}
