<%@taglib uri="http://java.sun.com/jstl/core" prefix="c1"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LISTADO DE AUTORES</title>
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
		<jsp:directive.include file="../WEB-INF/menu.jspf"/>
		</div>
		<c:if test="${not empty listadoSociosMorosos}">
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>IDSOCIO</th>
							<th>EMAIL</th>
							<th>NOMBRE</th>
							<th>LIBROS SIN DEVOLVER</th>
						</tr>
							<c:forEach var="socios" items="${listadoSociosMorosos}">
								<tr>
									<td width="10%">${socios.IDSOCIO}</td>
									<td width="35%">${socios.EMAIL}</td>
									<td width="20%">${socios.NOMBRE}</td>
									<td width="20%"><a href="${pageContext.request.contextPath}/ControllerAdmin?operacion=listarLibrosEnDeuda&IDSOCIO=${socios.IDSOCIO}"> Libros pendientes</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${not empty listaLibrosDeMorosos}">
				<div class="encabezadoTabla" align="center">
					<font color:="#FFFFFF">
					<h2>Lista de libros que debe el usuario <c:out value="${id}"></c:out></h2>
					</font>
				</div>
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>TITULO DEL LIBRO</th>
							<th>ID DEL EJEMPLAR</th>
							<th>DIAS DE RETRASO</th>
						</tr>
							<c:forEach var="libro" items="${listaLibrosDeMorosos}">
								<tr>
									<td width="50%">${libro.ISBN}</td>
									<td width="25%">${libro.TITULO}</td>
									<td width="25%">${libro.IDAUTOR}</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</c:if>
		</body>
	</html>