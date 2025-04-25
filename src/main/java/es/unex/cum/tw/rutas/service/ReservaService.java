package es.unex.cum.tw.rutas.service;

import java.util.List;

import es.unex.cum.tw.rutas.model.Reserva;
import es.unex.cum.tw.rutas.model.ReservaRuta;

public interface ReservaService {

	public List<Reserva> obtenerReservas();
	public boolean crearReserva(Reserva reserva);
	public List<ReservaRuta> obtenerReservasConRuta();
	public List<ReservaRuta> obtenerReservasConRutaPorUsuario(int idUsuario);
	public boolean eliminarReserva(String idReserva);
}
