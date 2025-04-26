package es.unex.cum.tw.rutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

import es.unex.cum.tw.rutas.service.RutaService;
import es.unex.cum.tw.rutas.service.RutaServiceBD;
import es.unex.cum.tw.rutas.service.ValoracionService;
import es.unex.cum.tw.rutas.service.ValoracionServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ValoracionController")
public class ValoracionController extends HttpServlet {

	private ValoracionService valoracionService = new ValoracionServiceBD();
	private RutaService rutaService = new RutaServiceBD();

	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("action");
		if ("CrearOActualizarValoracion".equals(accion)) {
			valorarRuta(req, res);
		} else {
		}

	}

	public void valorarRuta(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		JSONObject jsonResponse = new JSONObject();

		int idRuta = Integer.parseInt(req.getParameter("idRuta"));
		int valoracion = Integer.parseInt(req.getParameter("valoracion"));

		// Obtener el idUsuario desde la sesión
		HttpSession session = req.getSession(false);
		int idUsuario = Integer.parseInt((String) session.getAttribute("id"));

		// Crear o actualizar la valoración
		try {
			valoracionService.crearOActualizarValoracion(idUsuario, idRuta, valoracion);
			// Obtener la nueva valoración media
			double nuevaValoracionMedia = rutaService.obtenerValoracionMedia(idRuta);

			jsonResponse.put("success", true);
			jsonResponse.put("nuevaValoracionMedia", nuevaValoracionMedia);
		} catch (SQLException e) {
			e.printStackTrace();
			jsonResponse.put("success", false);
			jsonResponse.put("error", e.getMessage());
		}
		out.print(jsonResponse.toString());
		out.flush();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	@Override
	public void destroy() {
	}
}
