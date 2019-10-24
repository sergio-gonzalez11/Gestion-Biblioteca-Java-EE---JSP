/**
 * 
 */
package test;

import java.sql.SQLException;
import java.util.Date;

import dao.DaoAutor;
import dao.DaoError;
import entidades.Autor;

public class TestDaoAutor {

	
	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		DaoAutor da=new DaoAutor();
		
		da.listadoAutores();
	
		Autor a=new Autor();
		java.util.Date fecha = new Date(0);
		java.sql.Date fechaautor=new java.sql.Date(fecha.getTime());
		a.setNOMBRE("Sergio");
		a.setFECHANACIMIENTO(fechaautor);
		//da.insertaAutor(a);
		
		/*
		try {
			da.insertaAutor(a);
			}catch(fechaNoValida fnv) {
				System.out.println(fnv.getMessage());
			}
			*/
		
		try {
			da.insertaAutor(a);
		  }catch(SQLException e) {
			  DaoError de = new DaoError();
			System.out.println(de.getCodigo(e));
			System.out.println(de.getMessage(e));
		}
	}
}