<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta libro</title>
<jsp:directive.include file="../WEB-INF/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<div id="header" ></div>
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
					
						<form action="${pageContext.request.contextPath}/ControllerAdmin" method="post" class="form" id="tabla">
							<fieldset>
							
								<legend><img src="${pageContext.request.contextPath}/resources/img/logo.gif" width="20px"> Nuevo Libro</legend> 
								
								<table border="0">
									<tr>
										<td width=50%>ISBN</td> 
										<td width=50%><input type="text" name="ISBN"/></td> 
									</tr>
									<tr>
										<td width=50%>TITULO</td> 
										<td width=50%><input type="text" name="TITULO"/></td> 
									</tr>
									<tr>
										<td width=50%>IDAUTOR</td> 
										<td width=50%><input type="text" name="IDAUTOR"/></td> 
									</tr>
								</table>
								
								<br>
								
								<input class="boton" type="hidden" value="altaLibro" name="operacion" />
								
								<input class="boton" type="submit" value="Aceptar"/>
							
							</fieldset>		
						</form>
			<jsp:directive.page />
		</div>
	</body>
</html>