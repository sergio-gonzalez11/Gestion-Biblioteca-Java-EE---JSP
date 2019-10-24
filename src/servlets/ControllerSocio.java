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
import dao.DaoLibro;
import dao.DaoPrestamo;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import utilidades.Utilidades;

/**
 * Servlet implementation class ControllerSocio
 */
@WebServlet("/ControllerSocio")
public class ControllerSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerSocio() {
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
		case "redireccionConsultarLibros":
			response.sendRedirect("admin/consultaLibros.jsp");
			break;
		case "consultarLibro":
			
			String formaDeBusqueda = request.getParameter("formaBusqueda");
			String busqueda = request.getParameter("busqueda");
			
			try {
				
				dao = new DaoLibro();
				
				ArrayList<Libro> lista = new ArrayList<Libro>();
				
				if(formaDeBusqueda.equalsIgnoreCase("TITULO")) {
					lista = dao.consultarLibro(Utilidades.eliminarAcentos(busqueda.toUpperCase()), "%", "%");
					request.setAttribute("TITULO", "selected");
				}else if(formaDeBusqueda.equalsIgnoreCase("isbn")) {
					lista = dao.consultarLibro("%", Utilidades.eliminarAcentos(busqueda.toUpperCase()), "%");
					request.setAttribute("ISBN", "selected");
				}else {
					lista = dao.consultarLibro("%","%", Utilidades.eliminarAcentos(busqueda.toUpperCase()));
					request.setAttribute("NOMBREAUTOR", "selected");
				}
				request.setAttribute("lista", lista);
				request.setAttribute("busqueda", busqueda);
				request.getRequestDispatcher("admin/consultaLibros.jsp").forward(request, response);
			}catch(SQLException sqe) {
				
			}catch(Exception ee) {
				
			}
			break;

	
			case "altaSocio":

				String NOMBRE = request.getParameter("NOMBRE");
				String EMAIL = request.getParameter("EMAIL");
				String CLAVE = request.getParameter("CLAVE");
				String DIRECCION = request.getParameter("DIRECCION");

				ds = new DaoSocio();

				Socio so = new Socio();
			
				try {
					
					so.setNOMBRE(NOMBRE);
					so.setEMAIL(EMAIL);
					so.setCLAVE(CLAVE);
					so.setDIRECCION(DIRECCION);
					
					ds.altaSocio(so);
					
					request.getRequestDispatcher("admin/confirmacion.jsp").forward(request, response);

				}catch(ParseException pe) {
					procesarError(request, response, pe , "admin/altaSocio.jsp");
				}catch (SQLException se) {
					procesarError(request, response, se, "admin/altaSocio.jsp");
				}catch(Exception e) {
				}
				break;
			
			case "redireccionModificarDatosPersonales":
				try {
					DaoSocio daoso = new DaoSocio();
					Socio soc = daoso.buscarSocioPorEmail(request.getUserPrincipal().getName());
					request.setAttribute("socio", soc);
					request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
				}catch(SQLException e) {

				}catch(Exception e) {
					
				}
				break;

			case "modificarDatosPersonales":
				
				try {
					DaoSocio daos = new DaoSocio();
					Socio socio = new Socio();
					request.getUserPrincipal().getName();
					
					socio.setIDSOCIO(Integer.parseInt(request.getParameter("IDSOCIO")));
					
					String nombre = Utilidades.eliminarAcentos(request.getParameter("nuevoNombre"));
					String direccion = Utilidades.eliminarAcentos(request.getParameter("nuevaDireccion"));
					
					socio.setNOMBRE(nombre);
					socio.setDIRECCION(direccion);
					
					daos.modificarSocio(socio);
					
					request.setAttribute("socio", socio);
					request.setAttribute("confirmacion", "Registro modificado correctamente");
					
					request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
				
				}catch(SQLException se) {
					request.setAttribute("error", "Error en el Registro");
					request.getRequestDispatcher("admin/modificarDatosPersonales.jsp").forward(request, response);
				}catch(Exception e) {
					request.setAttribute("error", "Error en el Registro");
					request.getRequestDispatcher("admin/modificarDatosPersonales.jsp").forward(request, response);
				}
				break;

				
			case "logout":
				HttpSession sesion = request.getSession();
				sesion.invalidate();
				request.getRequestDispatcher("socios/home.jsp").forward(request, response);
				
			}
		}
	

		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
