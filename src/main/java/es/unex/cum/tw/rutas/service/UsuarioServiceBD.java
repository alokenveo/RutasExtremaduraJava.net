package es.unex.cum.tw.rutas.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unex.cum.tw.rutas.conexion.Conexion;
import es.unex.cum.tw.rutas.model.Usuario;

public class UsuarioServiceBD implements UsuarioService {

	@Override
	public Usuario autenticar(String username, String password) {
		Usuario user = getUserByUsername(username);
		if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public Usuario getUserByUsername(String username) {
		Usuario user = null;
		ResultSet resultados = null;
		try {
			String query = "SELECT * FROM usuarios where username=?";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			sentencia.setString(1, username);
			resultados = sentencia.executeQuery();

			if (resultados.next() == false) {
				return null;
			} else {
				user = new Usuario(Integer.parseInt(resultados.getString("id_usuario")), resultados.getString("nombre"),
						resultados.getString("apellidos"), resultados.getString("email"),
						resultados.getString("username"), resultados.getString("password"),
						resultados.getDate("fecha_nacimiento"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, "No se pudo cerrar el Resulset", ex);
				}
			}
		}
		return user;
	}

	@Override
	public Usuario getUserByUserId(String userId) {
		Usuario user = null;
		ResultSet resultados = null;
		try {
			String query = "SELECT * FROM usuarios where id_usuario=?";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			sentencia.setString(1, userId);
			resultados = sentencia.executeQuery();
			if (resultados.next() == false) {
				return null;
			} else {
				user = new Usuario(Integer.parseInt(resultados.getString("id_usuario")), resultados.getString("nombre"),
						resultados.getString("apellidos"), resultados.getString("email"),
						resultados.getString("username"), resultados.getString("password"),
						resultados.getDate("fecha_nacimiento"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, "No se pudo cerrar el Resulset", ex);
				}
			}
		}
		return user;
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		Usuario user = null;
		ResultSet resultados = null;
		List<Usuario> l = new ArrayList<Usuario>();
		try {
			String query = "SELECT * FROM usuarios";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				user = new Usuario(Integer.parseInt(resultados.getString("id_usuario")), resultados.getString("nombre"),
						resultados.getString("apellidos"), resultados.getString("email"),
						resultados.getString("username"), resultados.getString("password"),
						resultados.getDate("fecha_nacimiento"));
				l.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, "No se pudo cerrar el Resulset", ex);
				}
			}
		}
		return l;
	}

	@Override
	public boolean registrarUsuario(Usuario user) {
		ResultSet resultados = null;
		if (getUserByUsername(user.getUsername()) != null)
			return false;

		try {

			String query = "INSERT INTO usuarios(nombre,apellidos,email,username,password,fecha_nacimiento) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setString(1, user.getNombre());
				sentencia.setString(2, user.getApellidos());
				sentencia.setString(3, user.getEmail());
				sentencia.setString(4, user.getUsername());
				sentencia.setString(5, user.getPassword());
				sentencia.setDate(6, user.getFechaNacimiento());
				sentencia.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, "No se pudo cerrar recursos", ex);
				}
			}
		}
		return true;
	}

	@Override
	public boolean eliminarUsuario(String idUsuario) {
		try {
			String query = "DELETE FROM usuarios WHERE id_usuario=?";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setString(1, idUsuario);
				sentencia.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Usuario getUserByUserEmail(String email) {
		Usuario user = null;
		ResultSet resultados = null;
		try {
			String query = "SELECT * FROM usuarios where email=?";
			PreparedStatement sentencia = (PreparedStatement) Conexion.openConnection().prepareStatement(query);
			sentencia.setString(1, email);
			resultados = sentencia.executeQuery();

			if (resultados.next() == false) {
				return null;
			} else {
				user = new Usuario(Integer.parseInt(resultados.getString("id_usuario")), resultados.getString("nombre"),
						resultados.getString("apellidos"), resultados.getString("email"),
						resultados.getString("username"), resultados.getString("password"),
						resultados.getDate("fecha_nacimiento"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, "No se pudo cerrar el Resulset", ex);
				}
			}
		}
		return user;
	}

}
