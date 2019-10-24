/**
 * 
 */
package test;

import java.sql.SQLException;

import dao.DaoError;
import dao.DaoSocio;
import entidades.Socio;


public class TestDaoSocio {


	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
				
		DaoSocio ds = new DaoSocio();
		
		//ds.listadoSocios();
		
		//System.out.println(" ");
		
		//ds.buscar(1);
		
		//System.out.println(" ");
		
		//ds.contarSocios();
		
		//System.out.println(" ");
		
		//ds.actualizar(6, "RAUL", "ESPAÑA");
		
		//ds.buscarSocio(1);
		
		//ds.listarSociosPorNombre("SO");
		
		//ds.listadoSociosMorosos();
		
		//ds.listarLibrosDeMorosos(1);
		
		//socio s1 = new socio(6, "RAUL@SOCIO", "RAUL", "ESPAÑA");
		
		//ds.modificarSocio(s1);
		//ds.altaSocio(so);
		
		//socio so = new socio(0,"ALBERTO", "albert@gmail.com", "pazos", "cafe del tropico");
		//Socio so = new Socio(0, "ALBERT@GMAIL.COM", "ALBERTO3", "cafe del tropico3", "pazos3");
		//ds.altaSocio(so);
		
		/*
		try {
			ds.altaSocio(so);
		  }catch(SQLException e) {
			  DaoError de = new DaoError();
			System.out.println(de.getCodigo(e));
			System.out.println(de.getMessage(e));
		}
		*/
		// Solo puedo eliminar el de sergio porque lo e creado yo, sino es mucho pitoste para eliminarlo todo.
		Socio so = new Socio();
		so.setEMAIL("SERGIO@GMAIL.COM");
		//so.setIDSOCIO(12);
		ds.EliminarSocio(so);
	}
		
}