package es.unex.cum.tw.rutas.controller;

import java.io.IOException;
import java.util.List;

import es.unex.cum.tw.rutas.model.Ruta;
import es.unex.cum.tw.rutas.service.RutaService;
import es.unex.cum.tw.rutas.service.RutaServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/", "/home"})
public class HomeController extends HttpServlet {
    
    private RutaService rutaService = new RutaServiceBD();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    }
    
    public void mostrarInicio(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // Obtener las listas de rutas
            List<Ruta> ultimasRutas = rutaService.obtenerUltimasRutas(5);
            List<Ruta> rutasPorDificultad = rutaService.obtenerRutasPorDificultad();
            List<Ruta> rutasPorDistancia = rutaService.obtenerRutasPorDistancia();

            // Calcular valoraciones para cada lista
            for (Ruta ruta : ultimasRutas) {
                double valoracion = rutaService.obtenerValoracionMedia(ruta.getIdRuta());
                req.setAttribute("valoracion_" + ruta.getIdRuta(), valoracion);
            }
            for (Ruta ruta : rutasPorDificultad) {
                double valoracion = rutaService.obtenerValoracionMedia(ruta.getIdRuta());
                req.setAttribute("valoracion_" + ruta.getIdRuta(), valoracion);
            }
            for (Ruta ruta : rutasPorDistancia) {
                double valoracion = rutaService.obtenerValoracionMedia(ruta.getIdRuta());
                req.setAttribute("valoracion_" + ruta.getIdRuta(), valoracion);
            }

            // Pasar las listas al JSP
            req.setAttribute("ultimasRutas", ultimasRutas);
            req.setAttribute("rutasPorDificultad", rutasPorDificultad);
            req.setAttribute("rutasPorDistancia", rutasPorDistancia);

            // Redirigir a index.jsp
            req.getRequestDispatcher("./index.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al cargar la página principal: " + e.getMessage());
            req.getRequestDispatcher("./error.jsp").forward(req, res);
        }
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        mostrarInicio(req, res);
    }

    @Override
    public void destroy() {
    }
}
