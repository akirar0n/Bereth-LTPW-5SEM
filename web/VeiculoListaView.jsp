<%-- 
    Document   : VeiculoListaView
    Created on : 31 de mai. de 2025, 22:53:06
    Author     : thiagosilva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Controller.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="Enuns.CategoriaVeiculo" %>

<html>
    <head>
        <link rel="icon" type="image/x-icon" href="assets/img/antonios-logo.png">
        <link rel="stylesheet" href="assets/style/VeiculoListaView.css"/>
        <title>Lista de Veículos</title>
    </head>
    <body>
        <div class="container">
            <h2>Veículos Cadastrados</h2>

            <div class="grid">
                <c:forEach var="v" items="${veiculos}"> // revisar tag 
                    <div class="card">
                        <h3>${v.marca} ${v.modelo}</h3>
                        <p><strong>Categoria:</strong> ${v.categoriaVeiculo}</p>
                        <p><strong>Cor:</strong> ${v.cor}</p>
                        <p><strong>Motorização:</strong> ${v.motorizacao} L</p>
                        <p><strong>Peso:</strong> ${v.pesoKg} kg</p>
                        <p><strong>Assentos:</strong> ${v.assentos}</p>
                        <p><strong>Tanque:</strong> ${v.capacidadeTanque} L</p>
                        <p><strong>Ano:</strong> ${v.anoFabricacao}/${v.anoModelo}</p>
                        <p><strong>Placa:</strong> ${v.placa}</p>
                        <p><strong>Chassi:</strong> ${v.chassi}</p>
                    </div>
                </c:forEach>
            </div>

            <div style="text-align:center; margin-top: 20px;">
                <a href="VeiculoCadastroView.jsp">Cadastrar novo veículo</a>
            </div>
        </div>
    </body>
</html>