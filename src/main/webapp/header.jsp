<div id="header">
    <div id="logo">
        <a href="<%= request.getContextPath() %>/index.jsp" title="Ir a la pï¿½gina principal">
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
                    <li><a href="<%= request.getContextPath() %>/src/html/historial.jsp">Histórico de Puntuaciones</a></li>
                </ul>
            </li>
            <li><a href="/src/html/bibliografia.jsp">Bibliografía</a></li>
        </ul>
    </nav>
</div>
