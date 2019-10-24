<%@taglib uri="http://java.sun.com/jstl/core" prefix="c1"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta libro</title>
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
		<jsp:directive.include file="../WEB-INF/menu.jspf"/>
		</div>
		<div class="formulario">
			 <form action="${pageContext.request.contextPath}/ControllerAdmin">
			 	<fieldset>
			 		<legend><img src="${pageContext.request.contextPath}/resources/img/logo.gif" width="20px"> Busqueda sencilla</legend>
			 		 	
			 		 	<table border="0">
							<tr>
								<td width=50%>Texto a buscar: </td>
								<td width=50%>
        							<input type="text" required="required" name="busqueda" value="${busqueda}"></td>
        							<input type="hidden" name="operacion" value="consultarLibro">
        						</td>
							</tr>
							<tr>	
								<td width=50%>Seleccione una opcion</td> 
								<td width=50%>
									<select name="formaBusqueda" required="required" >
         								<option value="TITULO">TITULO</option>
           								<option value="NOMBREAUTOR">NOMBREAUTOR</option>
          								<option value="ISBN">ISBN</option>
       								</select>
       							</td> 
							</tr>
						</table>
						<br>
						<input type="submit" value="Buscar">
        		</fieldset>
      		</form>
		<c:if test="${not empty lista}">
				<div class="encabezadoTabla" align="center">
					<font color:="#FFFFFF">
					<h2>Consulta de <c:out value="${busqueda}"></c:out></h2>
					</font>
				</div>
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>TITULO</th>
							<th>AUTOR</th>
							<th>EJEMPLARES</th>
							<th>EN PRESTAMO</th>
							<th>DISPONIBLES</th>
						</tr>
							<c:forEach var="libro" items="${lista}">
								<tr>
									<td width="25%">${libro.TITULO}</td>
									<td width="25%">${libro.NOMBREAUTOR}</td>
									<td width="25%">${libro.TOTALES}</td>
									<td width="25%">${libro.PRESTADOS}</td>
									<td width="25%">${libro.DISPONIBLES}</td>
									
								</tr>
							</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
		</div>
		</body>
	</html>