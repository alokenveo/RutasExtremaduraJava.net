package es.unex.cum.tw.rutas.model;

import java.sql.Date;

public class ReservaRuta {
    private int idReserva;
    private String nombreRuta;
    private int dificultad;
    private int metros;
    private Date fechaReserva;
    private String puntoEncuentro;
    private int personas;

    // Constructor
    public ReservaRuta(int idReserva, String nombreRuta, int dificultad, int metros, Date fechaReserva,
                       String puntoEncuentro, int personas) {
        this.idReserva = idReserva;
        this.nombreRuta = nombreRuta;
        this.dificultad = dificultad;
        this.metros = metros;
        this.fechaReserva = fechaReserva;
        this.puntoEncuentro = puntoEncuentro;
        this.personas = personas;
    }

    // Getters y setters...
	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public String getNombreRuta() {
		return nombreRuta;
	}

	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public int getMetros() {
		return metros;
	}

	public void setMetros(int metros) {
		this.metros = metros;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getPuntoEncuentro() {
		return puntoEncuentro;
	}

	public void setPuntoEncuentro(String puntoEncuentro) {
		this.puntoEncuentro = puntoEncuentro;
	}

	public int getPersonas() {
		return personas;
	}

	public void setPersonas(int personas) {
		this.personas = personas;
	}
    
}
