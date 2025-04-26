package es.unex.cum.tw.rutas.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unex.cum.tw.rutas.conexion.Conexion;
import es.unex.cum.tw.rutas.model.Ruta;

public class RutaServiceBD implements RutaService {

	@Override
	public List<Ruta> obtenerRutas() {
		List<Ruta> rutas = new ArrayList<>();
		ResultSet resultados = null;
		Connection conn = null;
		PreparedStatement sentencia1 = null;
		PreparedStatement sentencia2 = null;

		try {
			conn = Conexion.openConnection();

			// Obtener las rutas
			String query1 = "SELECT * FROM rutas";
			sentencia1 = conn.prepareStatement(query1);
			resultados = sentencia1.executeQuery();

			while (resultados.next()) {
				Ruta ruta = new Ruta(resultados.getInt("idRuta"), resultados.getString("nombre"),
						resultados.getString("descripcion"), resultados.getString("enlace"),
						resultados.getDate("fechaIncorporacion"), resultados.getInt("maximoUsuario"),
						resultados.getInt("dificultad"), resultados.getInt("metros"));

				String query2 = "SELECT pathImagen FROM fotos_ruta WHERE idRuta=?";
				sentencia2 = conn.prepareStatement(query2);
				sentencia2.setInt(1, ruta.getIdRuta());
				ResultSet fotosResultSet = sentencia2.executeQuery();

				List<String> fotosRuta = new ArrayList<>();
				while (fotosResultSet.next()) {
					fotosRuta.add(fotosResultSet.getString("pathImagen"));
				}

				ruta.setFotos(fotosRuta);
				rutas.add(ruta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultados != null) {
					resultados.close();
				}
				if (sentencia1 != null) {
					sentencia1.close();
				}
				if (sentencia2 != null) {
					sentencia2.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudieron cerrar los recursos", ex);
			}
		}

		return rutas;
	}

	@Override
	public boolean crearRuta(Ruta ruta) {
		Connection conn = null;
		PreparedStatement sentencia = null;
		PreparedStatement sentencia2 = null;
		ResultSet rs = null;

		try {
			conn = Conexion.openConnection();

			// Insertamos la ruta principal
			String query = "INSERT INTO rutas(nombre, descripcion, enlace, maximoUsuario, dificultad, metros) VALUES (?, ?, ?, ?, ?, ?)";
			sentencia = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, ruta.getNombre());
			sentencia.setString(2, ruta.getDescripcion());
			sentencia.setString(3, ruta.getEnlace());
			sentencia.setInt(4, ruta.getMaximoUsuario());
			sentencia.setInt(5, ruta.getDificultad());
			sentencia.setInt(6, ruta.getMetros());
			sentencia.executeUpdate();

			// Recuperamos el idRuta generado automáticamente
			rs = sentencia.getGeneratedKeys();
			int idRutaGenerado = -1;
			if (rs.next()) {
				idRutaGenerado = rs.getInt(1);
			} else {
				throw new SQLException("No se pudo obtener el id generado de la ruta.");
			}

			// Insertamos las fotos asociadas
			String query2 = "INSERT INTO fotos_ruta(idRuta, pathImagen) VALUES (?, ?)";
			for (String path : ruta.getFotos()) {
				sentencia2 = conn.prepareStatement(query2);
				sentencia2.setInt(1, idRutaGenerado);
				sentencia2.setString(2, path);
				sentencia2.executeUpdate();
				sentencia2.close();
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException ex) {
				Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudo cerrar recursos", ex);
			}
		}
	}

