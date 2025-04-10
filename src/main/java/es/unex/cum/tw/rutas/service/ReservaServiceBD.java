package es.unex.cum.tw.rutas.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unex.cum.tw.rutas.conexion.Conexion;
import es.unex.cum.tw.rutas.model.Reserva;
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
				String serviciosExtrasJson = resultados.getString("serviciosExtras");
				List<String> serviciosExtras = new ArrayList<>();
				if (serviciosExtrasJson != null && !serviciosExtrasJson.isEmpty()) {
					try {
						serviciosExtras = Arrays.asList(
								serviciosExtrasJson.replace("[", "").replace("]", "").replace("\"", "").split(","));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				reserva = new Reserva(Integer.parseInt(resultados.getString("idReserva")),
						resultados.getInt("idUsuario"), resultados.getInt("idRuta"), resultados.getDate("fecha"),
						resultados.getInt("personas"), resultados.getString("horario"),
						resultados.getString("puntoEncuentro"), resultados.getString("alergias"),
						resultados.getString("comentarios"), serviciosExtras);
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

}
