package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Libro;

public class DaoLibro {
	
	public DaoLibro() {
	 }

	
	public boolean insertaLibro(Libro li) throws SQLException, Exception {
		 
		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		
		 try {
			 
			 conex = new Conexion();
			 con = conex.getConexion();
			 
			 String sql = "INSERT INTO LIBRO VALUES (?,?,?)";
			 
			 pt = con.prepareStatement(sql);
			
			 pt.setString(1, li.getISBN().toUpperCase());
			 pt.setString(2, li.getTITULO().toUpperCase());
			 pt.setLong(3, li.getIDAUTOR());
			 
			 if(!pt.execute()) {
				 System.out.println("Datos introducidos correctamente!");
					return true;
				}
			 
		} catch (SQLException se) {
			throw se;
		}catch (Exception e) {
			throw e;
		}finally{
			 if(pt!=null)
				 pt.close();
			 if(con!=null)
				 con.close();
		 }
		return false;
	 }
	
	

	public ArrayList<Libro>consultarLibro (String TITULO, String ISBN, String NOMBREAUTOR)throws SQLException, Exception {
		
		Conexion conex = null;
		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			conex = new Conexion();
			con = conex.getConexion();
			 
			ps = con.prepareStatement("SELECT Z.TITULO, Z.NOMBRE, X.TOTALES, Y.PRESTAMO, (X.TOTALES-Y.PRESTAMO)DISPONIBLES, Z.ISBN\r\n" + 
									  "FROM (SELECT L.ISBN, L.TITULO, A.NOMBRE\r\n" + 
									  "      FROM LIBRO L, AUTOR A\r\n" + 
									  "      WHERE upper(ISBN) LIKE UPPER (?) AND upper(TITULO) LIKE UPPER (?) AND upper(A.NOMBRE) LIKE UPPER (?) AND L.IDAUTOR = A.IDAUTOR\r\n" + 
									  "      ORDER BY TITULO ASC)Z,(SELECT COUNT(*)TOTALES, ISBN\r\n" + 
									  "                             FROM EJEMPLAR\r\n" + 
									  "                             WHERE BAJA='N' \r\n" + 
									  "                             GROUP BY ISBN)X,(SELECT COUNT(*)PRESTAMO, E.ISBN\r\n" + 
									  "                                              FROM PRESTAMO P, EJEMPLAR E\r\n" + 
									  "                                              WHERE P.IDEJEMPLAR = E.IDEJEMPLAR GROUP BY E.ISBN)Y\r\n" + 
																	"WHERE Z.ISBN = X.ISBN AND Y.ISBN = Z.ISBN");
			
			ps.setString(1, "%" + ISBN.toUpperCase() + "%");
			ps.setString(2, "%" + TITULO.toUpperCase() + "%");
			ps.setString(3, "%" + NOMBREAUTOR.toUpperCase() + "%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Libro li = new Libro();
				
				li.setTITULO(rs.getString(1));
				li.setNOMBREAUTOR(rs.getString(2));
				li.setTOTALES(rs.getLong(3));
				li.setPRESTADOS(rs.getLong(4));
				li.setDISPONIBLES(rs.getLong(5));
				li.setISBN(rs.getString(6));
				lista.add(li);
			}
			
			for(Libro li: lista){
				System.out.println(li.toString());
			} 
			
		}catch(SQLException sqe) {
			throw sqe;
		}catch(Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
		}
		return lista;
	}
	
	
	public ArrayList<Libro>eliminarLibro (String TITULO, String ISBN, String NOMBREAUTOR)throws SQLException, Exception {
		
		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			conex = new Conexion();
			con = conex.getConexion();
			 
			ps = con.prepareStatement("SELECT Z.TITULO, Z.NOMBRE, X.TOTALES, Y.PRESTAMO, (X.TOTALES-Y.PRESTAMO)DISPONIBLES\r\n" + 
									  "FROM (SELECT L.ISBN, L.TITULO, A.NOMBRE\r\n" + 
									  "      FROM LIBRO L, AUTOR A\r\n" + 
									  "      WHERE upper(ISBN) LIKE (?) AND upper(TITULO) LIKE (?) AND upper(A.NOMBRE) LIKE (?) AND L.IDAUTOR = A.IDAUTOR\r\n" + 
									  "      ORDER BY TITULO ASC)Z,(SELECT COUNT(*)TOTALES, ISBN\r\n" + 
									  "                             FROM EJEMPLAR\r\n" + 
									  "                             WHERE BAJA='S' \r\n" + 
									  "                             GROUP BY ISBN)X,(SELECT COUNT(*)PRESTAMO, E.ISBN\r\n" + 
									  "                                              FROM PRESTAMO P, EJEMPLAR E\r\n" + 
									  "                                              WHERE P.IDEJEMPLAR = E.IDEJEMPLAR GROUP BY E.ISBN)Y\r\n" + 
																	"WHERE Z.ISBN = X.ISBN AND Y.ISBN = Z.ISBN");
			
			ps.setString(1, "%" + ISBN.toUpperCase() + "%");
			ps.setString(2, "%" + TITULO.toUpperCase() + "%");
			ps.setString(3, "%" + NOMBREAUTOR.toUpperCase() + "%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Libro li = new Libro();
				
				li.setTITULO(rs.getString(1));
				li.setNOMBREAUTOR(rs.getString(2));
				li.setTOTALES(rs.getLong(3));
				li.setPRESTADOS(rs.getLong(4));
				li.setDISPONIBLES(rs.getLong(5));
				
				lista.add(li);
			}
			
			for(Libro li: lista){
				System.out.println(li.toString());
			} 
			
		}catch(SQLException sqe) {
			throw sqe;
		}catch(Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
		}
		return lista;
	}
}


