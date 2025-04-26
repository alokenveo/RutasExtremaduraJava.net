package es.unex.cum.tw.rutas.service;

import java.util.List;

import es.unex.cum.tw.rutas.model.Ruta;

public interface RutaService {

	public List<Ruta> obtenerRutas();

	public boolean crearRuta(Ruta ruta);

	public Ruta obtenerRutaPorId(String idRuta);

	public List<Ruta> obtenerUltimasRutas(int num);

	public List<Ruta> obtenerRutasPorDificultad();

	public List<Ruta> obtenerRutasPorDistancia();

	public double obtenerValoracionMedia(int idRuta);

}
