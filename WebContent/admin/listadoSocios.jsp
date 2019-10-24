<%@taglib uri="http://java.sun.com/jstl/core" prefix="c1"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LISTADO DE SOCIOS</title>
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
		<jsp:directive.include file="../WEB-INF/menu.jspf"/>
		</div>
		<c:if test="${not empty listadoSocios}">
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>IDSOCIO</th>
							<th>EMAIL</th>
							<th>NOMBRE</th>
							<th>DIRECCION</th>
						</tr>
							<c:forEach var="Socio" items="${listadoSocios}">
								<tr>
									<td>${Socio.IDSOCIO}</td>
									<td>${Socio.EMAIL}</td>
									<td>${Socio.NOMBRE}</td>
									<td>${Socio.DIRECCION}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
			</div>
		</body>
	</html>