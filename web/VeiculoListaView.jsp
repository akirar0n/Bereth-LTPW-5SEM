<%-- 
    Document   : VeiculoListaView
    Created on : 25 de mai. de 2025, 19:49:13
    Author     : thiagosilva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Controller.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="Enuns.CategoriaVeiculo" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catálogo de Veículos - Agiliza Veículos</title>
        <link rel="icon" type="image/x-icon" href="assets/img/antonios-logo.png">
        <!-- <link rel="stylesheet" href="assets/style/index.css"/> -->
        <link rel="stylesheet" href="assets/style/VeiculoListaView.css"/>
    </head>
    <body>


        <header>
            <nav class="navbar">
                <div class="logo">
                    <a href="index.jsp">Agiliza Veículos</a>
                </div>
                <ul class="nav-links">
                    <li><a href="index.jsp">Início</a></li>
                    <li><a href="VeiculoListaView.jsp">Catálogo</a></li> 
                    <li><a href="sobre.jsp">Sobre Nós</a></li>
                    <li><a href="contato.jsp">Contato</a></li>
                    <li><a href="UsuarioAlterarView.jsp">Alterar dados</a></li>
                </ul>
                <div class="nav-auth-buttons">
                    <%
                        boolean logado = session.getAttribute("nome") != null;
                        Enuns.Acesso acessoUsuario = (Enuns.Acesso) session.getAttribute("acesso");
                        boolean isAdmin = logado && acessoUsuario == Enuns.Acesso.Administrador;
                    %>

                    <% if (logado) {%>
                    <a href="DeslogarUsuario" class="btn btn-logout">Sair</a>
                    <%} else {%>
                    <a href="UsuarioLoginView.jsp" class="btn btn-login-nav">Login</a>
                    <a href="UsuarioCadastroView.jsp" class="btn btn-secondary btn-register-nav">Cadastre-se</a>
                    <%}%>
                </div>
            </nav>
        </header>

        <main class="content-wrapper">
            <div class="page-header">
                <h2>Nosso Catálogo de Veículos</h2>
            </div>

            <% String mensagemErro = (String) request.getAttribute("mensagemErro");
                String mensagemSucesso = (String) request.getAttribute("mensagemSucesso");
            %>
            <% if (mensagemErro != null) {%>
            <div class="info-message error-message">
                <%= mensagemErro%>
            </div>
            <% } else if (mensagemSucesso != null) {%>
            <div class="info-message success-message">
                <%= mensagemSucesso%>
            </div>
            <% }%>

            <div class="vehicle-grid">
                <c:choose>
                    <c:when test="${not empty veiculos}"> 
                        <c:forEach var="v" items="${veiculos}">
                            <div class="vehicle-card">
                                <div class="card-image">
                                    <%-- <img src="assets/img/${v.categoriaVeiculo.name().toLowerCase()}.png" alt="${v.modelo}"> --%>
                                    <img src="assets/img/default-car.png" alt="${v.modelo}"> <%-- Imagem padrão --%>
                                </div>
                                <div class="card-details">
                                    <h3>${v.marca} ${v.modelo}</h3>
                                    <p><strong>Categoria:</strong> ${v.categoriaVeiculo}</p>
                                    <p><strong>Cor:</strong> ${v.cor}</p>
                                    <p><strong>Motorização:</strong> ${String.format("%.1f", v.motorizacao)} L</p> <%-- Formata float para 1 casa decimal --%>
                                    <p><strong>Peso:</strong> ${String.format("%.0f", v.pesoKg)} kg</p>
                                    <p><strong>Assentos:</strong> ${v.assentos}</p>
                                    <p><strong>Tanque:</strong> ${String.format("%.1f", v.capacidadeTanque)} L</p>
                                    <p><strong>Ano:</strong> ${v.anoFabricacao}/${v.anoModelo}</p>
                                    <p><strong>Placa:</strong> ${v.placa}</p>
                                    <p><strong>Chassi:</strong> ${v.chassi}</p>
                                    <a href="#" class="btn btn-details">Ver Detalhes</a>
                                </div>
                                <% if (isAdmin) { %>
                                <div class="admin-buttons">
                                    <a href="AltVeiculo?idVeiculo=${v.idVeiculo}" class="btn car-btn-edit">Alterar</a>
                                    <a href="DelVeiculo?idVeiculo=${v.idVeiculo}" class="btn car-btn-delete"
                                       onclick="return confirm('Tem certeza que deseja excluir este veículo?');">Excluir</a>
                                </div>
                                <% }%>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="no-vehicles-message">Nenhum veículo cadastrado ainda. <a href="VeiculoCadastroView.jsp">Cadastre um novo veículo aqui.</a></p>
                    </c:otherwise>
                </c:choose>
            </div>

            <div style="text-align:center; margin-top: 30px; margin-bottom: 30px;">
                <a href="VeiculoCadastroView.jsp" class="btn btn-primary">Cadastrar novo veículo</a>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Agiliza Veículos. Todos os direitos reservados.</p>
        </footer>
    </body>
</html>