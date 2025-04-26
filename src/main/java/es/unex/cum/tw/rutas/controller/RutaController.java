package es.unex.cum.tw.rutas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/RutaController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class RutaController extends HttpServlet {

	private UsuarioService usuarioService = new UsuarioServiceBD();
	private RutaService rutaService = new RutaServiceBD();
	private ReservaService reservaService = new ReservaServiceBD();

	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("action");
		if (accion.equals("RutaAlta")) {
			registrarRuta(req, res);
		} else if (accion.equals("DeleteRuta")) {
			eliminarRuta(req, res);
		} else if (accion.equals("VerRutas")) {
			mostrarRutas(req, res);
		} else if (accion.equals("VerRuta")) {
			mostrarRuta(req, res);
		} else {
		}
	}

	public void registrarRuta(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String enlace = req.getParameter("enlace");
		Integer maximo = Integer.parseInt(req.getParameter("maximoUsuario"));
		Integer dificultad = Integer.parseInt(req.getParameter("dificultad"));
		Integer metros = Integer.parseInt(req.getParameter("metros"));

		List<String> fotos = new ArrayList<>();

		// Ruta en el servidor (directorio de despliegue)
		String uploadPath = req.getServletContext().getRealPath("/img/rutas/");
		System.out.println("Ruta de guardado (servidor): " + uploadPath);

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			System.out.println("Directorio no existe, creando: " + uploadPath);
			uploadDir.mkdirs();
		}

		// Procesar las imágenes
		for (Part part : req.getParts()) {
			if (part.getName().equals("fotos") && part.getSubmittedFileName() != null
					&& !part.getSubmittedFileName().isEmpty()) {
				String nombreArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				String rutaGuardar = uploadPath + File.separator + nombreArchivo;
				System.out.println("Guardando archivo en (servidor): " + rutaGuardar);
				try {
					part.write(rutaGuardar);
					fotos.add("img/rutas/" + nombreArchivo); // Guardar ruta relativa
				} catch (IOException e) {
					e.printStackTrace();
					req.setAttribute("error", "Error al guardar las imágenes: " + e.getMessage());
					req.getRequestDispatcher("/administrador.jsp").forward(req, res);
					return;
				}
			}
		}

		// Crear la ruta
		Ruta ruta = new Ruta(nombre, descripcion, enlace, maximo, dificultad, metros, fotos);

		if (rutaService.crearRuta(ruta)) {
			obtenerDatos(req, res);
		} else {
			req.setAttribute("error", "No se pudo crear la ruta");
			req.getRequestDispatcher("/administrador.jsp").forward(req, res);
		}
	}

	public void mostrarRutas(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Ruta> rutas = rutaService.obtenerRutas();
		req.setAttribute("rutas", rutas);
		req.getRequestDispatcher("./rutas.jsp").forward(req, res);
	}

	public void mostrarRuta(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idRuta = req.getParameter("idRuta");
		Ruta ruta = rutaService.obtenerRutaPorId(idRuta);
		if (ruta != null) {
			req.setAttribute("ruta", ruta);
			req.getRequestDispatcher("./info_ruta.jsp").forward(req, res);
		} else {
			res.sendRedirect("rutas.jsp");
		}
	}

	public void eliminarRuta(HttpServletRequest req, HttpServletResponse res) {
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
