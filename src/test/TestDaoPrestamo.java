/**
 * 
 */
package test;

import java.sql.SQLException;

import dao.DaoError;
import dao.DaoPrestamo;
import entidades.Prestamo;
import error.EjemplarBaja;


public class TestDaoPrestamo {


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DaoPrestamo dp = new DaoPrestamo();
		
		//dp.listadoPrestamosFueraPlazo();
		
		Prestamo p = new Prestamo(10, 4);
		
		/*
		try {
			dp.altaPrestamo(p);
		  }catch(SQLException e) {
			  daoError de = new daoError();
			System.out.println(de.getCodigo(e));
			System.out.println(de.getMessage(e));
		}
		*/
		try {
			dp.devolucionPrestamo(p);
		  }catch(SQLException e) {
			  DaoError de = new DaoError();
			System.out.println(de.getCodigo(e));
			System.out.println(de.getMessage(e));
		}
		
		//dp.altaPrestamo(p);
	}

}
