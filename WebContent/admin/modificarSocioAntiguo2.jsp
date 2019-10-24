<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
<title>Biblioteca</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf"/>
		</div>	
	<%--<article>--%>
		<div class="formulario">
			<form action="${pageContext.request.contextPath}/ControllerAdmin">
				
				<label for="NOMBRE">Nombre a modificar</label>
				<input type="text" value="${socio.NOMBRE}" name="nuevoNombre">
				
				<label for="DIRECCION">Dirección</label>
				<input type="text" value="${socio.DIRECCION}" name="nuevaDireccion">
				
				<input type="submit" value="Modificar">
				<input type="hidden" name="operacion" value="modificarSocio">
				<input type="hidden" name="IDSOCIO" value="${socio.IDSOCIO}">
			</form>
		</div>
	<%-- </article> --%>
	</div>
</body>
</html>