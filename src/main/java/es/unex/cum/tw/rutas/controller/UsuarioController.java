package es.unex.cum.tw.rutas.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import es.unex.cum.tw.rutas.model.Reserva;
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

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

	private UsuarioService usuarioService = new UsuarioServiceBD();
	private RutaService rutaService = new RutaServiceBD();
	private ReservaService reservaService = new ReservaServiceBD();

	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String accion = req.getParameter("action");
		if (accion.equals("UsuarioLogin")) {
			login(req, res);
		} else if (accion.equals("UsuarioAlta")) {
			registrar(req, res);
		} else if (accion.equals("UsuarioLogout")) {
			logout(req, res);
		} else if (accion.equals("UsuarioAdmin")) {
			obtenerDatos(req, res);
		} else if (accion.equals("DeleteUsuario")) {
			eliminarUsuario(req, res);
		} else {
			req.getRequestDispatcher("Error.jsp?error=No hay acción").forward(req, res);
		}

	}

	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String passRecibido = req.getParameter("password");
		try {

			Usuario usuario = usuarioService.autenticar(username, passRecibido);
			if (usuario != null) {
				HttpSession session = req.getSession(true);
				session.setAttribute("id", String.valueOf(usuario.getId()));
				session.setAttribute("nombre", usuario.getNombre());
				session.setAttribute("username", usuario.getUsername());
				res.sendRedirect("index.jsp");
			} else {
				res.sendRedirect("index.jsp");
				return;
			}
		} catch (Exception e2) {
			req.setAttribute("error", "Autenticacion correcta");
			req.getRequestDispatcher("./login.jsp").forward(req, res);
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		res.sendRedirect("login.jsp");
	}

	public void registrar(HttpServletRequest req, HttpServletResponse res)
			throws ParseException, ServletException, IOException {
		String n = req.getParameter("nombre");
		String apel = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String pass = req.getParameter("password");
		Date fecha = Date.valueOf(req.getParameter("fechaNacimiento"));
		Usuario usuario1 = new Usuario(n, apel, email, username, pass, fecha);

		try {
			boolean resultado = usuarioService.registrarUsuario(usuario1);
			if (resultado) {
				req.setAttribute("mensaje", "Dado de alta correctamente");
				req.getRequestDispatcher("./login.jsp").forward(req, res);
				return;
			} else {
				req.setAttribute("error", "El usuario ya existe");
				req.getRequestDispatcher("./registro.jsp").forward(req, res);
				return;
			}
		} catch (Exception e2) {
			req.setAttribute("error", "Fallo en SQL");
			req.getRequestDispatcher("./registro.jsp").forward(req, res);
		}
	}

	public void obtenerDatos(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		List<Ruta> rutas = rutaService.obtenerRutas();
		List<Reserva> reservas = reservaService.obtenerReservas();

		req.setAttribute("usuarios", usuarios);
		req.setAttribute("rutas", rutas);
		req.setAttribute("reservas", reservas);
		req.getRequestDispatcher("./administrador.jsp").forward(req, res);
	}

	public void eliminarUsuario(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idUsuario = req.getParameter("idUsuario");
		boolean borrado = usuarioService.eliminarUsuario(idUsuario);
		if(!borrado) {
			req.setAttribute("error", "No se ha podido eliminar al usuario");
		}
		
		obtenerDatos(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			processRequest(req, res);
		} catch (ServletException | IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			processRequest(req, res);
		} catch (ServletException | IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
	}

}
