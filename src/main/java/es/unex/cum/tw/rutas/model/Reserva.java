package es.unex.cum.tw.rutas.model;

import java.sql.Date;
import java.util.List;

public class Reserva {
	private int idReserva;
	private int idUsuario;
	private int idRuta;
	private Date fecha;
	private int personas;
	private String horario;
	private String puntoEncuentro;
	private String alergias;
	private String comentarios;
	private List<String> serviciosExtras;

	public Reserva() {
	}

	public Reserva(int idReserva, int idUsuario, int idRuta, Date fecha, int personas, String horario,
			String puntoEncuentro, String alergias, String comentarios, List<String> serviciosExtras) {
		this.idReserva = idReserva;
		this.idUsuario = idUsuario;
		this.idRuta = idRuta;
		this.fecha = fecha;
		this.personas = personas;
		this.horario = horario;
		this.puntoEncuentro = puntoEncuentro;
		this.alergias = alergias;
		this.comentarios = comentarios;
		this.serviciosExtras=serviciosExtras;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPersonas() {
		return personas;
	}

	public void setPersonas(int personas) {
		this.personas = personas;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getPuntoEncuentro() {
		return puntoEncuentro;
	}

	public void setPuntoEncuentro(String puntoEncuentro) {
		this.puntoEncuentro = puntoEncuentro;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public List<String> getServiciosExtras() {
		return serviciosExtras;
	}

	public void setServiciosExtras(List<String> serviciosExtras) {
		this.serviciosExtras = serviciosExtras;
	}
	
	

}
