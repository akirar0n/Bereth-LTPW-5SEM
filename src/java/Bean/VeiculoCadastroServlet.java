/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bean;

import Controller.Veiculo;
import Enuns.CategoriaVeiculo;
import Model.ManterVeiculo;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author thiagosilva
 */
public class VeiculoCadastroServlet extends HttpServlet {

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
            Veiculo veiculo = new Veiculo();
            veiculo.setCategoriaVeiculo(CategoriaVeiculo.fromString(request.getParameter("categoriaVeiculo")));
            veiculo.setMarca(request.getParameter("marca"));
            veiculo.setModelo(request.getParameter("modelo"));
            veiculo.setCor(request.getParameter("cor"));
            veiculo.setRodas(Integer.parseInt(request.getParameter("rodas")));
            veiculo.setMotorizacao(Float.parseFloat(request.getParameter("motorizacao")));
            veiculo.setPesoKg(Float.parseFloat(request.getParameter("pesoKg")));
            veiculo.setCapacidadeTanque(Float.parseFloat(request.getParameter("capacidadeTanque")));
            veiculo.setAssentos(Integer.parseInt(request.getParameter("assentos")));
            veiculo.setAnoFabricacao(Integer.parseInt(request.getParameter("anoFabricacao")));
            veiculo.setAnoModelo(Integer.parseInt(request.getParameter("anoModelo")));
            veiculo.setPlaca(request.getParameter("placa"));
            veiculo.setChassi(request.getParameter("chassi"));

            // Aqui você chamaria a classe DAO para salvar no banco de dados
            ManterVeiculo veiculoDao = new ManterVeiculo();
            // Agora o retorno de inserirVeiculo será confiável
            boolean inseridoComSucesso = veiculoDao.inserirVeiculo(veiculo);

            if (inseridoComSucesso) {
                request.setAttribute("mensagemSucesso", "Veículo cadastrado com sucesso!");
            } else {
                request.setAttribute("mensagemErro", "Não foi possível cadastrar o veículo. Tente novamente.");
            }
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao cadastrar veículo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("ListaVeiculo").forward(request, response);
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
