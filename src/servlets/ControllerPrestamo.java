package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoEjemplar;
import dao.DaoLibro;
import dao.DaoPrestamo;
import dao.DaoSocio;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import error.EjemplarBaja;
import utilidades.Utilidades;

/**
 * Servlet implementation class ControllerPrestamo
 */
@WebServlet("/ControllerPrestamo")
public class ControllerPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void procesarError(HttpServletRequest request,
			HttpServletResponse response, Exception e, String url) throws ServletException,
	IOException {
		String mensajeError = e.getMessage();
		if(mensajeError.contains("IDEJEMPLAR")) {
			mensajeError = "EL ejemplar no esta en la tabla prestamo";
		request.setAttribute("error", mensajeError);
		}                           
		
			request.getRequestDispatcher(url).forward(request,response);
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String operacion = request.getParameter("operacion");
		
		DaoPrestamo dp = null;
		
		Prestamo p = null;
		
		Ejemplar e = null;
		
		DaoEjemplar daoj = null;
		
		DaoLibro dao = null;
		
		
		switch(operacion) {		
		case "devolucionPrestamo":

			DaoPrestamo daoP = new DaoPrestamo();
			Prestamo pre = new Prestamo();
			int idejemplar = Integer.parseInt(request.getParameter("IDEJEMPLAR"));
			
			
			try {
				
				pre.setIDEJEMPLAR(idejemplar);
				daoP.devolucionPrestamo(pre);
				
				//request.getRequestDispatcher("Confirmaciones/operacionCorrecta.jsp").forward(request, response);

				request.setAttribute("confirmacion", "Devolución registrada exitosamente.");
		        request.getRequestDispatcher("admin/devolucionPrestamo.jsp").forward(request, response);
		        
			} catch(EjemplarBaja eB){ 
				procesarError(request, response, eB, "admin/devolucionPrestamo.jsp");
			}catch (SQLException se) {
				procesarError(request, response, se, "admin/devolucionPrestamo.jsp");
			}catch(Exception ee) {
				procesarError(request, response, ee, "admin/devolucionPrestamo.jsp");
			}
			break;

			
		case "redireccionEliminarEjemplar":
			response.sendRedirect("admin/eliminarEjemplar.jsp");
			break;
		case "consultarEjemplar":
			
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
				request.getRequestDispatcher("admin/eliminarEjemplar.jsp").forward(request, response);
			}catch(SQLException sqe) {
				
			}catch(Exception ee) {
				
			}
			break;
			
			
			/*
		case "ejemplaresAEliminar":
			try {
				daoEjemplar daoe = new daoEjemplar();
				String ejemplar = request.getParameterValues("idejemplar");
				daoe.eliminarEjemplar(ejemplares);
				
				request.setAttribute("busqueda", request.getParameter("busqueda"));
				
				if(ejemplares.length==1) {
					request.setAttribute("confirmacion", "Ejemplar del libro eliminado correctamente");
				}else {
					request.setAttribute("confirmacion", "Ejemplares del libro eliminados correctamente");
				}
				request.getRequestDispatcher("admin/eliminarEjemplares.jsp").forward(request, response);
			}catch(SQLException eS) {
				//procesarErrorSql(request, response, e, "admin/eliminarEjemplares.jsp");
			}catch(Exception ee) {
				procesarError(request, response, ee, "admin/eliminarEjemplares.jsp");
			}
			break;

		
/*
		case "listadoEjemplares":
			
			daoj = new daoEjemplar();
			
			try {
				e = new ejemplar();
				
				daoj.eliminarEjemplar(e);
				
				request.setAttribute("listadoEjemplares", e);
				request.getRequestDispatcher("admin/eliminarEjemplar.jsp").forward(request, response);
	
			} catch (Exception ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			break;
	
		}
	}
		*/
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
