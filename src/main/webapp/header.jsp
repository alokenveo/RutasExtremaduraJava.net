<div id="header">
    <div id="logo">
        <a href="<%= request.getContextPath() %>" title="Ir a la p�gina principal">
            <img src="img/logo.png" alt="Logo Portal">
            <h1>Rutas Extremadura.net</h1>
        </a>
    </div>

    <nav>
        <ul>
            <li>
                Secciones
                <ul>
                    <li><a href="<%= request.getContextPath() %>/index.jsp#rutas">Rutas</a></li>
                    <li><a href="<%= request.getContextPath() %>/src/html/novedades.jsp">Novedades</a></li>
                    <li><a href="<%= request.getContextPath() %>/src/html/reserva.jsp">Reserva</a></li>
                </ul>
            </li>
            <li>
                Conecta4
                <ul>
                    <li><a href="<%= request.getContextPath() %>/src/html/conecta4.jsp">Jugar Conecta4</a></li>
                    <li><a href="<%= request.getContextPath() %>/src/html/historial.jsp">Hist�rico de Puntuaciones</a></li>
                </ul>
            </li>
            <li><a href="/src/html/bibliografia.jsp">Bibliograf�a</a></li>

            <%
            if (session.getAttribute("username").equals("admin"))   {
            %>
                    <li>
                        <form class="link-form" id="form-admin" action="UsuarioController" method="get">
                            <a href="javascript:;" onclick="document.getElementById('form-admin').submit()">Adminstrador</a>
                            <input type="hidden" name="action" value="UsuarioAdmin"/>
                        </form>
                    </li>  
            <%} %>

            <li>
                <i class="bi bi-person-circle" style="width: 50px;"></i>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/src/html/conecta4.jsp">Perfil de usuario</a></li>
                    <li>
                        <form action="UsuarioController" method="POST" style="display: inline;">
                            <input type="hidden" name="action" value="UsuarioLogout" />
                            <button type="submit" style="background: none; border: none; padding: 0; color: inherit; font: inherit; cursor: pointer;">
                                <i class="bi bi-box-arrow-right"></i> Cerrar sesión
                            </button>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</div>
