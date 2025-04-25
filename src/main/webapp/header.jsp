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
                <form class="link-form" id="form-ruta" action="RutaController" method="get">
                    <a href="javascript:;" onclick="document.getElementById('form-ruta').submit()">Rutas</a>
                    <input type="hidden" name="action" value="VerRutas"/>
                </form>
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
                    <li>
                        <form class="link-form" id="form-admin" action="UsuarioController" method="get">
                            <a href="javascript:;" onclick="document.getElementById('form-admin').submit()">Perfil de Usuario</a>
                            <input type="hidden" name="action" value="UsuarioPerfil"/>
                        </form>
                    </li>
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
