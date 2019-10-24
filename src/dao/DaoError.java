/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexiones.Conexion;

public class DaoError {

	public DaoError() {}
	
	public String getCodigo(SQLException e) {
		String codigoError = e.getMessage();
		codigoError = codigoError.substring(codigoError.indexOf('-')+1, codigoError.indexOf(':'));
		return codigoError;
	}
	
	public String getMessageDisparador(SQLException e) {
		String codigoError = e.getMessage();
		int principio = codigoError.indexOf(':');
		int finalCadena = codigoError.indexOf("ORA", principio);
		codigoError = codigoError.substring(principio+1, finalCadena);
		return codigoError;
	}
	
	public String getMessage(SQLException e) throws SQLException, Exception {
		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mensajeError = e.getMessage();
		mensajeError = mensajeError.substring(mensajeError.indexOf('(')+1, mensajeError.indexOf(')'));
		try {
			conex = new Conexion();
			con = conex.getConexion();
			ps = con.prepareStatement("SELECT MENSAJE FROM ERROR WHERE RESTRICCION=?");
			ps.setString(1, mensajeError);
			rs = ps.executeQuery();
			if(rs.next()) {
				mensajeError = rs.getString(1);
			}
		}catch(SQLException ee) {
			throw ee;
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(con!=null) {
				con.close();
			}
		}
		return mensajeError;
	}

}
