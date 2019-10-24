/**
 * 
 */
package test;

import java.sql.SQLException;

import dao.DaoLibro;
import entidades.Libro;

public class TestDaoLibro {


	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		DaoLibro dal = new DaoLibro();
		
		//dal.consultarLibros("001");
		Libro li = new Libro();
		li.setISBN("013");
		li.setNOMBREAUTOR("JORGE");
		li.setIDAUTOR(5);
		
		
		dal.insertaLibro(li);

	}

}
