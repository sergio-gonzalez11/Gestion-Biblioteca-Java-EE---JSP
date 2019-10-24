<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prestamo</title>
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
							
								<legend><img src="${pageContext.request.contextPath}/resources/img/logo.gif" width="20px"> Nuevo Prestamo</legend> 
								
								<table border="0">
									<tr>
										<td width=50%>IDEJEMPLAR</td> 
										<td width=50%><input type="text" name="IDEJEMPLAR" required="required"/></td> 
									</tr>
									
									<tr>
										<td width=50%>IDSOCIO</td> 
										<td width=50%><input type="text" name="IDSOCIO" required="required"/></td> 
									</tr>
								</table>
								
								<br>
								
								<input class="boton" type="hidden" value="altaPrestamo" name="operacion" />
								
								<input class="boton" type="submit" value="Registrar Prestamo"/>
							
							</fieldset>		
						</form>
			<jsp:directive.page />
		</div>
	</body>
</html>