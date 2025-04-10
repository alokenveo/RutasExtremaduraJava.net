package es.unex.cum.tw.rutas.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unex.cum.tw.rutas.conexion.Conexion;
import es.unex.cum.tw.rutas.model.Ruta;

public class RutaServiceBD implements RutaService {

	@Override
	public List<Ruta> obtenerRutas() {
		Ruta ruta = null;
		ResultSet resultados = null;
		List<Ruta> l = new ArrayList<Ruta>();

		try {
			String query = "SELECT * FROM rutas";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();
			
			while (resultados.next()) {
				ruta = new Ruta(Integer.parseInt(resultados.getString("idRuta")), resultados.getString("nombre"),
						resultados.getString("descripcion"), resultados.getString("enlace"),
						resultados.getDate("fechaIncorporacion"), resultados.getInt("maximoUsuario"),
						resultados.getInt("dificultad"), resultados.getInt("metros"));
				l.add(ruta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudo cerrar el Resulset", ex);
				}
			}
		}

		return l;
	}

}
