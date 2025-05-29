<%-- 
    Document   : UsuarioAdminCadView
    Created on : 25 de mai. de 2025, 03:50:16
    Author     : thiagosilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastre-se</title>
        <link rel="stylesheet" href="assets/style/UsuarioCadastroView.css">
    </head>

    <body>
        <div class="main-login">
            <div class="left-login">
                <h1>Faça seu cadastro como cliente!</h1>
                <img src="assets/img/login-img.svg" class="left-login-img" alt="Ilustração">
            </div>
            <div class="right-login">
                <div class="card-login">
                    <h1>Cadastre-se</h1>
                    <form method="post" action="${pageContext.request.contextPath}/CadUsuario">

                        <div class="form-grid">
                            
                              <div class="textfield radio-group">
                                <label>Tipo de Acesso:</label>
                                <div class="radio-options">
                                    <input type="radio" id="cliente" name="acesso" value="Cliente" checked>
                                    <label for="cliente">Cliente</label>
                                    
                                    <input type="radio" id="admin" name="acesso" value="Administrador">
                                    <label for="admin">Admin</label>
                                </div>
                            </div>
                                
                            <div class="textfield">
                                <label for="nome">Nome Completo</label>
                                <input type="text" id="nome" name="nome" placeholder="Insira seu nome completo">
                            </div>

                            <div class="textfield">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" placeholder="Insira seu email" required>
                            </div>

                            <div class="textfield">
                                <label for="senha">Senha</label>
                                <input type="password" id="senha" name="senha" placeholder="Insira sua senha" required>
                            </div>

                            <div class="textfield">
                                <label for="cpf">CPF</label>
                                <input type="text" id="cpf" name="cpf" placeholder="000.000.000-00" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" title="Formato: 000.000.000-00">
                            </div>

                            <div class="textfield">
                                <label for="rg">RG</label>
                                <input type="text" id="rg" name="rg" placeholder="00.000.000-0">
                            </div>

                            <div class="textfield">
                                <label for="idade">Idade</label>
                                <input type="number" id="idade" name="idade" placeholder="Sua idade" min="0" max="150">
                            </div>

                            <div class="textfield">
                                <label for="nascimento">Data de Nascimento</label>
                                <input type="date" id="nascimento" name="nascimento">
                            </div>
                        </div>

                        <button type="submit" class="btn-cadastro">Cadastrar</button>
                        <button type="reset" class="btn-limpar">Limpar</button>

                        <p style="color: aliceblue; margin-top: 15px;">Já tem conta? 
                            <a href="${pageContext.request.contextPath}/index.jsp" style="color: #3498db; text-decoration: none;">Faça login aqui</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>