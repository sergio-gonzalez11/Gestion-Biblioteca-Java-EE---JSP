
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import conexiones.Conexion;
import entidades.Libro;
import entidades.Socio;


public class DaoSocio extends Conexion {

	public DaoSocio() {

	}
	
	
	public Socio buscarSocioPorEmail(String email) throws SQLException, Exception {
		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Socio socio = null;
		
		try {
			
			conex = new Conexion();
			con = conex.getConexion();
			
			ps = con.prepareStatement("SELECT * FROM SOCIO WHERE EMAIL = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				socio = new Socio();
				socio.setIDSOCIO(rs.getInt(1));
				socio.setEMAIL(rs.getString(2));
				socio.setNOMBRE(rs.getString(3));
				socio.setDIRECCION(rs.getNString(4));
			}
		}catch(SQLException e) {
			throw e;
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
		return socio;
	}


	
	public ArrayList<Socio> listaSociosPaginada(long pagina, long numeroRegistrosPagina)
			throws SQLException, Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Socio s = new Socio();
		ArrayList<Socio> lista = new ArrayList<Socio>();

		try {

			conex = new Conexion();
			con = conex.getConexion();

			ps = con.prepareStatement("SELECT FILA, IDSOCIO, EMAIL, NOMBRE, DIRECCION\n"
					+ "					FROM (SELECT ROWNUM FILA, IDSOCIO, EMAIL, NOMBRE, DIRECCION\n"
					+ "							FROM(SELECT IDSOCIO, EMAIL, NOMBRE, DIRECCION FROM SOCIO ORDER BY IDSOCIO ASC))\n"
					+ "							WHERE FILA BETWEEN ? AND ?");

			ps.setLong(1, pagina);
			ps.setLong(2, numeroRegistrosPagina);

			rs = ps.executeQuery();

			while (rs.next()) {
				s = new Socio();

				s.setIDSOCIO(rs.getInt("IDSOCIO"));
				s.setEMAIL(rs.getString("EMAIL"));
				s.setNOMBRE(rs.getString("NOMBRE"));
				s.setDIRECCION(rs.getString("DIRECCION"));

				lista.add(s);
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception ee) {
			throw ee;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return lista;
	}

	// Método normal
	public ArrayList<Socio> listadoSocios() throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		ArrayList<Socio> listadoSocios = new ArrayList<Socio>();

		try {

			conex = new Conexion();
			con = conex.getConexion();

			String sql = "SELECT * FROM SOCIO";

			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();

			while (rs.next()) {

				int idsocio = rs.getInt("IDSOCIO");
				String email = rs.getString("EMAIL");
				String nombre = rs.getString("NOMBRE");
				String direccion = rs.getString("DIRECCION");

				Socio s = new Socio(idsocio, email, nombre, direccion, null);

				listadoSocios.add(s);
			}

			for (Socio s : listadoSocios) {
				System.out.println(s.toString());
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listadoSocios;
		
	}
	
	

	public void buscar(int idSocio) throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		Scanner sc = new Scanner(System.in);

		try {

			conex = new Conexion();
			con = conex.getConexion();

			ps = con.prepareStatement("SELECT * FROM SOCIO WHERE IDSOCIO=?");

			System.out.println("Introduce el idsocio de la persona a buscar!");

			idSocio = sc.nextInt();

			ps.setLong(1, idSocio);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println("El id introducido pertenece a la persona " + rs.getString("NOMBRE") + " "
						+ rs.getString("DIRECCION"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
	}

	
	public ArrayList<Socio> listarSociosPorNombre(String nombre) throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Socio> lista = new ArrayList<Socio>();

		try {
			conex = new Conexion();
			con = conex.getConexion();

			ps = con.prepareStatement("SELECT * FROM SOCIO WHERE upper (NOMBRE) LIKE UPPER(?)");

			ps.setString(1, "%" + nombre + "%");
			rs = ps.executeQuery();

			while (rs.next()) {

				Socio s = new Socio();

				s.setIDSOCIO(rs.getInt(1));
				s.setEMAIL(rs.getNString(2));
				s.setNOMBRE(rs.getNString(3));
				s.setDIRECCION(rs.getNString(4));

				lista.add(s);
			}

			for (Socio s : lista) {
				System.out.println(s.toString());
			}

		} catch (SQLException e) {
			throw e;
		} catch (Exception ee) {
			throw ee;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return lista;
	}

	
	
	public long contarSocios() throws SQLException, Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;

		long totalRegistros = 0;

		try {

			conex = new Conexion();
			con = conex.getConexion();

			pt = con.prepareStatement("SELECT COUNT(*) FROM SOCIO");

			rs = pt.executeQuery();

			if (rs.next()) {
				totalRegistros = rs.getLong(1);
			}

		} catch (SQLException e) {
			throw e;
		} catch (Exception ee) {
			throw ee;
		} finally {
			if (pt != null) {
				pt.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return totalRegistros;
	}

	
	
	public Socio altaSocio(Socio so) throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {

			conex = new Conexion();
			con = conex.getConexion();

			String sql1 = "INSERT INTO USUARIOS VALUES(?, MD5HASH(?))";
			ps = con.prepareStatement(sql1);
			ps.setString(1, so.getEMAIL());
			ps.setString(2, so.getCLAVE());
			ps.executeUpdate();
			ps.close();

			String sql2 = "INSERT INTO GRUPOS VALUES (?,?)";
			ps = con.prepareStatement(sql2);
			ps.setString(1, "sociosbiblioteca");
			ps.setString(2, so.getEMAIL());
			ps.executeUpdate();
			ps.close();

			String sql3 = "INSERT INTO SOCIO VALUES(S_SOCIO.NEXTVAL,?,?,?)";
			ps = con.prepareStatement(sql3);
			ps.setString(1, so.getEMAIL());
			ps.setString(2, so.getNOMBRE());
			ps.setString(3, so.getDIRECCION());
			ps.executeUpdate();
			ps.close();
			con.commit();
			con.close();

		} catch (SQLException e) {
			throw e;
		} catch (Exception ee) {
			throw ee;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return so;
	}

	

	public Socio buscarSocio(long idSocio) throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Socio s = new Socio();

		try {
			conex = new Conexion();
			con = conex.getConexion();

			ps = con.prepareStatement("SELECT * FROM SOCIO WHERE IDSOCIO=?");

			ps.setLong(1, idSocio);
			rs = ps.executeQuery();

			while (rs.next()) {
				s.setIDSOCIO(rs.getInt("IDSOCIO"));
				s.setEMAIL(rs.getString("EMAIL"));
				s.setNOMBRE(rs.getString("NOMBRE"));
				s.setDIRECCION(rs.getString("DIRECCION"));
			}

			System.out.println(s.toString());

		} catch (SQLException e) {
			throw e;
		} catch (Exception ee) {
			throw ee;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return s;
	}

	
	public void modificarSocio(Socio s) throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {

			conex = new Conexion();
			con = conex.getConexion();

			ps = con.prepareStatement("UPDATE SOCIO SET NOMBRE=?, DIRECCION=? WHERE IDSOCIO=?");

			ps.setString(1, s.getNOMBRE().toUpperCase());
			ps.setString(2, s.getDIRECCION().toUpperCase());
			ps.setInt(3, s.getIDSOCIO());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} catch (Exception ee) {
			throw ee;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	
	public ArrayList<Socio> listadoSociosMorosos() throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;

		try {
			conex = new Conexion();
			con = conex.getConexion();

			String sql = "SELECT DISTINCT P.IDSOCIO, S.NOMBRE, S.EMAIL FROM PRESTAMO P, SOCIO S WHERE P.FECHALIMITEDEVOLUCION < SYSDATE AND S.IDSOCIO = P.IDSOCIO ORDER BY P.IDSOCIO ASC";

			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();

			ArrayList<Socio> listadoSociosMorosos = new ArrayList<Socio>();

			while (rs.next()) {

				int idsocio = rs.getInt("IDSOCIO");
				String email = rs.getString("EMAIL");
				String nombre = rs.getString("NOMBRE");

				Socio s = new Socio(idsocio, email, nombre, null, null);

				listadoSociosMorosos.add(s);
			}

			for (Socio s : listadoSociosMorosos) {
				System.out.println(s.toString());
			}

			return listadoSociosMorosos;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	
	public ArrayList<Libro> listarLibrosDeMorosos(int idSocio) throws Exception {

		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Libro> listaLibrosDeMorosos = new ArrayList<Libro>();

		try {
			conex = new Conexion();
			con = conex.getConexion();

			ps = con.prepareStatement("SELECT L.TITULO, P.IDEJEMPLAR, TRUNC(SYSDATE-P.FECHALIMITEDEVOLUCION)\r\n"
					+ "FROM PRESTAMO P, EJEMPLAR E, LIBRO L\r\n"
					+ "WHERE P.IDEJEMPLAR = E.IDEJEMPLAR AND E.ISBN = L.ISBN AND\r\n" + "      P.IDSOCIO = ?");

			ps.setInt(1, idSocio);
			rs = ps.executeQuery();

			while (rs.next()) {

				Libro libro = new Libro();

				libro.setISBN(rs.getString(1));
				libro.setTITULO(rs.getString(2));
				libro.setIDAUTOR(rs.getLong(3));

				listaLibrosDeMorosos.add(libro);
			}

			for (Libro libro : listaLibrosDeMorosos) {
				System.out.println(libro.toString());
			}

		} catch (SQLException se) {

		} catch (Exception e) {

		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return listaLibrosDeMorosos;
	}
	
	
	public Socio EliminarSocio (Socio so)throws SQLException, Exception {
		
		Conexion conex = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		try {
			conex = new Conexion();
			con = conex.getConexion();
			 
			ps = con.prepareStatement("DELETE FROM SOCIO WHERE EMAIL=?");
			ps.setString(1, so.getEMAIL());
			ps.execute();
			
			/*
			ps = con.prepareStatement("DELETE FROM USUARIOS WHERE EMAIL=?");
			ps.setString(1, so.getEMAIL());
			ps.execute();
			ps.close();
			
			ps = con.prepareStatement("DELETE FROM PRESTAMO WHERE IDSOCIO=?");
			ps.setInt(1, so.getIDSOCIO());
			ps.execute();
			ps.close();

			ps = con.prepareStatement("DELETE FROM DEVOLUCION WHERE IDSOCIO=?");
			ps.setInt(1, so.getIDSOCIO());
			ps.execute();
			ps.close();
			
			ps = con.prepareStatement("DELETE FROM GRUPOS WHERE IDUSUARIO=?");
			ps.setString(1, so.getEMAIL());
			ps.execute();
			ps.close();
			
			ps = con.prepareStatement("DELETE FROM SOCIOPENALIZADO WHERE IDSOCIO=?");
			ps.setInt(1, so.getIDSOCIO());
			ps.execute();
			ps.close();
			*/
			System.out.println("Socio eliminado correctamente");
			
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
		return so;
	}

}
