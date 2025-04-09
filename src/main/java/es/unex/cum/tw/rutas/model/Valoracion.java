package es.unex.cum.tw.rutas.model;

public class Valoracion {
    private int idValoracion;
    private int idUsuario;
    private int idRuta;
    private int valoracion; // 1 a 5
    private String comentario;

    public Valoracion() {}

    public Valoracion(int idValoracion, int idUsuario, int idRuta,
                      int valoracion, String comentario) {
        this.idValoracion = idValoracion;
        this.idUsuario = idUsuario;
        this.idRuta = idRuta;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

	public int getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
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

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
