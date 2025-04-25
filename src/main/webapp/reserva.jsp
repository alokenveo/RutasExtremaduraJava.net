<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.rutas.model.Ruta" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva | Rutas Extremadura.net</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/style_reserva.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <!-- Cabecera -->
    <%@ include file="header.jsp" %>

    <main>
        <section id="reserva">
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
                        <option value="" disabled selected>Selecciona una ruta</option>
                        <%
                        List<Ruta> rutas = (List<Ruta>) request.getAttribute("rutas");
                        if (rutas != null) {
                            for (Ruta ruta : rutas) {
                        %>
                                <option value="<%= ruta.getIdRuta() %>"><%= ruta.getNombre() %></option>
                        <%
                            }
                        }
                        %>
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
                    <input type="number" id="personas" name="personas" min="1" max="50" required>

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
                <button type="submit">Reservar Ruta</button>
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
        </section>
    </main>

    <!-- Pie de página -->
    <%@ include file="footer.jsp" %>

    <script src="<%= request.getContextPath() %>/src/js/reserva.js"></script>
</body>
</html>