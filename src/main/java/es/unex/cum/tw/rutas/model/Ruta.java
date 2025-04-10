package es.unex.cum.tw.rutas.model;

import java.sql.Date;
import java.util.List;

public class Ruta {
	private int idRuta;
	private String nombre;
	private String descripcion;
	private String enlace;
	private Date fechaIncorporacion;
	private int maximoUsuario;
	private int dificultad;
	private int metros;

	private List<String> fotos;

	public Ruta() {
	}

	public Ruta(int idRuta, String nombre, String descripcion, String enlace, Date fechaIncorporacion,
			int maximoUsuario, int dificultad, int metros, List<String> fotos) {
		this.idRuta = idRuta;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.fechaIncorporacion = fechaIncorporacion;
		this.maximoUsuario = maximoUsuario;
		this.dificultad = dificultad;
		this.metros = metros;
		this.fotos = fotos;
	}
	
	public Ruta(int idRuta, String nombre, String descripcion, String enlace, Date fechaIncorporacion,
			int maximoUsuario, int dificultad, int metros) {
		this.idRuta = idRuta;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.fechaIncorporacion = fechaIncorporacion;
		this.maximoUsuario = maximoUsuario;
		this.dificultad = dificultad;
		this.metros = metros;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public int getMaximoUsuario() {
		return maximoUsuario;
	}

	public void setMaximoUsuario(int maximoUsuario) {
		this.maximoUsuario = maximoUsuario;
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

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

}
