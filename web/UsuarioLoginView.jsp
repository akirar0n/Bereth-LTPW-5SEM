<%-- 
    Document   : UsuarioLoginView
    Created on : 25 de mai. de 2025, 01:46:47
    Author     : thiagosilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastre-se</title>
        <link rel="icon" type="image/x-icon" href="assets/img/antonios-logo.png">
        <link rel="stylesheet" href="assets/style/UsuarioLoginView.css">
    </head>

    <body>

        <%
            String mensagem = request.getParameter("mensagem");
            if (mensagem != null && !mensagem.isEmpty()) {
        %>
        <div class="message-display" style="color: green; margin-bottom: 15px; text-align: center; font-weight: bold;">
            <%= mensagem%>
        </div>
        <%
            }
            String mensagemErroLogin = (String) request.getAttribute("mensagemErro");
            if (mensagemErroLogin != null && !mensagemErroLogin.isEmpty()) {
        %>
        <div class="message-display" style="color: red; margin-bottom: 15px; text-align: center; font-weight: bold;">
            <%= mensagemErroLogin%>
        </div>
        <%
            }
        %>

        <div class="main-login">
            <div class="left-login">
                <h1>Entre com sua conta</h1>
                <img src="assets/img/antonios-logo.png" class="left-login-img" alt="Ilustração de correção de gramática">
            </div>
            <div class="right-login">
                <div class="card-login">
                    <h1>LOGIN</h1>

                    <form action="LoginUsuario" method="post">
                        <div class="textfield">
                            <label for="email">Email</label>
                            <input type="email" name="email" placeholder="Insira seu Email" required> 
                        </div>

                        <div class="textfield">
                            <label for="senha">Senha</label>
                            <input type="password" name="senha" placeholder="Insira sua senha" required>
                        </div>
                        <button type="submit" class="btn-login">Entrar</button> 

                        <p class="links-adicionais">
                            <a href="recuperar_senha.jsp">Esqueceu sua senha?</a><br>
                            <a href="UsuarioCadastroView.jsp">Não tem uma conta? Cadastre-se!</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>