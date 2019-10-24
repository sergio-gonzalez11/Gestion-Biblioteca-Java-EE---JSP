/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Autor;
import entidades.Ejemplar;
import entidades.Libro;


public class DaoEjemplar {

	public DaoEjemplar() {
	 }

	
	// Método de Fernando
	public ArrayList <Ejemplar>buscarEjemplar (Libro li) throws SQLException, Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Ejemplar ej = null; 
		
		ArrayList <Ejemplar> listaEjemplar = new ArrayList<Ejemplar>();

		try {
			conex = new Conexion();
			con = conex.getConexion();

			String sql = "SELECT * FROM EJEMPLAR WHERE ISBN=? AND BAJA='N'AND IDEJEMPLAR NOT IN (SELECT IDEJEMPLAR FROM PRESTAMO)";

			ps = con.prepareStatement(sql);
			ps.setString(1, li.getISBN());

			rs = ps.executeQuery();

			while(rs.next()) {
				ej = new Ejemplar();

				ej.setIDEJEMPLAR(rs.getLong("IDEJEMPLAR"));
				ej.setISBN(rs.getString("ISBN"));
				
				listaEjemplar.add(ej);
				
			}

			for(Ejemplar e: listaEjemplar){
				System.out.println(e.toString());
			}

		}catch (SQLException e) {
			System.out.println(e.getMessage() + "No esta dado de baja");
		}catch(Exception ee) {
			throw ee;
		}finally {
			if(ps!=null) {
				ps.close();
			}
			if(rs!=null) {
				rs.close();
			}
			if(con!=null) {
				con.close();
			}
		}
		return listaEjemplar;
		
	}
	
	
	
	
	
	// Método controlador//
		public boolean eliminarEjemplar(Ejemplar e) throws SQLException, Exception {
			
			Conexion conex = null;
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				conex = new Conexion();
				con = conex.getConexion();

				String sql = "UPDATE EJEMPLAR SET BAJA='S' WHERE IDEJEMPLAR=?";
				
				ps = con.prepareStatement(sql);
				ps.setLong(1, e.getIDEJEMPLAR());
				
				if(ps.executeUpdate()==1) {
					System.out.println("Operacion realizada correctamente");
					return true;
				}
			}catch(SQLException se) {
				throw se;
			}catch(Exception ee) {
				throw ee;
			}finally {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}
			return false;
		}
		
		
		/*
		public void altaEjemplar(Ejemplar e, Long nEjemplares) throws SQLException, Exception{
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Long idEjemplar = (long) 0;
			try {
				con = new Conexion().getConexion();
				ps = con.prepareStatement("SELECT E.IDEJEMPLAR\r\n" + 
															  "FROM EJEMPLAR E\r\n" + 
															  "WHERE E.IDEJEMPLAR = (SELECT MAX(IDEJEMPLAR)\r\n" + 
															  					   "                      FROM EJEMPLAR)\r\n" + 
															  "FOR UPDATE");
				rs = ps.executeQuery();
				if(rs.next()) {
					idEjemplar = rs.getLong(1);
				}
				ps.close();
				con.setAutoCommit(false);
				for(long i =0; i < nEjemplares ; i++) {
					idEjemplar++;
					ps = con.prepareStatement("INSERT INTO EJEMPLAR VALUES (?,?,'N')");
					ps.setLong(1, idEjemplar);
					ps.setString(2, e.getISBN());
					ps.execute();
				}
				con.commit();
			}catch(SQLException se) {
				if( con != null) {
					con.rollback();
				}
				throw se;
			}catch(Exception ee) {
				throw ee;
			}finally {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}
		}
		*/
		
		
}

