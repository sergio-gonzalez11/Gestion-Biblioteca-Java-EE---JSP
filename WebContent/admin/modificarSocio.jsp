<%@taglib uri="http://java.sun.com/jstl/core" prefix="c1"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EDITAR SOCIO</title>
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
		<jsp:directive.include file="../WEB-INF/menu.jspf"/>
					</div>
					<c:if test="${requestScope.error!=null}"> <%--Controlar el error de un libro dado de baja--%>
						<div id="diverror">
							<p><c:out value="${error}"></c:out></p>
								</div>
					</c:if>
					<c:if test="${requestScope.confirmacion!=null}"> 
						<div id="divconfirmacion">
							<p><c:out value="${confirmacion}"></c:out></p>
								</div>
					</c:if>
		<c:if test="${not empty listadoSocios}">
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>IDSOCIO</th>
							<th>EMAIL</th>
							<th>NOMBRE</th>
							<th>DIRECCION</th>
							<th>ACTUALIZAR</th>
							<th>BORRAR</th>
						</tr>
							<c:forEach var="socios" items="${listadoSocios}">
								<tr>
									<td width="5%">${socios.IDSOCIO}</td>
									<td width="35%">${socios.EMAIL}</td>
									<td width="20%">${socios.NOMBRE}</td>
									<td width="20%">${socios.DIRECCION}</td>
									<td width="15%"><a href="${pageContext.request.contextPath}/Controller?operacion=mSocioBuscar&IDSOCIO=${socios.IDSOCIO}">Editar</a></td>
									<td width="15%"><a href="${pageContext.request.contextPath}/Controller?operacion=socioEliminar&EMAIL=${socios.EMAIL}">Eliminar</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${not empty socios}">
				<div class="encabezadoTabla" align="center">
					<font color="#FFFFFF";>
					<h2>El socio <c:out value="${id} ${socios.NOMBRE}" ></c:out></h2>
					</font>
				</div>
				<c:if test="${socios != null}">
					<form action="${pageContext.request.contextPath}/Controller?operacion=mSocioFinal" method="post" class="form" id="tabla">
						<table border="0" align="center">
        					<tr>
        						<td width=50%>IDSOCIO</td> 
        						<td width=50%><input type="text" disabled value="${socios.getIDSOCIO()}"></td>
        					</tr>
        					<tr>
        						<td width=50%>EMAIL</td>
        						<td width=50%><input type="text" disabled value="${socios.getEMAIL()}"></td>
        					</tr>
        					<tr>
        						<td width=50%>NOMBRE</td>
        						<td width=50%><input type="text" value="${socios.getNOMBRE()}" name="nuevoNombre"></td>
        					</tr>	
        					<tr>
        						<td width=50%>DIRECCION</td>
        						<td width=50%><input type="text" value="${socios.getDIRECCION()}" name="nuevaDireccion"></td>	
        					</tr>
        					
        					<tr>
        						<td width=50%>
        							<input type="hidden" name="IDSOCIO" value="${socios.IDSOCIO}">
        							<input type="hidden" name="operacion" value="mSocioFinal">
        							<input type="submit" value="Modificar">
        						</td>
        					</tr>	
    					</table>
    					<br></br>
    				</form>
				</c:if>
			</c:if>
		</body>
	</html>