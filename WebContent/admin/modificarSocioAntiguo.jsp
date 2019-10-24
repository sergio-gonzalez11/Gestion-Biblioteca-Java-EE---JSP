<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biblioteca</title>
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf"/>
		</div>
			<%-- <article> --%>
			<div class="formulario">
				<form  method="post" action="${pageContext.request.contextPath}/ControllerAdmin">
					<label for="nombreSocio">Nombre del socio:</label> 
						<input name="frmbusquedanombre" type="text" id="frmbusquedanombre" value="${iniciales}" size="25" maxlength="40">
						<input name="operacion" type="hidden" id="operacion" value="busquedaSocioAModificar">
						<input type="submit" value="Buscar socio">
				</form>
			</div>
			<c:if test="${(listadoSocios!=null)}">
				<c:if test="${(not empty listadoSocios)}">
					<div class="tabla">
						<table id="tabla" border="4px solid">
							<tr>
								<th>Nº Socio</th>
								<th>Email</th>
								<th>Nombre</th>
								<th>Dirección</th>
								<th>Acciones</th>
							</tr>
							<c:forEach var="socio" items="${listadoSocios}">
								<tr>
									<td>${socio.IDSOCIO}</td>
									<td>${socio.EMAIL}</td>
									<td>${socio.NOMBRE}</td>
									<td>${socio.DIRECCION}</td>
									<td>
										<form action="${pageContext.request.contextPath}/ControllerAdmin" method="post">
											<input name="operacion" type="hidden" value="socioAModificar">
											<input name="IDSOCIO" type="hidden" value="${socio.IDSOCIO}">
											<input type="Submit" value="Editar">
										</form>
									</td>
								</tr>
							</c:forEach>
							</table>
							</div>
							</c:if>
							</c:if>
							</div>
							</body>
							</html>