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
		<c:if test="${not empty listadoAutores}">
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>IDAUTOR</th>
							<th>NOMBRE</th>
							<th>FECHA DE NACIMIENTO</th>
						</tr>
							<c:forEach var="autor" items="${listadoAutores}">
								<tr>
									<td width="10%">${autor.IDAUTOR}</td>
									<td width="35%">${autor.NOMBRE}</td>
									<td width="20%">${autor.FECHANACIMIENTO}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
			</div>
		</body>
	</html>