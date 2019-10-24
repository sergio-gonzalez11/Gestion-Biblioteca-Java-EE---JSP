package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Prestamo;
import error.EjemplarBaja;

/**
 * @author usuario
 *
 */
public class DaoPrestamo {
	
	public DaoPrestamo() {

	}
	
	/*
	public ArrayList<prestamo>listadoPrestamosFueraPlazo() throws Exception {
		
		conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;

		try {
			
			conex = new conexion();
			con = conex.getConexion();

			String sql = "SELECT * FROM PRESTAMO";

			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();

			ArrayList<prestamo> listadoPrestamosFueraPlazo = new ArrayList<prestamo>();

			while(rs.next()){

				int idejemplar = rs.getInt("IDEJEMPLAR");
				long idsocio = rs.getLong("IDSOCIO");
				//java.sql.Date fechaprestamo = rs.getDate("FECHAPRESTAMO");
				//java.sql.Date fechalimitedevolucion = rs.getDate("FECHALIMITEDEVOLUCION");

				prestamo p = new prestamo(idejemplar, idsocio);

				//listadoPrestamosFueraPlazo.add(p);
			}

			for(prestamo p: listadoPrestamosFueraPlazo){
				System.out.println(p.toString());
			} 

			return listadoPrestamosFueraPlazo;

		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	*/
	
	public void altaPrestamo(Prestamo p) throws EjemplarBaja, SQLException, Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {
			conex = new Conexion();
			con = conex.getConexion();

			String sql1 = "SELECT * FROM EJEMPLAR WHERE IDEJEMPLAR=? AND BAJA=?";
			String Baja = "S";
			
			pt = con.prepareStatement(sql1);
			
			pt.setInt(1, p.getIDEJEMPLAR());
			pt.setString(2, Baja);
			
			rs = pt.executeQuery();
			
			if(rs.next()) {
				throw new EjemplarBaja();
			}
			
			String sql2 = "INSERT INTO PRESTAMO (IDEJEMPLAR, IDSOCIO) VALUES (?,?)";
			
			pt = con.prepareStatement(sql2);

			pt.setInt(1, p.getIDEJEMPLAR());
			pt.setLong(2,p.getIDSOCIO());

			pt.execute();
			con.commit();
			
			System.out.println("Datos introducidos correctamente!");

		}catch(EjemplarBaja eB){ 
				throw new EjemplarBaja();
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
	
	
	
	public void devolucionPrestamo(Prestamo p) throws  EjemplarBaja, SQLException, Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {

			conex = new Conexion();
			con = conex.getConexion();
			
			String sql2 = "DELETE FROM PRESTAMO WHERE IDEJEMPLAR=?";
			
			pt = con.prepareStatement(sql2);
			pt.setInt(1, p.getIDEJEMPLAR());
			
			pt.executeUpdate();
			pt.close();
			
			System.out.println("Datos introducidos correctamente!");

		}catch(EjemplarBaja eB){ 
				throw new EjemplarBaja();
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
