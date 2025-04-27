<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.rutas.model.Ruta" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta http-equiv="content-type" content="text/html; charset=utf8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
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
            <!-- Novedades: Últimas 5 rutas añadidas -->
            <div class="seccion-rutas">
                <h2>Últimas Novedades</h2>
                <div class="lista-rutas">
                    <%
                        List<Ruta> ultimasRutas = (List<Ruta>) request.getAttribute("ultimasRutas");
                        if (ultimasRutas != null && !ultimasRutas.isEmpty()) {
                            for (Ruta ruta : ultimasRutas) {
                                String primeraFoto = (ruta.getFotos() != null && !ruta.getFotos().isEmpty()) ? ruta.getFotos().get(0) : "";
                                double valoracionMedia = (Double) request.getAttribute("valoracion_" + ruta.getIdRuta()) != null ? (Double) request.getAttribute("valoracion_" + ruta.getIdRuta()) : 0.0;
                    %>
                        <div class="ruta-item">
                            <img src="<%= request.getContextPath() %><%= primeraFoto %>" alt="Imagen de <%= ruta.getNombre() %>">
                            <div class="ruta-info">
                                <a href="<%= request.getContextPath() %>/RutaController?action=VerRuta&idRuta=<%= ruta.getIdRuta() %>">
                                    <%= ruta.getNombre() %>
                                </a>
                                <span class="valoracion">
                                    <i class="bi bi-star-fill"></i> <%= String.format("%.1f", valoracionMedia) %>
                                </span>
                            </div>
                        </div>
                    <%
                            }
                        } else {
                    %>
                        <p>No hay novedades disponibles.</p>
                    <%
                        }
                    %>
                </div>
            </div>

            <!-- Rutas por dificultad -->
            <div class="seccion-rutas">
                <h2>Rutas por Dificultad</h2>
                <div class="lista-rutas">
                    <%
                        List<Ruta> rutasPorDificultad = (List<Ruta>) request.getAttribute("rutasPorDificultad");
                        if (rutasPorDificultad != null && !rutasPorDificultad.isEmpty()) {
                            for (Ruta ruta : rutasPorDificultad) {
                                String primeraFoto = (ruta.getFotos() != null && !ruta.getFotos().isEmpty()) ? ruta.getFotos().get(0) : "";
                                double valoracionMedia = (Double) request.getAttribute("valoracion_" + ruta.getIdRuta()) != null ? (Double) request.getAttribute("valoracion_" + ruta.getIdRuta()) : 0.0;
                    %>
                        <div class="ruta-item">
                            <img src="<%= request.getContextPath() %><%= primeraFoto %>" alt="Imagen de <%= ruta.getNombre() %>">
                            <div class="ruta-info">
                                <a href="<%= request.getContextPath() %>/RutaController?action=VerRuta&idRuta=<%= ruta.getIdRuta() %>">
                                    <%= ruta.getNombre() %>
                                </a>
                                <span class="valoracion">
                                    <i class="bi bi-star-fill"></i> <%= String.format("%.1f", valoracionMedia) %>
                                </span>
                            </div>
                        </div>
                    <%
                            }
                        } else {
                    %>
                        <p>No hay rutas disponibles.</p>
                    <%
                        }
                    %>
                </div>
            </div>

            <!-- Rutas por distancia -->
            <div class="seccion-rutas">
                <h2>Rutas por Distancia</h2>
                <div class="lista-rutas">
                    <%
                        List<Ruta> rutasPorDistancia = (List<Ruta>) request.getAttribute("rutasPorDistancia");
                        if (rutasPorDistancia != null && !rutasPorDistancia.isEmpty()) {
                            for (Ruta ruta : rutasPorDistancia) {
                                String primeraFoto = (ruta.getFotos() != null && !ruta.getFotos().isEmpty()) ? ruta.getFotos().get(0) : "";
                                double valoracionMedia = (Double) request.getAttribute("valoracion_" + ruta.getIdRuta()) != null ? (Double) request.getAttribute("valoracion_" + ruta.getIdRuta()) : 0.0;
                    %>
                        <div class="ruta-item">
                            <img src="<%= request.getContextPath() %><%= primeraFoto %>" alt="Imagen de <%= ruta.getNombre() %>">
                            <div class="ruta-info">
                                <a href="<%= request.getContextPath() %>/RutaController?action=VerRuta&idRuta=<%= ruta.getIdRuta() %>">
                                    <%= ruta.getNombre() %>
                                </a>
                                <span class="valoracion">
                                    <i class="bi bi-star-fill"></i> <%= String.format("%.1f", valoracionMedia) %>
                                </span>
                            </div>
                        </div>
                    <%
                            }
                        } else {
                    %>
                        <p>No hay rutas disponibles.</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </section>
    </main>

    <!-- Pie de página -->
    <%@ include file="footer.jsp"%>


    <script>
        const imagenes = [
            '<%= request.getContextPath() %>/img/rutas/castillos1.jpg',
            '<%= request.getContextPath() %>/img/rutas/castillos2.jpg',
            '<%= request.getContextPath() %>/img/rutas/castillos3.jpg',
            '<%= request.getContextPath() %>/img/rutas/conquistadores1.jpg',
            '<%= request.getContextPath() %>/img/rutas/conquistadores2.jpg',
            '<%= request.getContextPath() %>/img/rutas/conquistadores3.jpg',
            '<%= request.getContextPath() %>/img/rutas/dehesas1.jpg',
            '<%= request.getContextPath() %>/img/rutas/dehesas2.jpg',
            '<%= request.getContextPath() %>/img/rutas/dehesas3.jpg',
            '<%= request.getContextPath() %>/img/rutas/plata1.jpg',
            '<%= request.getContextPath() %>/img/rutas/plata2.jpg',
            '<%= request.getContextPath() %>/img/rutas/plata3.jpg',
            '<%= request.getContextPath() %>/img/rutas/jamon2.jpg',
            '<%= request.getContextPath() %>/img/rutas/jamon1.jpg',
            '<%= request.getContextPath() %>/img/rutas/jamon3.jpg',
            '<%= request.getContextPath() %>/img/rutas/monasterios1.jpg',
            '<%= request.getContextPath() %>/img/rutas/monasterios2.jpg'
            ];

            let indice = 0;
            const img = document.getElementById("imagen-carrusel");

            function cambiarImagen() {
            img.src = imagenes[indice];
            indice = (indice + 1) % imagenes.length;
            }

            setInterval(cambiarImagen, 4500);

            cambiarImagen(); // Cambia la imagen al cargar la página
    </script>
</body>

</html>
