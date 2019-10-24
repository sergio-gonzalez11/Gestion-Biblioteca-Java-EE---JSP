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
		<c:if test="${confirmacion!=null}">
			<div id="divconfirmacion">
				<p>
					<c:out value="${confirmacion}"></c:out>
				</p>
			</div>
		</c:if>
		<c:if test="${requestScope.error!=null}">
			<div class="diverror">
				<p>
					<c:out value="${error}"></c:out>
				</p>
			</div>
		</c:if>
		
		<div class="formulario">
			<form action="${pageContext.request.contextPath}/ControllerSocio">

				<input type="hidden" name="operacion" value="modificarDatosPersonales"> 
				<input type="hidden" name="IDSOCIO" value="${socio.getIDSOCIO()}"> 
				
				<label for="NOMBRE">Nombre a modificar</label> 
				<input type="text" required="required" value="${socio.getNOMBRE()}" name="nuevoNombre">
				
				<label for="DIRECCION">Dirección</label> 
				<input type="text" required="required" value="${socio.getDIRECCION()}" name="nuevaDireccion"> 
				<input type="submit"value="Modificar Datos">
				
			</form>
		</div>
	</div>
</body>
</html>