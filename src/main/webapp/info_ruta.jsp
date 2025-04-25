<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.unex.cum.tw.rutas.model.Ruta" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información de Ruta | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/styles_info.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <%
    if (session.getAttribute("nombre") == null) {
        response.sendRedirect("login.jsp");
    } else {
        Ruta ruta = (Ruta) request.getAttribute("ruta");
        if (ruta == null) {
            response.sendRedirect("rutas.jsp");
        } else {
    %>
        <!-- Cabecera -->
        <%@ include file="header.jsp"%>

        <main>
            <h1>Información de la Ruta: <%= ruta.getNombre() %></h1>

            <section id="info-ruta" class="info-ruta-container">
                <div class="ruta-imagenes">
                    <%
                        List<String> fotos = ruta.getFotos();
                        if (fotos != null) {
                            for (String foto : fotos) {
                    %>
                        <img src="<%= request.getContextPath() %><%= foto %>" alt="Imagen de <%= ruta.getNombre() %>" />
                    <%
                            }
                        }
                    %>
                </div>

                <div class="ruta-detalles">
                    <h2><%= ruta.getNombre() %></h2>
                    <p><strong>Descripción:</strong> <%= ruta.getDescripcion() %></p>
                    <p><strong>Fecha de Incorporación:</strong> <%= ruta.getFechaIncorporacion() %></p>
                    <p><strong>Máximo de Usuarios:</strong> <%= ruta.getMaximoUsuario() %></p>
                    <p><strong>Dificultad:</strong> <%= ruta.getDificultad() %> (1-5)</p>
                    <p><strong>Metros:</strong> <%= ruta.getMetros() %> m</p>
                    <div class="info-adicional">
                        <h3>Información Adicional</h3>
                        <p><strong>Enlace:</strong> <a href="<%= ruta.getEnlace() %>" target="_blank">Visitar página de la ruta</a></p>
                    </div>
                    <button class="reserva-boton" onclick="abrirModalReserva()">Reservar</button>
                </div>
            </section>

            <!-- Modal de Reserva -->
            <div id="modal-reserva" class="modal">
                <div class="modal-content">
                    <span class="modal-close" onclick="cerrarModalReserva()">×</span>
                    <h2>Formulario de Reserva</h2>

                    <form id="form-reserva" action="ReservaController" method="POST">
                        <!-- DATOS PERSONALES -->
                        <fieldset>
                            <legend>Datos Personales</legend>

                            <div class="grupo-campos">
                                <div class="campo">
                                    <label for="telefono">Teléfono *</label>
                                    <input type="tel" id="telefono" name="telefono" required pattern="[0-9]{9}"
                                        placeholder="Ej: 600123456">
                                </div>

                                <div class="campo">
                                    <label for="tipo-documento">Tipo de Documento *</label>
                                    <select id="tipo-documento" name="tipo-documento" required>
                                        <option value="DNI">DNI</option>
                                        <option value="NIE">NIE</option>
                                        <option value="Pasaporte">Pasaporte</option>
                                    </select>
                                </div>

                                <div class="campo">
                                    <label for="num-documento">Número de Documento *</label>
                                    <input type="text" id="num-documento" name="num-documento" required
                                        pattern="[A-Za-z0-9]{8,12}">
                                </div>
                            </div>
                        </fieldset>

                        <!-- SELECCIÓN DE RUTA -->
                        <fieldset>
                            <legend>Selecciona la Ruta</legend>

                            <label for="ruta">Ruta *</label>
                            <select id="ruta" name="ruta" required>
                                <option value="<%= ruta.getIdRuta() %>" selected><%= ruta.getNombre() %></option>
                            </select>

                            <label for="horario">Horario Preferente *</label>
                            <select id="horario" name="horario" required>
                                <option value="mañana">Mañana (9:00 - 12:00)</option>
                                <option value="tarde">Tarde (16:00 - 19:00)</option>
                            </select>

                            <label for="punto-encuentro">Lugar de Salida *</label>
                            <input type="text" id="punto-encuentro" name="punto-encuentro" required>
                        </fieldset>

                        <!-- DETALLES DE LA RESERVA -->
                        <fieldset>
                            <legend>Detalles de la Reserva</legend>

                            <label for="fecha">Fecha *</label>
                            <input type="date" id="fecha" name="fecha" required>

                            <label for="personas">Número de Personas *</label>
                            <input type="number" id="personas" name="personas" min="1" max="<%= ruta.getMaximoUsuario() %>" required>

                            <label for="alergias">Alergias o Preferencias Alimentarias</label>
                            <input type="text" id="alergias" name="alergias"
                                placeholder="Ej: Vegetariano, alergia a frutos secos...">

                            <label for="comentarios">Comentarios Adicionales</label>
                            <textarea id="comentarios" name="comentarios" rows="4"
                                placeholder="Indica si hay alguna necesidad especial..."></textarea>
                        </fieldset>

                        <!-- SERVICIOS EXTRAS -->
                        <fieldset>
                            <legend>Servicios Extras</legend>
                            <label class="servicios"><input type="checkbox" name="extras" value="comida"> Comida incluida</label>
                            <label class="servicios"><input type="checkbox" name="extras" value="equipo"> Equipo de senderismo</label>
                            <label class="servicios"><input type="checkbox" name="extras" value="guia"> Guía personalizado</label>
                        </fieldset>

                        <div style="display: flex; align-items: center">
                            <label for="terminos" style="margin-top: 0">
                                Acepto los <a href="#">términos y condiciones</a> y la <a href="#">política de privacidad</a> *
                            </label>
                            <input type="checkbox" id="terminos" name="terminos" required>
                        </div>

                        <!-- ENVÍO -->
                        <div class="modal-buttons">
                            <button type="submit">Reservar Ruta</button>
                            <button type="button" class="cancel-button" onclick="cerrarModalReserva()">Cancelar</button>
                        </div>
                        <input type="hidden" name="action" value="CrearReserva" />

                        <!-- Mensaje de validación -->
                        <div id="mensaje-form" style="margin-top: 10px; color: red;">
                            <% 
                                String error = (String) request.getAttribute("error");
                                if (error != null) {
                                    out.print(error);
                                }
                            %>
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <!-- Pie de página -->
        <%@ include file="footer.jsp"%>

        <script>
            function abrirModalReserva() {
                document.getElementById('modal-reserva').style.display = 'flex';
            }

            function cerrarModalReserva() {
                document.getElementById('modal-reserva').style.display = 'none';
                document.getElementById('form-reserva').reset();
            }

            // Cerrar modal al hacer clic fuera del contenido
            document.getElementById('modal-reserva').addEventListener('click', (e) => {
                if (e.target === document.getElementById('modal-reserva')) {
                    cerrarModalReserva();
                }
            });
        </script>
    <% 
        }
    } %>
</body>
</html>