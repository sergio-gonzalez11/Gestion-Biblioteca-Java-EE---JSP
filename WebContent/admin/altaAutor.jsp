<%@taglib uri="http://java.sun.com/jstl/core" prefix="c1"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta Autor</title>
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
							
								<legend><img src="${pageContext.request.contextPath}/resources/img/logo.gif" width="20px"> Insertar Nuevo Autor</legend> 
								
								<table border="0">
									<tr>
										<td width=50%>nombre</td> 
										<td width=50%><input type="text" name="NOMBRE"/></td> 
									</tr>
									
									<tr>
										<td width=50%>fecha de nacimiento</td> 
										<td width=50%><input type="text" name="FECHANACIMIENTO"/><a style="color:#FF0000">(dd-MM-yy)</a></td> 
									</tr>
								
								</table>
								
								<input class="boton" type="hidden" value="altaAutor" name="operacion" />
								
								<input class="boton" type="submit" value="Darme de alta"/>
							
							</fieldset>		
						</form>
			<jsp:directive.page />
		</div>
	</body>
</html>