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

import dao.DaoAutor;
import dao.DaoEjemplar;
import dao.DaoError;
import dao.DaoLibro;
import dao.DaoPrestamo;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import error.EjemplarBaja;
import utilidades.Utilidades;


@WebServlet({ "/ControllerAdmin", "/controllerAdmin" })
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerAdmin() {
		super();
	}
	
	
	protected void procesarError(HttpServletRequest request,
			HttpServletResponse response, Exception e, String url) throws ServletException,
	IOException {
		String mensajeError = e.getMessage();
		if(mensajeError.contains("ejemplar")) {
			mensajeError = "Error de ejemplar desconocido";
		request.setAttribute("error", mensajeError);
		}
			if(mensajeError.contains("Unparseable date")) {
				mensajeError = "Formato de fecha no válido";
				request.setAttribute("error", mensajeError);
			}
				if(mensajeError.contains("ejemplar")) {
					request.setAttribute("error", mensajeError);
				}
				
				request.getRequestDispatcher(url).forward(request, response);
		}
	
	
	protected void procesarErrorSQL(HttpServletRequest request,HttpServletResponse response, SQLException e, String url)
			throws ServletException, IOException {
		String mensajeError= e.getMessage();
			request.setAttribute("error", mensajeError);
	request.getRequestDispatcher(url).forward(request,response);
}
	
	
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
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String operacion = request.getParameter("operacion");
		
		DaoSocio ds = null;
		
		String iniciales;
		
		DaoAutor da = null;
		//ArrayList<Autor>listadoAutores = null;
		
		Autor a = null;
		
		DaoLibro dal = null;
		
		DaoEjemplar de = null;
		
		ArrayList<Socio>listadoSociosMorosos = null;
		
		
		Libro li = null;
		DaoPrestamo dp = null;
		
		//prestamo p = null;
		
		switch(operacion) {
		
		case "listarSocios":
			
			try {
				
				long pagina = Long.parseLong(request.getParameter("nPagina"));
				DaoSocio das = new DaoSocio();
				
				final long numeroRegistros = 5;
				long totalRegistros = das.contarSocios();
				long paginaMaxima =(long) Math.ceil((double)totalRegistros/(double)numeroRegistros);
				
				ArrayList<Socio>listadoSocios = new ArrayList<Socio>();
				pagina += 1;
				listadoSocios = das.listaSociosPaginada((numeroRegistros*pagina)-4, numeroRegistros*pagina);
				
				if(pagina>paginaMaxima) {
					pagina = 1;
					listadoSocios = das.listaSociosPaginada((numeroRegistros*pagina)-4, numeroRegistros*pagina);
				}
				request.setAttribute("nMaximoPagina", pagina*numeroRegistros);
				request.setAttribute("nMaximoPagina", pagina*numeroRegistros);
				if(pagina<1 || pagina == paginaMaxima) {
					pagina = paginaMaxima;
					listadoSocios = das.listaSociosPaginada((numeroRegistros*pagina)-4, numeroRegistros*pagina);
					request.setAttribute("nMaximoPagina", totalRegistros);
				}
				request.setAttribute("totalRegistros", totalRegistros);
				request.setAttribute("nPagina", pagina);
				request.setAttribute("listadoSocios", listadoSocios);
				request.getRequestDispatcher("admin/listadoSocios.jsp").forward(request, response);
			}catch(SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/listadoSocios.jsp");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}catch(Exception e) {
				procesarError(request, response, e, "admin/listadoSocios.jsp");
			}
			break;
		/*	
		
		case "listarSocios":
			
			ds = new DaoSocio();
			
		try {
			ArrayList<Socio> listadoSocios = new ArrayList<Socio>();
			
			listadoSocios = ds.listadoSocios();
			
			request.setAttribute("listadoSocios", listadoSocios);
			request.getRequestDispatcher("admin/listadoSocios.jsp").forward(request, response);				
			
		} catch (Exception e) {
			procesarError(request, response, e, "admin/listadoSocios.jsp");
		}
		
		break;
		*/
		case "busquedaSocioAModificar":
			try {
				DaoSocio daosocio = new DaoSocio();
				
				String formulario = request.getParameter("frmbusquedanombre");
				ArrayList<Socio>listadoSocios = daosocio.listarSociosPorNombre(formulario);
				
				request.setAttribute("iniciales", formulario);
				request.setAttribute("listadoSocios", listadoSocios);
				request.getRequestDispatcher("admin/modificarSocioAntiguo.jsp").forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "redirectMSocios":
			response.sendRedirect("admin/modificarSocioAntiguo.jsp");
			break;
		case "socioAModificar":
			try {
				DaoSocio daosocio = new DaoSocio();
				
				request.setAttribute("socio", daosocio.buscarSocio(Long.parseLong(request.getParameter("IDSOCIO"))));
				
				request.getRequestDispatcher("admin/modificarSocioAntiguo2.jsp").forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "modificarSocio":
			try {
				DaoSocio daosocio = new DaoSocio();
				Socio socio = new Socio();
				
				socio.setIDSOCIO(Integer.parseInt(request.getParameter("IDSOCIO")));
				socio.setNOMBRE(request.getParameter("nuevoNombre"));
				socio.setDIRECCION(request.getParameter("nuevaDireccion"));
				
				daosocio.modificarSocio(socio);
				
				request.setAttribute("confirmacion", "Socio modificado");
				request.getRequestDispatcher("admin/modificarSocioAntiguo2.jsp").forward(request, response);
			}catch(Exception e) {

			}
			break;
			
				
		case "altaAutor":
			
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat ("dd-MM-yyyy");
			formatoDeFecha.setLenient(false);
			java.util.Date fechaFormateada = null;
			
			String nombre = request.getParameter("NOMBRE");
			String fechaFormulario = request.getParameter("FECHANACIMIENTO");
				
			DaoAutor daoA = new DaoAutor();
			Autor au = new Autor();
			
			try {

				fechaFormateada = formatoDeFecha.parse(fechaFormulario);
				au.setFECHANACIMIENTO(new java.sql.Date(fechaFormateada.getTime()));
				au.setNOMBRE(nombre);
				daoA.insertaAutor(au);
				
				//request.getRequestDispatcher("admin/confirmacion.jsp").forward(request, response);

				request.setAttribute("confirmacion", "El autor ha sido registrado correctamente en la base de datos.");
		        request.getRequestDispatcher("admin/altaAutor.jsp").forward(request, response);
		        
			}catch(ParseException pe) {
				request.setAttribute("nombre", request.getParameter("nombreAutor"));
				request.setAttribute("fecha", fechaFormulario);
				procesarError(request, response, pe , "admin/altaAutor.jsp");
			}catch (SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/altaAutor.jsp");
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e) {
			}
			break;


		case "listarAutores":
			
			ArrayList<Autor> listadoAutores = new ArrayList<Autor>();
			DaoAutor daoAu = new DaoAutor();
			
			try {
				
				listadoAutores = daoAu.listadoAutores();
				
				request.setAttribute("listadoAutores", listadoAutores);
				request.getRequestDispatcher("admin/listadoAutor.jsp").forward(request, response);				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			

			case "listadoSociosMorosos":
			
			ds = new DaoSocio();
			try {
				
				listadoSociosMorosos = ds.listadoSociosMorosos();
				request.setAttribute("listadoSociosMorosos", listadoSociosMorosos);
				
				request.getRequestDispatcher("admin/listadoSociosMorosos.jsp").forward(request, response);
			}catch(SQLException se){
				try {
					procesarErrorSql(request, response, se, "admin/listadoSociosMorosos.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e) {

			}
			
		case "listarLibrosEnDeuda":
			ds = new DaoSocio();
			try {
				
				//Volvemos a listar la primera consulta para que aparecza otra vez
				
				listadoSociosMorosos = ds.listadoSociosMorosos();
				request.setAttribute("listadoSociosMorosos", listadoSociosMorosos);
				
				
				//Aqui parseamos el idsocio del elemento editar de listar librosEnDeuda&IDSOCIO
				
				int idsocio = Integer.parseInt(request.getParameter("IDSOCIO"));
				
				
				//Hacemos la nueva consulta que queremos mostrar
				
				ArrayList<Libro>listaLibrosDeMorosos = new ArrayList<Libro>();
				listaLibrosDeMorosos = ds.listarLibrosDeMorosos(idsocio);
				
				
				//Aqui relacionamos las variables del for each y el value "id" con la variable parseada para que sepa el socio que estamos seleccionando
				
				request.setAttribute("listaLibrosDeMorosos", listaLibrosDeMorosos);
				request.setAttribute("id", idsocio);
				
				request.getRequestDispatcher("admin/listadoSociosMorosos.jsp").forward(request, response);
				
			}catch(SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/listadoSociosMorosos.jsp");
				} catch (Exception e) {
				}
			}catch(Exception e) {

			}
			
			break;
			
			
		case "altaPrestamo":
			
			DaoPrestamo daoP = new DaoPrestamo();
			Prestamo p = new Prestamo();
			
			int idejemplar = Integer.parseInt(request.getParameter("IDEJEMPLAR"));
			long idsocio = Long.parseLong(request.getParameter("IDSOCIO"));

			try {

				p.setIDEJEMPLAR(idejemplar);
				p.setIDSOCIO(idsocio);
				daoP.altaPrestamo(p);
				
				request.setAttribute("confirmacion", "El Prestamo ha sido registrado correctamente en la base de datos.");
				request.getRequestDispatcher("admin/altaPrestamo.jsp").forward(request, response);

				//request.getRequestDispatcher("admin/confirmacion.jsp").forward(request, response);

			} catch(EjemplarBaja eB){ 
				procesarError(request, response, eB, "admin/altaPrestamo.jsp");
			}catch (SQLException se) {
				try {
					procesarErrorSql(request, response, se, "admin/altaPrestamo.jsp");
				}catch (Exception e) {
				}
			}catch(Exception e) {
				procesarError(request, response, e, "admin/altaPrestamo.jsp");
			}
			break;


		
		case "altaLibro":
			
			Libro l = new Libro();
			DaoLibro daol = new DaoLibro();
			
			String isbn = request.getParameter("ISBN");
			String titulo = request.getParameter("TITULO");
			long idautor = Long.parseLong(request.getParameter("IDAUTOR")); 
		
		try {
			
			l.setISBN(isbn);
			l.setTITULO(titulo);
			l.setIDAUTOR(idautor);
			
			daol.insertaLibro(l);
			
			request.setAttribute("confirmacion", "El Libro ha sido registrado correctamente en la base de datos.");
			request.getRequestDispatcher("admin/altaLibro.jsp").forward(request, response);
			
			} catch(EjemplarBaja eB){ 
				procesarError(request, response, eB, "admin/altaLibro.jsp");
			}catch (SQLException se) {
					try {
						procesarErrorSql(request, response, se, "admin/altaLibro.jsp");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}catch(Exception e) {
				procesarError(request, response, e, "admin/error.jsp");
			}
			
		break;
		
		case "redireccionConsultarLibros":
			response.sendRedirect("admin/consultaLibros.jsp");
			break;
		case "consultarLibro":
			
			String formaDeBusqueda = request.getParameter("formaBusqueda");
			String busqueda = request.getParameter("busqueda");
			
			try {
				
				daol = new DaoLibro();			
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
				
				request.getRequestDispatcher("admin/consultaLibros.jsp").forward(request, response);
			}catch(SQLException sqe) {
				
			}catch(Exception ee) {
				
			}
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
