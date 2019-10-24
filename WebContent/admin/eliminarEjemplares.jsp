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
					
		<div class="formulario">
			 <form action="${pageContext.request.contextPath}/Controller">
			 	<fieldset>
			 		<legend><img src="${pageContext.request.contextPath}/resources/img/logo.gif" width="20px"> Busqueda sencilla</legend>
			 		 	
			 		 	<table border="0">
							<tr>
								<td width=50%>Texto a buscar: </td>
								<td width=50%>
        							<input type="text" required="required" name="busqueda" value="${busqueda}"></td>
        							<input type="hidden" name="operacion" value="consultarEjemplar">
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
							<th>OPERACIONES</th>
						</tr>
							<c:forEach var="libro" items="${lista}">
								<tr>
									<td width="25%">${libro.TITULO}</td>
									<td width="25%">${libro.NOMBREAUTOR}</td>
									<td width="25%"><a href="${pageContext.request.contextPath}/Controller?operacion=listarEjemplares&ISBN=${libro.ISBN}&TITULO=${libro.TITULO}"> Eliminar</a></td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</c:if>
			<c:if test="${listaEjemplares != null}">
					<c:if test="${empty listaEjemplares}">
						<div class="error">
							<p>El libro <c:out value="${titulo}"></c:out> no tiene ningún ejemplar para eliminar</p>
						</div>
					</c:if>
					<c:if test="${not empty listaEjemplares}">
						<div class="encabezadoTabla">
							<h2>Ejemplares disponibles de: <c:out value="${titulo}"></c:out></h2>
						</div>
				<div id="tabla">
					<table id="tabla" border="4px solid">
						<tr>
							<th>IDEJEMPLAR</th>
							<th>ISBN</th>
							<th>OPERACIONES</th>
						</tr>
							<c:forEach var="e" items="${listaEjemplares}">
								<tr>
									<td width="25%">${e.IDEJEMPLAR}</td>
									<td width="25%">${e.ISBN}</td>
									<td width="25%"><a href="${pageContext.request.contextPath}/Controller?operacion=eliminarEjemplar&IDEJEMPLAR=${e.IDEJEMPLAR}"> Eliminar</a></td>
								</tr>
							</c:forEach>
					</table>
				</div>
				</c:if>
		</c:if>
		</div>
		</div>
		</body>
	</html>