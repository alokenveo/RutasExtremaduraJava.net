package es.unex.cum.tw.rutas.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unex.cum.tw.rutas.conexion.Conexion;

public class ValoracionServiceBD implements ValoracionService {

	@Override
	public void crearOActualizarValoracion(int idUsuario, int idRuta, int valoracion) throws SQLException {
		Connection conn = null;
		PreparedStatement checkStmt = null;
		PreparedStatement upsertStmt = null;
		ResultSet rs = null;

		try {
			conn = Conexion.openConnection();
			conn.setAutoCommit(false); // Iniciar transacción

			// Verificar si el usuario ya ha valorado esta ruta
			String checkQuery = "SELECT idValoracion FROM valoraciones WHERE idUsuario = ? AND idRuta = ?";
			checkStmt = conn.prepareStatement(checkQuery);
			checkStmt.setInt(1, idUsuario);
			checkStmt.setInt(2, idRuta);
			rs = checkStmt.executeQuery();

			if (rs.next()) {
				// Si ya existe una valoración, actualizarla
				String updateQuery = "UPDATE valoraciones SET valoracion = ?, fecha = CURRENT_TIMESTAMP WHERE idUsuario = ? AND idRuta = ?";
				upsertStmt = conn.prepareStatement(updateQuery);
				upsertStmt.setInt(1, valoracion);
				upsertStmt.setInt(2, idUsuario);
				upsertStmt.setInt(3, idRuta);
			} else {
				// Si no existe, insertar una nueva valoración
				String insertQuery = "INSERT INTO valoraciones (idUsuario, idRuta, valoracion, comentario, fecha) VALUES (?, ?, ?, NULL, CURRENT_TIMESTAMP)";
				upsertStmt = conn.prepareStatement(insertQuery);
				upsertStmt.setInt(1, idUsuario);
				upsertStmt.setInt(2, idRuta);
				upsertStmt.setInt(3, valoracion);
			}

			upsertStmt.executeUpdate();
			conn.commit(); // Confirmar transacción

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback(); // Revertir transacción en caso de error
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (checkStmt != null) {
				try {
					checkStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (upsertStmt != null) {
				try {
					upsertStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Integer obtenerValoracionUsuario(int idUsuario, int idRuta) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conexion.openConnection();
			String query = "SELECT valoracion FROM valoraciones WHERE idUsuario = ? AND idRuta = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, idUsuario);
			stmt.setInt(2, idRuta);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("valoracion");
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
