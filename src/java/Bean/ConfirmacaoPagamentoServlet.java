package Bean;

import Controller.Carrinho;
import Controller.ItemCarrinho;
import Model.ManterCarrinho;
import Model.ManterPedido;
import Model.ManterPedidoVeiculo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ConfirmacaoPagamentoServlet", urlPatterns = {"/confirmacaoPagamento"})
public class ConfirmacaoPagamentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1️⃣ Verifica se o pagamento foi aprovado via parâmetro
        String status = request.getParameter("collection_status");  // "approved" ou outro
        if (!"approved".equalsIgnoreCase(status)) {
            response.sendRedirect("CarrinhoView.jsp");
            return;
        }

        // 2️⃣ Recupera ID do cliente
        HttpSession session = request.getSession(false);
        Integer idUsuario = (session != null) ? (Integer) session.getAttribute("idUsuario") : null;
        if (idUsuario == null) {
            response.sendRedirect("UsuarioLoginView.jsp");
            return;
        }

        // 3️⃣ Busca itens do carrinho
        ManterCarrinho carrinhoDAO = new ManterCarrinho();
        Carrinho carrinhoBean = new Carrinho();
        carrinhoBean.setIdUsuario(idUsuario);
        List<ItemCarrinho> itens = carrinhoDAO.listarCarrinho(carrinhoBean);

        if (itens.isEmpty()) {
            response.sendRedirect("CarrinhoView.jsp");
            return;
        }

        // 4️⃣ Cria Pedido e obtém ID
        ManterPedido pedidoDAO = new ManterPedido();
        int idPedido;
        try {
                double total = 0.0;
            for (ItemCarrinho item : itens){
                double subtotal = item.getSubtotal();
                total += subtotal;
            }
            idPedido = pedidoDAO.criarPedido(idUsuario, total);

            // 5️⃣ Insere itens em pedido_produto
            ManterPedidoVeiculo pvDAO = new ManterPedidoVeiculo();
            for (ItemCarrinho item : itens) {
                pvDAO.adicionarItem(idPedido,
                        item.getVeiculo().getIdVeiculo(),
                        item.getQuantidade(),
                        item.getSubtotal());
            }
            
            
            // 6️⃣ Limpa carrinho
            carrinhoDAO.limparCarrinho(idUsuario);

            // 7️⃣ Redireciona para página de pedidos
            response.sendRedirect("PedidosView.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        } catch (Exception ex) {
            Logger.getLogger(ConfirmacaoPagamentoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erro.jsp");
        }

    }
}
