<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles_registro.css">
</head>
<body>
    <main>
        <div class="registro-container">
            <div class="registro-left">
                <h1>Registrarse</h1>
                <form action="UsuarioController" method="POST"> <!-- Ajusta la acción al servlet que procesa el registro -->
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="apellidos">Apellidos:</label>
                        <input type="text" id="apellidos" name="apellidos" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="username">Usuario:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                    </div>
                    <button type="submit">Registrarse</button>
                    <input type="hidden" name="action" value="UsuarioAltaNormal" />

                    <p class="login-link">
                        ¿Ya tienes una cuenta? <a href="<%= request.getContextPath() %>/login.jsp">Inicia sesión aquí</a>
                    </p>
                </form>
                <% 
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                    <p class="error-message"><%= error %></p>
                <% } %>
            </div>
            <div class="registro-right">
                <div id="logo">
                    <a href="#">
                        <img src="img/logo.png" alt="Logo de la App"> <!-- Cambia la ruta al logo real -->
                    </a>
                </div>
            </div>
        </div>
    </main>
</body>
</html>