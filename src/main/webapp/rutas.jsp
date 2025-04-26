<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.rutas.model.Ruta" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta http-equiv="content-type" content="text/html; charset=utf8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rutas | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/filtros.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body>

    <!-- Cabecera -->
    <%@ include file="header.jsp"%>

    <main>
        <h1>Rutas</h1>

        <!-- Sección de filtrado -->
        <section id="filtro-rutas" class="filtro-container">
            <h2>Filtrar Rutas</h2>
            <form action="RutaController" method="GET">
                <div class="filtro-campos">
                    <div class="campo">
                        <label for="dificultad">Dificultad (1-5):</label>
                        <input type="number" id="dificultad" name="dificultad" min="1" max="5" placeholder="Ej: 3">
                    </div>
                    <div class="campo">
                        <label for="metros">Metros (máximo):</label>
                        <input type="number" id="metros" name="metros" min="0" placeholder="Ej: 1000">
                    </div>
                    <div class="campo">
                        <label for="maximoUsuario">Máximo de Usuarios:</label>
                        <input type="number" id="maximoUsuario" name="maximoUsuario" min="1" placeholder="Ej: 10">
                    </div>
                </div>
                <button type="submit" class="filtro-boton">Filtrar</button>
                <input type="hidden" name="action" value="filtrarRutas" />
            </form>
        </section>

        <section id="rutas" class="rutas-container">
            <%
                List<Ruta> rutas = (List<Ruta>) request.getAttribute("rutas");
                for (Ruta ruta : rutas) {
            %>
                <div class="ruta">
                    <div class="ruta-imagenes">
                        <%-- Recorremos las fotos de la ruta --%>
                        <%
                            List<String> fotos = ruta.getFotos();
                            for (String foto : fotos) {
                        %>
                            <img src="<%= request.getContextPath() %><%= foto %>" alt="Imagen de <%= ruta.getNombre() %>" />
                        <%  
                            }
                        %>
                    </div>
    
                    <h3>
                        <a href="RutaController?action=VerRuta&idRuta=<%= ruta.getIdRuta() %>">
                            <%= ruta.getNombre() %>
                        </a>
                    </h3>
    
                    <p><%= ruta.getDescripcion() %></p>
                </div>
            <%
                }
            %>
        </section>
    </main>
    

    <!-- Pie de página -->
    <%@ include file="footer.jsp"%>

</body>

</html>