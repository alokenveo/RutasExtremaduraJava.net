<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="css/styles_login.css">
</head>
<body>
    <main>
        <div class="login-container">
            <div class="login-left">
                <div id="logo">
                    <a href="#">
                        <img src="img/logo.png" alt="Logo de la App">
                    </a>
                </div>
            </div>
            <div class="login-right">
                <h1>Iniciar Sesión</h1>
                <form action="loginServlet" method="POST"> <!-- Ajusta la acción al servlet que procesa el login -->
                    <div class="form-group">
                        <label for="username">Usuario:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit">Entrar</button>
                </form>
                <% 
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                    <p class="error-message"><%= error %></p>
                <% } %>
            </div>
        </div>
    </main>
</body>
</html>