	@Override
	public Ruta obtenerRutaPorId(String idRuta) {
		Ruta ruta = null;
		ResultSet resultados = null;
		Connection conn = null;
		PreparedStatement sentencia = null;
		PreparedStatement sentencia2 = null;

		try {
			conn = Conexion.openConnection();

			String query = "SELECT * FROM rutas WHERE idRuta=?";
			sentencia = conn.prepareStatement(query);
			sentencia.setString(1, idRuta);
			resultados = sentencia.executeQuery();

			if (!resultados.next()) {
				return null;
			}

			ruta = new Ruta(resultados.getInt("idRuta"), resultados.getString("nombre"),
					resultados.getString("descripcion"), resultados.getString("enlace"),
					resultados.getDate("fechaIncorporacion"), resultados.getInt("maximoUsuario"),
					resultados.getInt("dificultad"), resultados.getInt("metros"));

			String query2 = "SELECT pathImagen FROM fotos_ruta WHERE idRuta=?";
			sentencia2 = conn.prepareStatement(query2);
			sentencia2.setString(1, idRuta);
			resultados = sentencia2.executeQuery();

			List<String> fotosRuta = new ArrayList<>();
			while (resultados.next()) {
				fotosRuta.add(resultados.getString("pathImagen"));
			}

			ruta.setFotos(fotosRuta);
			return ruta;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultados != null)
					resultados.close();
				if (sentencia != null)
					sentencia.close();
				if (sentencia2 != null)
					sentencia2.close();
			} catch (SQLException ex) {
				Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudieron cerrar los recursos", ex);
			}
		}

		return ruta;
	}

	@Override
	public List<Ruta> obtenerUltimasRutas(int num) {
		List<Ruta> rutas = new ArrayList<>();
		ResultSet resultados = null;
		Connection conn = null;
		PreparedStatement sentencia1 = null;
		PreparedStatement sentencia2 = null;

		try {
			conn = Conexion.openConnection();

			// Obtener las rutas
			String query1 = "SELECT * FROM rutas ORDER BY fechaIncorporacion DESC LIMIT ?";
			sentencia1 = conn.prepareStatement(query1);
			sentencia1.setInt(1, num);
			resultados = sentencia1.executeQuery();

			while (resultados.next()) {
				Ruta ruta = new Ruta(resultados.getInt("idRuta"), resultados.getString("nombre"),
						resultados.getString("descripcion"), resultados.getString("enlace"),
						resultados.getDate("fechaIncorporacion"), resultados.getInt("maximoUsuario"),
						resultados.getInt("dificultad"), resultados.getInt("metros"));

				String query2 = "SELECT pathImagen FROM fotos_ruta WHERE idRuta=?";
				sentencia2 = conn.prepareStatement(query2);
				sentencia2.setInt(1, ruta.getIdRuta());
				ResultSet fotosResultSet = sentencia2.executeQuery();

				List<String> fotosRuta = new ArrayList<>();
				while (fotosResultSet.next()) {
					fotosRuta.add(fotosResultSet.getString("pathImagen"));
				}

				ruta.setFotos(fotosRuta);
				rutas.add(ruta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultados != null) {
					resultados.close();
				}
				if (sentencia1 != null) {
					sentencia1.close();
				}
				if (sentencia2 != null) {
					sentencia2.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudieron cerrar los recursos", ex);
			}
		}

		return rutas;
	}

	@Override
	public List<Ruta> obtenerRutasPorDificultad() {
		List<Ruta> rutas = new ArrayList<>();
		ResultSet resultados = null;
		Connection conn = null;
		PreparedStatement sentencia1 = null;
		PreparedStatement sentencia2 = null;

		try {
			conn = Conexion.openConnection();

			// Obtener las rutas
			String query1 = "SELECT * FROM rutas ORDER BY dificultad DESC";
			sentencia1 = conn.prepareStatement(query1);
			resultados = sentencia1.executeQuery();

			while (resultados.next()) {
				Ruta ruta = new Ruta(resultados.getInt("idRuta"), resultados.getString("nombre"),
						resultados.getString("descripcion"), resultados.getString("enlace"),
						resultados.getDate("fechaIncorporacion"), resultados.getInt("maximoUsuario"),
						resultados.getInt("dificultad"), resultados.getInt("metros"));

				String query2 = "SELECT pathImagen FROM fotos_ruta WHERE idRuta=?";
				sentencia2 = conn.prepareStatement(query2);
				sentencia2.setInt(1, ruta.getIdRuta());
				ResultSet fotosResultSet = sentencia2.executeQuery();

				List<String> fotosRuta = new ArrayList<>();
				while (fotosResultSet.next()) {
					fotosRuta.add(fotosResultSet.getString("pathImagen"));
				}

				ruta.setFotos(fotosRuta);
				rutas.add(ruta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultados != null) {
					resultados.close();
				}
				if (sentencia1 != null) {
					sentencia1.close();
				}
				if (sentencia2 != null) {
					sentencia2.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudieron cerrar los recursos", ex);
			}
		}

		return rutas;
	}

	@Override
	public List<Ruta> obtenerRutasPorDistancia() {
		List<Ruta> rutas = new ArrayList<>();
		ResultSet resultados = null;
		Connection conn = null;
		PreparedStatement sentencia1 = null;
		PreparedStatement sentencia2 = null;

		try {
			conn = Conexion.openConnection();

			// TODO
			String query1 = "SELECT * FROM rutas ORDER BY metros DESC";
			sentencia1 = conn.prepareStatement(query1);
			resultados = sentencia1.executeQuery();

			while (resultados.next()) {
				Ruta ruta = new Ruta(resultados.getInt("idRuta"), resultados.getString("nombre"),
						resultados.getString("descripcion"), resultados.getString("enlace"),
						resultados.getDate("fechaIncorporacion"), resultados.getInt("maximoUsuario"),
						resultados.getInt("dificultad"), resultados.getInt("metros"));

				String query2 = "SELECT pathImagen FROM fotos_ruta WHERE idRuta=?";
				sentencia2 = conn.prepareStatement(query2);
				sentencia2.setInt(1, ruta.getIdRuta());
				ResultSet fotosResultSet = sentencia2.executeQuery();

				List<String> fotosRuta = new ArrayList<>();
				while (fotosResultSet.next()) {
					fotosRuta.add(fotosResultSet.getString("pathImagen"));
				}

				ruta.setFotos(fotosRuta);
				rutas.add(ruta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultados != null) {
					resultados.close();
				}
				if (sentencia1 != null) {
					sentencia1.close();
				}
				if (sentencia2 != null) {
					sentencia2.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudieron cerrar los recursos", ex);
			}
		}

		return rutas;
	}

	@Override
	public double obtenerValoracionMedia(int idRuta) {
		PreparedStatement sentencia = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = Conexion.openConnection();
			String query = "SELECT AVG(valoracion) as media FROM valoraciones WHERE idRuta = ?";
			sentencia = conn.prepareStatement(query);
			sentencia.setInt(1, idRuta);
			rs = sentencia.executeQuery();
			if (rs.next()) {
				return rs.getDouble("media");
			}
			return 0.0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0.0;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
