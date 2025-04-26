package es.unex.cum.tw.rutas.service;

import java.sql.SQLException;

public interface ValoracionService {
	public void crearOActualizarValoracion(int idUsuario, int idRuta, int valoracion) throws SQLException;
	public Integer obtenerValoracionUsuario(int idUsuario, int idRuta);
}
