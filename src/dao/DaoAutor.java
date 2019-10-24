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
import error.FechaNoValida;

/**
 * @author usuario
 *
 */
public class DaoAutor {

	public DaoAutor() {
	 }

	public ArrayList<Autor> listadoAutores() throws SQLException, Exception {
		
		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {
			
			conex = new Conexion();
			con = conex.getConexion();
			
			String sql = "SELECT * FROM AUTOR ORDER BY IDAUTOR";
			
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			
			ArrayList<Autor> listadoAutores = new ArrayList<Autor>();
			
			while (rs.next()) {
				int idautor = rs.getInt(1);
				String nombre = rs.getString(2);
				java.sql.Date fechaNacimiento = rs.getDate(3);

				Autor a = new Autor(idautor, nombre, fechaNacimiento);
				listadoAutores.add(a);
			}
			
			for(Autor a: listadoAutores){
				System.out.println(a.toString());
			}
			
			return listadoAutores;
				
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception ee) {
				throw ee;
			}finally {
				if(pt!=null) {
					pt.close();
				}
				if(rs!=null) {
					rs.close();
				}
				if(con!=null) {
					con.close();
				}
			}
		return null;
	}
	
	
	 public void insertaAutor(Autor a) throws SQLException, Exception {
		 
		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		 try {
			 
			 conex = new Conexion();
			 con = conex.getConexion();
			 
			 String sql = "INSERT INTO AUTOR VALUES (S_AUTOR.NEXTVAL,?,?)";
			 
			 pt = con.prepareStatement(sql);
			
			 pt.setString(1, a.getNOMBRE());
			 pt.setDate(2,a.getFECHANACIMIENTO());
			 
			 pt.executeUpdate();
			 
			 /*
			 rs = pt.executeQuery();
			 
			 if(rs.next()){
					throw new fechaNoValida();
				}
				
				*/
			 
		 }catch(FechaNoValida fnv){ 
				throw new FechaNoValida();
		} catch (SQLException se) {
			throw se;
		}catch (Exception e) {
			throw e;
		}finally{
			 if(pt!=null)
				 pt.close();
			 if(rs!=null)
				 rs.close();
			 if(con!=null)
				 con.close();
		 }
	 }
}

