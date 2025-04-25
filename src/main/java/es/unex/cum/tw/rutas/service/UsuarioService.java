package es.unex.cum.tw.rutas.service;

import java.util.List;

import es.unex.cum.tw.rutas.model.Usuario;

public interface UsuarioService {
	public Usuario autenticar(String username, String password);
	public Usuario getUserByUsername(String username);
	public Usuario getUserByUserId(String userId);
	public Usuario getUserByUserEmail(String email);
	public List<Usuario> obtenerUsuarios();
	public boolean registrarUsuario(Usuario user);
	public boolean eliminarUsuario(String idUsuario);
}
