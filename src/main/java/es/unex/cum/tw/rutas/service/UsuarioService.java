package es.unex.cum.tw.rutas.service;

import java.util.List;

import es.unex.cum.tw.rutas.model.Usuario;

public interface UsuarioService {
	public boolean authenticate(String username, String password);
	public Usuario getUserByUsername(String username);
	public Usuario getUserByUserId(String userId);
	public List<Usuario> getListOfUsers();
	public boolean register(Usuario user);
}
