package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoAutor;
import dao.DaoEjemplar;
import dao.DaoError;
import dao.DaoLibro;
import dao.DaoPrestamo;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import utilidades.Utilidades;

/**
 * Servlet implementation class ControllerSocio
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void procesarError(HttpServletRequest request, 
    		HttpServletResponse response, Exception e, String url) throws ServletException,
	IOException {
    	String mensajeError = e.getMessage();
		if(mensajeError.contains("ejemplar")) {
		request.setAttribute("error", mensajeError);
		}
			if(mensajeError.contains("Unparseable date")) {
				request.setAttribute("error", mensajeError);
			}
				if(mensajeError.contains("ejemplar")) {
					request.setAttribute("error", mensajeError);
				}
			
				request.getRequestDispatcher(url).forward(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
protected void procesarErrorSql(HttpServletRequest request, HttpServletResponse response, SQLException e, String url)  throws SQLException, Exception {
		
		DaoError dao = new DaoError();
		String codigo =dao.getCodigo(e);
		request.setAttribute("error",  e.getMessage());
		
		switch(codigo) {
		case "00001":
			request.setAttribute("error", dao.getMessage(e));
			request.getRequestDispatcher(url).forward(request, response);
		break;
		case "02291":
			try {
				request.setAttribute("error", dao.getMessage(e));
				request.getRequestDispatcher(url).forward(request, response);		
			}catch(Exception eee) {
				request.setAttribute("error", "Error desconocido"+eee.getMessage());
				request.getRequestDispatcher(url).forward(request, response);		
			}
			break;
		case "20002":
				request.setAttribute("error", dao.getMessageDisparador(e));
				request.getRequestDispatcher(url).forward(request, response);
			break;
		case "20001":
			request.setAttribute("error", dao.getMessageDisparador(e));
			request.getRequestDispatcher(url).forward(request, response);
		break;
		case "20000":
			request.setAttribute("error", dao.getMessageDisparador(e));
			request.getRequestDispatcher(url).forward(request, response);
		break;
			default:
				request.getRequestDispatcher("url").forward(request, response);
		}
	}

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String operacion = request.getParameter("operacion");
		//System.out.println(operacion);
		
		DaoSocio ds = null;
		
		String iniciales;
		
		DaoAutor da = null;
		ArrayList<Autor>listadoAutores = null;
		
		Autor a = null;
		
		DaoLibro dao = null;
		
		ArrayList<Socio>listadoSociosMorosos = null;
		
		ArrayList<Libro> l = null;
		
		Libro li = null;
		
		DaoPrestamo dp = null;
		
		Prestamo p = null;
		
		switch (operacion) {
		
		case "mSocioListar":	
			DaoSocio daoS = new DaoSocio();
			
			try {
				
				//Primero listamos la lista para editar
				ArrayList<Socio> listadoSocios = new ArrayList<Socio>();
				listadoSocios = daoS.listadoSocios();
				
				request.setAttribute("listadoSocios", listadoSocios);
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
				
			}catch(SQLException e) {

			}catch(Exception e) {

			}
			break;
		
		case "mSocioBuscar":
			DaoSocio daoSo = new DaoSocio();
			Socio so = new Socio();
			
			try {	
				
				//Paso 2, buscamos por IDSOCIO, parseamos y relaccionamos las variables del jsp
				int idsocio = Integer.parseInt(request.getParameter("IDSOCIO"));
				
				//Aqui al ser un objeto y no haber arraylist le metemos direcatmente el buscar socio
				request.setAttribute("socios", daoSo.buscarSocio(idsocio));
				request.setAttribute("id", idsocio);
				
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
			//}catch(SQLException e) {
			}catch(Exception e) {
			}
			break;
		
		case "mSocioFinal":			
			DaoSocio dso = new DaoSocio();
			Socio soci = new Socio();
			
			try {
				
				//Paso3, recojemos los valores del input como nuevoNombre, nuevoDireccion el id por el que buscamos
				int idsocio = Integer.parseInt(request.getParameter("IDSOCIO"));
				String nombre = request.getParameter("nuevoNombre");
				String direccion = request.getParameter("nuevaDireccion");
				
				//Por ultimo metemos las variables y con el dao modificamos los datos
				soci.setIDSOCIO(idsocio);
				soci.setNOMBRE(nombre);
				soci.setDIRECCION(direccion);
				
				dso.modificarSocio(soci);
				
				request.setAttribute("confirmacion", "Socio actualizado.");				
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
			}catch(SQLException e) {

			}catch(Exception e) {

			}
			break;
			
		case "socioEliminar":			
			try {
				
				Socio socio = new Socio();
				DaoSocio daso = new DaoSocio();
			
				//Recojemos el email que es la clave primaria
				String email = request.getParameter("EMAIL");
				
				//Asignamos la variable y eliminamos
				socio.setEMAIL(email);
				
				daso.EliminarSocio(socio);
				
				request.setAttribute("confirmacion", "Socio eliminado.");				
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
			}catch(SQLException e) {

			}catch(Exception e) {
				request.setAttribute("error", "Socio no eliminado.");
				procesarError(request, response, e, "admin/modificarSocio.jsp");
			}
		break;
		
		
		
		case "consultarEjemplar":
			
			String formaDeBusqueda = request.getParameter("formaBusqueda");
			String busqueda = request.getParameter("busqueda");
			
			try {
				
				DaoLibro daol = new DaoLibro();			
				ArrayList<Libro> lista = new ArrayList<Libro>();
				
				if(formaDeBusqueda.equalsIgnoreCase("TITULO")) {
					lista = daol.consultarLibro(Utilidades.eliminarAcentos(busqueda.toUpperCase()), "%", "%");
					request.setAttribute("TITULO", "selected");
				}else if(formaDeBusqueda.equalsIgnoreCase("ISBN")) {
					lista = daol.consultarLibro("%", Utilidades.eliminarAcentos(busqueda.toUpperCase()), "%");
					request.setAttribute("ISBN", "selected");
				}else {
					lista = daol.consultarLibro(Utilidades.eliminarAcentos(busqueda.toUpperCase()), "%","%");
					request.setAttribute("NOMBREAUTOR", "selected");
				}
				
				request.setAttribute("lista", lista);
				request.setAttribute("busqueda", busqueda);
				
				request.getRequestDispatcher("admin/eliminarEjemplares.jsp").forward(request, response);
			}catch(SQLException sqe) {
				
			}catch(Exception ee) {
				
			}
			break;
			
		case "listarEjemplares":
			ds = new DaoSocio();
			try {
				
				//Volvemos a listar la primera consulta para que aparecza otra vez
				
				DaoLibro daol = new DaoLibro();			
				ArrayList<Libro> lista = new ArrayList<Libro>();
				
					
				//Aqui parseamos el idsocio del elemento editar de listar librosEnDeuda&IDSOCIO
				
				String isbn = request.getParameter("ISBN");
				String titulo = request.getParameter("TITULO");
				
				
				//Hacemos la nueva consulta que queremos mostrar
				DaoEjemplar dae = new DaoEjemplar();
				ArrayList <Ejemplar> listaEjemplares = new ArrayList<Ejemplar>();
				
				Libro lib = new Libro();
				lib.setISBN(isbn);
				
				listaEjemplares = dae.buscarEjemplar(lib);
				
				
				//Aqui relacionamos las variables del for each y el value "id" con la variable parseada para que sepa el socio que estamos seleccionando
				request.setAttribute("listaEjemplares", listaEjemplares);
				request.setAttribute("id", isbn);
				request.setAttribute("titulo", titulo);
				
				request.getRequestDispatcher("admin/eliminarEjemplares.jsp").forward(request, response);
				
			}catch(ParseException pe) {
				procesarError(request, response, pe , "admin/eliminarEjemplares.jsp");
			}catch(SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/eliminarEjemplares.jsp");
				} catch (Exception e) {
				}
			}catch(Exception e) {

			}
			
			break;
			
		case "eliminarEjemplar":
			ds = new DaoSocio();
			try {
				
				DaoEjemplar dae = new DaoEjemplar();
				Ejemplar e = new Ejemplar();
				
				//Recojemos el idejemplar por el k vamos a borrar y parseamos xk no es un string
				long idejemplar = Long.parseLong(request.getParameter("IDEJEMPLAR"));
				
				
				//Le metemos la variable recojida del jsp
				e.setIDEJEMPLAR(idejemplar);
				
				
				//Y eliminamos con el metodo que tenemos en el dao
				dae.eliminarEjemplar(e);
				
				request.setAttribute("confirmacion", "Ejemplar eliminado.");		
				request.getRequestDispatcher("admin/eliminarEjemplares.jsp").forward(request, response);
				
			}catch(ParseException pe) {
				procesarError(request, response, pe , "admin/eliminarEjemplares.jsp");
			}catch(SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/eliminarEjemplares.jsp");
				} catch (Exception e) {
				}
			}catch(Exception e) {

			}
			
			break;
			
			
		case "altaSocio":
			ds = new DaoSocio();
			Socio soc = new Socio();
			try {
				
				//Recojemos las variables del jsp y al ser string no hace falta parsear.
				String nombre = request.getParameter("NOMBRE");
				String email = request.getParameter("EMAIL");
				String clave = request.getParameter("CLAVE");
				String direccion = request.getParameter("DIRECCION");
				
				//Introducimos las variables en los cmapos correspondiente de la clase
				soc.setNOMBRE(nombre);
				soc.setEMAIL(email);
				soc.setCLAVE(clave);
				soc.setDIRECCION(direccion);
				
				//Con el metodo del dao introducimos los datos del objeto socio
				ds.altaSocio(soc);
				
				
				request.setAttribute("confirmacion", "Nuevo socio registrado.");		
				request.getRequestDispatcher("admin/altaSocio.jsp").forward(request, response);
				
			}catch(ParseException pe) {
				procesarError(request, response, pe , "admin/altaSocio.jsp");
			}catch(SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/altaSocio.jsp");
				} catch (Exception e) {
				}
			}catch(Exception e) {

			}
			
			break;
	}
}

		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
