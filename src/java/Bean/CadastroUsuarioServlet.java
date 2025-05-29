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
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

/**
 *
 * @author thiagosilva
 */
public class CadastroUsuarioServlet extends HttpServlet {

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

            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String rg = request.getParameter("rg");
            String nome = request.getParameter("nome");
            String idadeStr = request.getParameter("idade");
            String nascimentoStr = request.getParameter("nascimento");

            Usuario usuario = new Usuario();

            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setRg(rg);
            usuario.setNome(nome);
            String acessoStr = request.getParameter("acesso");
            Acesso acesso = null;
            if (acessoStr == null || acessoStr.isEmpty()) {
                request.setAttribute("mensagemErro", "Tipo de Acesso deve ser selecionado.");
                request.getRequestDispatcher("UsuarioCadastroView.jsp").forward(request, response);
                return;
            }

            try {
                acesso = Acesso.fromString(acessoStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("mensagemErro", "Tipo de acesso inválido selecionado: " + acessoStr + ". Por favor, escolha 'Cliente' ou 'Administrador'.");
                System.err.println("Erro ao converter string de acesso '" + acessoStr + "' para enum Acesso: " + e.getMessage());
                request.getRequestDispatcher("UsuarioCadastroView.jsp").forward(request, response);
                return;
            }

            int idade = 0;
            if (idadeStr != null && !idadeStr.isEmpty()) {
                idade = Integer.parseInt(idadeStr);
            }
            usuario.setIdade(idade);

            Date nascimentoSqlDate = null;
            if (nascimentoStr != null && !nascimentoStr.isEmpty()) {
                try {
                    LocalDate localDateNascimento = LocalDate.parse(nascimentoStr);

                    java.util.Date utilDate = java.util.Date.from(localDateNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    nascimentoSqlDate = new Date(utilDate.getTime());

                } catch (Exception e) {
                    System.out.println("Erro ao converter data de nascimento: " + e.getMessage());
                }
            }
            usuario.setNascimento(nascimentoSqlDate);
            usuario.setAcesso(acesso);
            ManterUsuario manterUsuario = new ManterUsuario();
            boolean sucesso = manterUsuario.inserirLogin(usuario);

            if (sucesso) {

                response.sendRedirect("UsuarioLoginView.jsp?mensagem=Cadastro%20realizado%20com%20sucesso!");
            } else {

                response.sendRedirect("UsuarioCadastroView.jsp?mensagem=Erro%20ao%20realizar%20o%20cadastro");
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
