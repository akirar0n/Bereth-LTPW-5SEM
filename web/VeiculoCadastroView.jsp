<%-- 
    Document   : veiculoCatalogo
    Created on : 25 de mai. de 2025, 05:07:21
    Author     : thiagosilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Veículo - Agiliza Veículos</title>
        <link rel="stylesheet" href="assets/style/index.css"/> 
        <link rel="stylesheet" href="assets/style/VeiculoCadastroView.css"/>
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
            </nav>
        </header>

        <main class="content-wrapper">
            <div class="form-container">
                <div class="form-header">
                    <h2>Cadastrar Veículo</h2>
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

                <form action="CadastrarVeiculoServlet" method="post" class="cadastro-form">
                    <div class="textfield">
                        <label for="categoriaVeiculo">Categoria do Veículo:</label>
                        <select id="categoriaVeiculo" name="categoriaVeiculo" required>
                            <option value="">Selecione a Categoria</option>
                            <option value="CARRO">Carro</option>
                            <option value="MOTO">Moto</option>
                            <option value="CAMINHAO">Caminhão</option>
                            <option value="ONIBUS">Ônibus</option>
                            <option value="VAN">Van</option>
                        </select>
                    </div>

                    <div class="textfield">
                        <label for="marca">Marca:</label>
                        <input type="text" id="marca" name="marca" placeholder="Ex: Ford" required>
                    </div>

                    <div class="textfield">
                        <label for="modelo">Modelo:</label>
                        <input type="text" id="modelo" name="modelo" placeholder="Ex: Mustang" required>
                    </div>

                    <div class="textfield">
                        <label for="cor">Cor:</label>
                        <input type="text" id="cor" name="cor" placeholder="Ex: Vermelho" required>
                    </div>

                    <div class="textfield">
                        <label for="rodas">Número de Rodas:</label>
                        <input type="number" id="rodas" name="rodas" min="1" required>
                    </div>

                    <div class="textfield">
                        <label for="motorizacao">Motorização (Litros):</label>
                        <input type="number" id="motorizacao" name="motorizacao" step="0.1" min="0.1" required>
                    </div>

                    <div class="textfield">
                        <label for="pesoKg">Peso (Kg):</label>
                        <input type="number" id="pesoKg" name="pesoKg" min="1" required>
                    </div>

                    <div class="textfield">
                        <label for="capacidadeTanque">Capacidade do Tanque (Litros):</label>
                        <input type="number" id="capacidadeTanque" name="capacidadeTanque" step="0.1" min="1" required>
                    </div>

                    <div class="textfield">
                        <label for="assentos">Número de Assentos:</label>
                        <input type="number" id="assentos" name="assentos" min="1" required>
                    </div>

                    <div class="textfield">
                        <label for="anoFabricacao">Ano de Fabricação:</label>
                        <input type="number" id="anoFabricacao" name="anoFabricacao" min="1900" max="<%= java.time.Year.now().getValue()%>" required>
                    </div>

                    <div class="textfield">
                        <label for="anoModelo">Ano do Modelo:</label>
                        <input type="number" id="anoModelo" name="anoModelo" min="1900" max="<%= java.time.Year.now().getValue() + 1%>" required>
                    </div>

                    <div class="textfield">
                        <label for="placa">Placa:</label>
                        <input type="text" id="placa" name="placa" placeholder="ABC1D23" maxlength="7" pattern="[A-Z]{3}[0-9][0-9A-Z][0-9]{2}" title="Formato Mercosul: AAA1B23 ou AAA1234" required>
                    </div>

                    <div class="textfield">
                        <label for="chassi">Número do Chassi:</label>
                        <input type="text" id="chassi" name="chassi" placeholder="17 dígitos" maxlength="17" required>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Cadastrar Veículo</button>
                        <button type="reset" class="btn btn-secondary">Limpar</button>
                    </div>
                </form>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Agiliza Veículos. Todos os direitos reservados.</p>
        </footer>
    </body>
</html>
