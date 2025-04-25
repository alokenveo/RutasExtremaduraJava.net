package es.unex.cum.tw.rutas.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unex.cum.tw.rutas.conexion.Conexion;
import es.unex.cum.tw.rutas.model.Reserva;
import es.unex.cum.tw.rutas.model.ReservaRuta;
import es.unex.cum.tw.rutas.model.Ruta;

public class ReservaServiceBD implements ReservaService {

	@Override
	public List<Reserva> obtenerReservas() {
		Reserva reserva = null;
		ResultSet resultados = null;
		List<Reserva> l = new ArrayList<Reserva>();

		try {
			String query = "SELECT * FROM reservas";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				reserva = new Reserva(Integer.parseInt(resultados.getString("idReserva")),
						resultados.getInt("idUsuario"), resultados.getInt("idRuta"), resultados.getDate("fecha"),
						resultados.getInt("personas"), resultados.getString("horario"),
						resultados.getString("puntoEncuentro"), resultados.getString("alergias"),
						resultados.getString("comentarios"), resultados.getString("serviciosExtras"));
				l.add(reserva);
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

	@Override
	public boolean crearReserva(Reserva reserva) {
		PreparedStatement sentencia = null;
		try {
			String query = "INSERT INTO reservas (idUsuario, idRuta, fecha, horario, puntoEncuentro, personas, alergias, comentarios, serviciosExtras) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setInt(1, reserva.getIdUsuario());
				sentencia.setInt(2, reserva.getIdRuta());
				sentencia.setDate(3, reserva.getFecha());
				sentencia.setString(4, reserva.getHorario());
				sentencia.setString(5, reserva.getPuntoEncuentro());
				sentencia.setInt(6, reserva.getPersonas());
				sentencia.setString(7, reserva.getAlergias() != null ? reserva.getAlergias() : null);
				sentencia.setString(8, reserva.getComentarios() != null ? reserva.getComentarios() : null);
				sentencia.setString(9, reserva.getServiciosExtras());
				sentencia.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException ex) {
					Logger.getLogger(ReservaService.class.getName()).log(Level.SEVERE, "No se pudo cerrar la sentencia",
							ex);
				}
			}
		}
	}

	@Override
	public List<ReservaRuta> obtenerReservasConRuta() {
		List<ReservaRuta> lista = new ArrayList<ReservaRuta>();
		ResultSet resultados = null;
		ReservaRuta reserva = null;

		try {
			String query = "SELECT rsv.idReserva, rt.nombre, rt.dificultad, rt.metros, "
					+ "rsv.fecha, rsv.puntoEncuentro, rsv.personas "
					+ "FROM reservas rsv JOIN rutas rt ON rsv.idRuta = rt.idRuta";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				reserva = new ReservaRuta(resultados.getInt("idReserva"), resultados.getString("nombre"),
						resultados.getInt("dificultad"), resultados.getInt("metros"), resultados.getDate("fecha"),
						resultados.getString("puntoEncuentro"), resultados.getInt("personas"));
				lista.add(reserva);
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

		return lista;
	}

	@Override
	public List<ReservaRuta> obtenerReservasConRutaPorUsuario(int idUsuario) {
		List<ReservaRuta> lista = new ArrayList<>();
		ResultSet resultados = null;
		ReservaRuta reserva = null;

		try {
			String query = "SELECT rsv.idReserva, rt.nombre, rt.dificultad, rt.metros, "
					+ "rsv.fecha, rsv.puntoEncuentro, rsv.personas " + "FROM reservas rsv "
					+ "JOIN rutas rt ON rsv.idRuta = rt.idRuta " + "WHERE rsv.idUsuario = ?";

			PreparedStatement sentencia = Conexion.openConnection().prepareStatement(query);
			sentencia.setInt(1, idUsuario);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				reserva = new ReservaRuta(resultados.getInt("idReserva"), resultados.getString("nombre"),
						resultados.getInt("dificultad"), resultados.getInt("metros"), resultados.getDate("fecha"),
						resultados.getString("puntoEncuentro"), resultados.getInt("personas"));
				lista.add(reserva);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, "No se pudo cerrar el ResultSet", ex);
				}
			}
		}

		return lista;
	}

	@Override
	public boolean eliminarReserva(String idReserva) {
		try {
			String query = "DELETE FROM reservas WHERE idReserva=?";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setString(1, idReserva);
				sentencia.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
