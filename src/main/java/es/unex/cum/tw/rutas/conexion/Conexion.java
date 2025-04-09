package es.unex.cum.tw.rutas.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class Conexion {

	private static Connection conexion=null;

	private static Connection buildConnection() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource servicioConexiones = (DataSource) envContext.lookup("jdbc/testdb");
			conexion = servicioConexiones.getConnection();
			return conexion;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}		
	}

	public static void Connection() throws SQLException {
		if (conexion==null) buildConnection();        
    }
	public static Connection openConnection() throws SQLException {
		if (conexion==null) buildConnection();
        return conexion;
    }
}
