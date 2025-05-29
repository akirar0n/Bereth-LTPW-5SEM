<%-- 
    Document   : index
    Created on : 21 de mai. de 2025, 22:42:03
    Author     : thiagosilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="assets/style/index.css"/>
    </head>
    <body>
        <header>
            <nav class="navbar">
                <div class="logo">
                    <a href="index.jsp">Agiliza Veículos</a>
                </div>
                <ul class="nav-links">
                    <li><a href="index.jsp">Início</a></li>
                    <li><a href="catalogo.jsp">Catálogo</a></li>
                    <li><a href="sobre.jsp">Sobre Nós</a></li>
                    <li><a href="contato.jsp">Contato</a></li>
                    <li><a href="UsuarioAlterarView.jsp">Alterar dados</a></li>
                </ul>
                <div class="nav-auth-buttons">
                    <%
                        boolean logado = session.getAttribute("nome") != null;
                    %>

                    <% if (logado) {%>
                    <a href="DeslogarUsuario" class="btn btn-logout">Sair</a>
                    <%} else {%>
                    <a href="UsuarioLoginView.jsp" class="btn btn-login-nav">Login</a>
                    <a href="UsuarioCadastroView.jsp" class="btn btn-secondary btn-register-nav">Cadastre-se</a>
                    <%}%>
                </div>
                <!-- 
                <div class="menu-toggle" id="mobile-menu">
                    <span class="bar"></span>
                    <span class="bar"></span>
                    <span class="bar"></span>
                </div>
                -->
            </nav>
        </header>

        <main class="content-wrapper"> 
            <% if (logado) {%>
            <div class="user-info-section">
                <p class="welcome-message success-message">Bem vindo, <strong><%=session.getAttribute("nome")%></strong>!</p>
              
                <a href="VeiculoCadastroView.jsp" data-toggle="modal" data-target="#modalCadastrar" class="btn btn-primary btn-add-news">Cadastrar Veículo</a>
            </div>
            <% } else {%>
            <div class="user-info-section">
                <div class="info-message error-message">
                    Autentique-se para coisar as coisas!
                </div>
            </div>
            <% }%>

            <section class="hero-section">
                <h1>Encontre o seu veículo ideal</h1>
                <p>A maior variedade de carros novos e seminovos para você!</p>
                <a href="catalogo.jsp" class="btn btn-primary">Ver Catálogo</a>
            </section>

            <section class="featured-cars">
                <h2>Carros em Destaque</h2>
                <div class="car-grid">
                    <div class="car-item">
                        <img src="img/carro_exemplo.jpg" alt="Carro Exemplo">
                        <h3>Nome do Carro</h3>
                        <p>Preço: R$ XXXXX</p>
                        <a href="#" class="btn btn-view-details">Ver Detalhes</a>
                    </div>
                    <div class="car-item">
                        <img src="img/carro_exemplo2.jpg" alt="Carro Exemplo 2">
                        <h3>Nome do Carro 2</h3>
                        <p>Preço: R$ YYYYY</p>
                        <a href="#" class="btn btn-view-details">Ver Detalhes</a>
                    </div>
                </div>
            </section>

            <section class="about-promo">
                <h2>Sobre a Agiliza Veículos</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                <a href="sobre.jsp" class="btn btn-secondary">Saiba Mais</a>
            </section>

        </main>

        <footer>
            <p>&copy; 2025 Agiliza Veículos. Todos os direitos reservados.</p>
        </footer>
    </body>
</html>