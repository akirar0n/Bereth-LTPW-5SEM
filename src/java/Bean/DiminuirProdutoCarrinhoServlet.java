/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bean;

import Controller.Carrinho;
import Model.ManterCarrinho;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author bryan
 */
@WebServlet(name = "DiminuirProdutoCarrinhoServlet", urlPatterns = {"/DiminuirProdutoCarrinho"})
public class DiminuirProdutoCarrinhoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int idVeiculo = Integer.parseInt(request.getParameter("idVeiculo"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            // 2. Recupera id do cliente da sessão
            HttpSession session = request.getSession(false);
            Integer idUsuario = (Integer) session.getAttribute("idUsuario");

            if (idUsuario == null) {
                response.sendRedirect("UsuarioLoginView.jsp"); // Redireciona se o usuário não estiver logado
                return;
            }

            Carrinho carrinho = new Carrinho();
            carrinho.setIdVeiculo(idVeiculo);
            carrinho.setIdUsuario(idUsuario);
            carrinho.setQuantidade(quantidade);

            // 3. Adiciona ao carrinho usando o DAO
            ManterCarrinho dao = new ManterCarrinho();
            dao.diminuirProdutoCarrinho(carrinho);

            // 4. Redireciona para a página do carrinho (ou página de produtos)
            response.sendRedirect("CarrinhoView.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao adicionar ao carrinho: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

    }
    


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
