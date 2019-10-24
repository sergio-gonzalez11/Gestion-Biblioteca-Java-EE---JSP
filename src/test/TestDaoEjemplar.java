/**
 * 
 */
package test;

import java.util.ArrayList;

import dao.DaoEjemplar;
import entidades.Ejemplar;
import entidades.Libro;

/**
 * @author usuario
 *
 */
public class TestDaoEjemplar {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		//daoEjemplar daoj = new daoEjemplar();
		
		//daoj.listadoEjemplares();
		
//		Ejemplar e = new Ejemplar();
		
		DaoEjemplar daoe = new DaoEjemplar();
		
//		e.setIDEJEMPLAR(2);
		
		//daoe.eliminarEjemplar(e);
		
		Libro li = new Libro();
		li.setISBN("008");
		
		ArrayList<Ejemplar> lista = daoe.buscarEjemplar(li);
		for(Ejemplar e : lista) {
			System.out.println(e.toString());
		}
		
		

	}

}
