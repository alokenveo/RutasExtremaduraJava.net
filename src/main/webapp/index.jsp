<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta http-equiv="content-type" content="text/html; charset=utf8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body>

    <!-- Cabecera -->
    <%@ include file="header.jsp"%>

    <main>
        <section id="carrusel">
            <div class="carrusel-container">
                <img id="imagen-carrusel" src="" alt="Imagen del carrusel">
            </div>
        </section>

        <section id="rutas">
            <h2>Rutas</h2>
            <p>Estamos probando algo a ver si funciona</p>
            <div class="rutas-container">
                <%-- Aquí podrías iterar rutas desde el backend más adelante --%>
                <%-- Ejemplo:
                List<Ruta> rutas = (List<Ruta>) request.getAttribute("rutas");
                if (rutas != null) {
                    for (Ruta ruta : rutas) {
                %>
                        <div class="ruta">
                            <h3><%= ruta.getNombre() %></h3>
                            <p><%= ruta.getDescripcion() %></p>
                        </div>
                <%
                    }
                }
                --%>
            </div>
        </section>
    </main>

    <!-- Pie de página -->
    <%@ include file="footer.jsp"%>

    <script src="/src/js/componentes.js"></script>
    <script src="/src/js/carrusel.js"></script>
    <script src="/src/js/rutas.js"></script>
</body>

</html>
