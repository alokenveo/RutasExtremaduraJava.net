<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.rutas.model.Usuario" %>
<%@ page import="es.unex.cum.tw.rutas.model.Ruta" %>
<%@ page import="es.unex.cum.tw.rutas.model.Reserva" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adminstrador | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/styles_admin.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body>
    <!-- Cabecera -->
    <%@ include file="header.jsp" %>

    <h1 style="text-align: center;  background-color: #D89B00; padding: 20px;">Adminstrador</h1>

    <main id="admin-main">
        

        <!-- Menú admin -->
        <aside id="admin-menu">
            <h2>Menú de administración</h2>
            <ul>
                <li id="ver-usuarios" class="menu-item active">Usuarios</li>
                <li id="ver-rutas" class="menu-item">Rutas</li>
                <li id="ver-reservas" class="menu-item">Reservas</li>
            </ul>
        </aside>

        <!-- Contenido dinámico -->
        <div id="admin-content">
            <!-- Adminstrar rutas -->
            <section id="usuarios-admin" class="admin-section active">
                <h2>Usuarios</h2>
                <div class="admin-buttons">
                    <button class="admin-button" id="add-usuario">Añadir usuario</button>
                </div>

                <!-- Modal de añadir usuario -->
                <div id="modal-usuario" class="modal">
                    <div class="modal-content">
                        <span class="modal-close" id="modal-close-usuario">&times;</span>
                        <h2>Añadir Nuevo Usuario</h2>
                        <form action="UsuarioController" method="POST" id="form-usuario">
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
                                <label for="username">Username:</label>
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
                            <div class="modal-buttons">
                                <button type="submit" class="admin-button">Añadir usuario</button>
                                <button type="button" class="admin-button cancel-button" id="cancel-button-usuario">Cancelar</button>
                            </div>
                            <input type="hidden" name="action" value="UsuarioAltaAdmin" />
                        </form>
                    </div>
                </div>

                <div style="width: 100%">
                    <table id="usuarios-table">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Email</th>
                                <th>Username</th>
                                <th>Fecha de Nacimiento</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody id="usuarios-body">
                            <%
                                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                                if (usuarios != null) {
                                    for (Usuario usuario : usuarios) {
                                %>
                                    <tr class="fila-tabla">
                                        <td><%= usuario.getNombre() %></td>
                                        <td><%= usuario.getApellidos() %></td>
                                        <td><%= usuario.getEmail() %></td>
                                        <td><%= usuario.getUsername() %></td>
                                        <td><%= usuario.getFechaNacimiento() %></td>
                                        <td><form action="UsuarioController" method="post">
                                            <input type="hidden" name="action" value="DeleteUsuario" />
                                            <input type="hidden" name="idUsuario" value="<%= usuario.getId() %>" />
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

            <!-- Adminstrar rutas -->
            <section id="rutas-admin" class="admin-section">
                <h2>Rutas</h2>
                <div class="admin-buttons">
                    <button class="admin-button" id="add-ruta">Añadir ruta</button>
                </div>
                <!-- Modal de adición de ruta -->
                <div id="modal-ruta" class="modal">
                    <div class="modal-content">
                        <span class="modal-close" id="modal-close-ruta">&times;</span>
                        <h2>Añadir Nueva Ruta</h2>
                        <form action="RutaController" method="POST" id="form-ruta" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" id="nombre" name="nombre" required>
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripción:</label>
                                <input type="text" id="descripcion" name="descripcion" required>
                            </div>
                            <div class="form-group">
                                <label for="enlace">Enlace:</label>
                                <input type="text" id="enlace" name="enlace" required>
                            </div>
                            <div class="form-group">
                                <label for="maximoUsuario">Usuarios máximos:</label>
                                <input type="number" id="maximoUsuario" name="maximoUsuario" required>
                            </div>
                            <div class="form-group">
                                <label for="dificultad">Dificultad:</label>
                                <input type="number" id="dificultad" name="dificultad" required>
                            </div>
                            <div class="form-group">
                                <label for="metros">Metros(m):</label>
                                <input type="number" id="metros" name="metros" required>
                            </div>

                            <div class="form-group">
                                <label for="fotos">Selecciona fotos:</label>
                                <input type="file" id="fotos" name="fotos" multiple accept="image/*">
                            </div>

                            <div class="modal-buttons">
                                <button type="submit" class="admin-button">Añadir ruta</button>
                                <button type="button" class="admin-button cancel-button" id="cancel-button-ruta">Cancelar</button>
                            </div>
                            <input type="hidden" name="action" value="RutaAlta" />
                        </form>
                    </div>                    
                </div>
                <div>
                    <table id="rutas-table">
                        <thead>
                            <tr>
                                <th>Id Ruta</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Fecha</th>
                                <th>Máximo Usuarios</th>
                                <th>Dificultad</th>
                                <th>Metros(m)</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Ruta> rutas = (List<Ruta>) request.getAttribute("rutas");
                                if (rutas != null) {
                                    for (Ruta ruta : rutas) {
                                %>
                                    <tr class="fila-tabla">
                                        <td><%= ruta.getIdRuta() %></td>
                                        <td><%= ruta.getNombre() %></td>
                                        <td><%= ruta.getDescripcion() %></td>
                                        <td><%= ruta.getFechaIncorporacion() %></td>
                                        <td><%= ruta.getMaximoUsuario() %></td>
                                        <td><%= ruta.getDificultad() %></td>
                                        <td><%= ruta.getMetros() %></td>
                                        <td><form action="RutaController" method="post">
                                            <input type="hidden" name="action" value="DeleteRuta" />
                                            <input type="hidden" name="idRuta" value="<%= ruta.getIdRuta() %>" />
                                            <button type="submit" class="delete-button"><i class="bi bi-trash"></i></button>
                                        </form></td>
                                    </tr>
                                    <!-- Modal de info reserva -->
                                    <div style="display: none;"></div>
                                <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>

            </section>

            <!-- Adminstrar rutas -->
            <section id="reservas-admin" class="admin-section">
                <h2>Reservas</h2>
                <div>
                    <table id="reservas-table">
                        <thead>
                            <tr>
                                <th>Id Reserva</th>
                                <th>Id Usuario</th>
                                <th>Id Ruta</th>
                                <th>Fecha</th>
                                <th>Eliminar Reserva</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
                                if (reservas != null) {
                                    for (Reserva reserva : reservas) {
                                %>
                                    <tr class="fila-tabla">
                                        <td><%= reserva.getIdReserva() %></td>
                                        <td><%= reserva.getIdUsuario() %></td>
                                        <td><%= reserva.getIdRuta() %></td>
                                        <td><%= reserva.getFecha() %></td>
                                        <td><form action="ReservaController" method="post">
                                            <input type="hidden" name="action" value="DeleteReserva" />
                                            <input type="hidden" name="idReserva" value="<%= reserva.getIdReserva() %>" />
                                            <input type="hidden" name="origen" value="admin" />
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

    <script src="js/script_admin.js"></script>
</body>

</html>