<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.rutas.model.Usuario" %>
<%@ page import="es.unex.cum.tw.rutas.model.Ruta" %>
<%@ page import="es.unex.cum.tw.rutas.model.ReservaRuta" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/styles_user.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body>
    <!-- Cabecera -->
    <%@ include file="header.jsp" %>

    <%
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        String nombreUsuario = (usuario != null) ? usuario.getNombre() : "Invitado";
    %>
    <h1 style="text-align: center; background-color: #D89B00; padding: 20px;">Perfil de <%= nombreUsuario %> </h1>


    <main id="admin-main">
        

        <!-- Menú admin -->
        <aside id="admin-menu">
            <h2>Menú de Usuario</h2>
            <ul>
                <li id="ver-reservas" class="menu-item active">Mis Reservas</li>
            </ul>
        </aside>

        <!-- Contenido dinámico -->
        <div id="admin-content">

            <!-- Adminstrar reservas -->
            <section id="reservas-admin" class="admin-section active">
                <h2>Mis Reservas</h2>
                <div>
                    <table id="reservas-table">
                        <thead>
                            <tr>
                                <th>Id Reserva</th>
                                <th>Nombre Ruta</th>
                                <th>Dificultad</th>
                                <th>Metros</th>
                                <th>Fecha Reserva</th>
                                <th>Punto Encuentro</th>
                                <th>Personas</th>
                                <th>Anular Reserva</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ReservaRuta> reservas = (List<ReservaRuta>) request.getAttribute("reservas");
                                if (reservas != null) {
                                    for (ReservaRuta reserva : reservas) {
                                %>
                                    <tr class="fila-tabla">
                                        <td><%= reserva.getIdReserva() %></td>
                                        <td><%= reserva.getNombreRuta() %></td>
                                        <td><%= reserva.getDificultad() %></td>
                                        <td><%= reserva.getMetros() %></td>
                                        <td><%= reserva.getFechaReserva() %></td>
                                        <td><%= reserva.getPuntoEncuentro() %></td>
                                        <td><%= reserva.getPersonas() %></td>
                                        <td><form action="ReservaController" method="post">
                                            <input type="hidden" name="action" value="DeleteReserva" />
                                            <input type="hidden" name="idReserva" value="<%= reserva.getIdReserva() %>" />
                                            <input type="hidden" name="origen" value="usuario" />
                                            <button type="submit" class="delete-button"><i class="bi bi-trash"></i></button>
                                        </form></td>
                                    </tr>
                                <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                
            </section>
        </div>
    </main>

    <!-- Pie de página -->
    <%@ include file="footer.jsp" %>

    <script src="js/script_user.js"></script>
</body>

</html>