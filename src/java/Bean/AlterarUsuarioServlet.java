/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bean;

import Controller.Usuario;
import Enuns.Acesso;
import Model.ManterUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author thiagosilva
 */
public class AlterarUsuarioServlet extends HttpServlet {

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

            HttpSession session = request.getSession();
            Integer idUsuario = (Integer) session.getAttribute("idUsuario");

            if (idUsuario == null) {
                response.sendRedirect("UsuarioLoginView.jsp?mensagem=Faça%20login%20novamente");
                return;
            }

            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String rg = request.getParameter("rg");
            String nome = request.getParameter("nome");
            String idadeStr = request.getParameter("idade");
            String nascimentoStr = request.getParameter("nascimento");
            String acessoStr = request.getParameter("acesso");

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setRg(rg);
            usuario.setNome(nome);

            int idade = 0;
            if (idadeStr != null && !idadeStr.isEmpty()) {
                try {
                    idade = Integer.parseInt(idadeStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensagemErro", "Idade inválida.");
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("UsuarioAlterarView.jsp").forward(request, response);
                    return;
                }
            }
            usuario.setIdade(idade);

            Date nascimentoSqlDate = null;
            if (nascimentoStr != null && !nascimentoStr.isEmpty()) {
                try {
                    LocalDate localDate = LocalDate.parse(nascimentoStr);
                    nascimentoSqlDate = Date.valueOf(localDate);
                } catch (Exception e) {
                    request.setAttribute("mensagemErro", "Data de nascimento inválida.");
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("UsuarioAlterarView.jsp").forward(request, response);
                    return;
                }
            }
            usuario.setNascimento(nascimentoSqlDate);

            Acesso acesso;
            if (acessoStr == null || acessoStr.isEmpty()) {
                request.setAttribute("mensagemErro", "Tipo de Acesso deve ser selecionado.");
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("UsuarioAlterarView.jsp").forward(request, response);
                return;
            }

            try {
                acesso = Acesso.fromString(acessoStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("mensagemErro", "Tipo de acesso inválido selecionado: " + acessoStr);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("UsuarioAlterarView.jsp").forward(request, response);
                return;
            }

            usuario.setAcesso(acesso);

            ManterUsuario manterUsuario = new ManterUsuario();
            boolean sucesso = manterUsuario.alterarUsuario(usuario);

            if (sucesso) {
                response.sendRedirect("UsuarioAlterarView.jsp?mensagem=Usuario%20alterado%20com%20sucesso!");
            } else {
                request.setAttribute("mensagemErro", "Erro ao alterar usuário.");
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("UsuarioAlterarView.jsp").forward(request, response);
            }
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
