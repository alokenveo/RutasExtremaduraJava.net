package es.unex.cum.tw.rutas.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

import es.unex.cum.tw.rutas.model.Reserva;
import es.unex.cum.tw.rutas.model.ReservaRuta;
import es.unex.cum.tw.rutas.model.Ruta;
import es.unex.cum.tw.rutas.model.Usuario;
import es.unex.cum.tw.rutas.service.ReservaService;
import es.unex.cum.tw.rutas.service.ReservaServiceBD;
import es.unex.cum.tw.rutas.service.RutaService;
import es.unex.cum.tw.rutas.service.RutaServiceBD;
import es.unex.cum.tw.rutas.service.UsuarioService;
import es.unex.cum.tw.rutas.service.UsuarioServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ReservaController")
public class ReservaController extends HttpServlet {
	private UsuarioService usuarioService = new UsuarioServiceBD();
	private RutaService rutaService = new RutaServiceBD();
	private ReservaService reservaService = new ReservaServiceBD();

	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("action");
		if (accion.equals("CrearReserva")) {
			crearReserva(req, res);
		} else if (accion.equals("VerReserva")) {
			mostrarFormReserva(req, res);
		} else if (accion.equals("DeleteReserva")) {
			eliminarReserva(req, res);
		} else {
		}
	}

	public void crearReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			// Obtener los parámetros del formulario
			String telefono = req.getParameter("telefono");
			String tipoDocumento = req.getParameter("tipo-documento");
			String numDocumento = req.getParameter("num-documento");
			int idRuta = Integer.parseInt(req.getParameter("ruta"));
			String horario = req.getParameter("horario");
			String puntoEncuentro = req.getParameter("punto-encuentro");
			Date fecha = Date.valueOf(req.getParameter("fecha"));
			int personas = Integer.parseInt(req.getParameter("personas"));
			String alergias = req.getParameter("alergias");
			String comentarios = req.getParameter("comentarios");
			String[] serviciosExtras = req.getParameterValues("extras");

			// Convertir serviciosExtras a JSON
			String serviciosExtrasJson = "[]";
			if (serviciosExtras != null) {
				JSONArray jsonArray = new JSONArray();
				for (String extra : serviciosExtras) {
					jsonArray.put(extra);
				}
				serviciosExtrasJson = jsonArray.toString();
			}

			// Usuario usuario=usuarioService.
			HttpSession session = req.getSession(true);
			int idUsuario = Integer.parseInt((String) session.getAttribute("id"));
			Reserva reserva = new Reserva(idUsuario, idRuta, fecha, personas, horario, puntoEncuentro, alergias,
					comentarios, serviciosExtrasJson);

			boolean creada = reservaService.crearReserva(reserva);
			if (creada) {
				// Redirigir a una página de éxito o actualizar datos
				res.sendRedirect(req.getContextPath() + "/reserva.jsp?success=Reserva+creada+exitosamente");
			} else {
				req.setAttribute("error", "No se pudo crear la reserva. Inténtalo de nuevo.");
				req.getRequestDispatcher("./reserva.jsp").forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error al procesar la reserva: " + e.getMessage());
			req.getRequestDispatcher("./reserva.jsp").forward(req, res);
		}
	}

	public void eliminarReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idReserva = req.getParameter("idReserva");
		String origen = req.getParameter("origen");
		boolean borrado = reservaService.eliminarReserva(idReserva);

		if (!borrado) {
			req.setAttribute("error", "No se ha podido eliminar la reserva");
		}

		if ("admin".equals(origen)) {
			obtenerDatos(req, res);
		} else if ("usuario".equals(origen)) {
			obtenerDatosUsuario(req, res);
		} else {
			res.sendRedirect("index.jsp");
		}
	}

	public void mostrarFormReserva(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		List<Ruta> rutas = rutaService.obtenerRutas();
		req.setAttribute("rutas", rutas);
		req.getRequestDispatcher("./reserva.jsp").forward(req, res);
	}

	private void obtenerDatos(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		List<Ruta> rutas = rutaService.obtenerRutas();
		List<Reserva> reservas = reservaService.obtenerReservas();

		req.setAttribute("usuarios", usuarios);
		req.setAttribute("rutas", rutas);
		req.setAttribute("reservas", reservas);
		req.getRequestDispatcher("./administrador.jsp").forward(req, res);
	}

	private void obtenerDatosUsuario(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		int idUsuario = Integer.parseInt((String) session.getAttribute("id"));
		List<ReservaRuta> reservas = reservaService.obtenerReservasConRutaPorUsuario(idUsuario);
		Usuario usuario = usuarioService.getUserByUserId(String.valueOf(idUsuario));

		req.setAttribute("usuario", usuario);
		req.setAttribute("reservas", reservas);
		req.getRequestDispatcher("./perfil.jsp").forward(req, res);
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
