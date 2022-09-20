 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String message="";
	
	if(request.getParameter("login") != null ){
		if(request.getParameter("adminPassword").equals("123")){
			session.setAttribute("isAdmin", "true");
			response.sendRedirect("admin/CategorySummary.jsp");
		}
		else{
			message = "Şifre Yanlış!";
		}
	}
		
	
	
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Giriş Yap</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>
		
		<main>
			<h2>Giriş Yap</h2>
			
				<%=message%>
				<form action="" accept-charset="utf-8">
					<table>
						<tbody>
							<tr>
								<td><label>Şifre:</label></td>
								<td><input type="password" name="adminPassword" /></td>
							</tr>

							<tr>
								<td><label></label></td>
								<td><input type="submit" value="Giriş Yap" name="login"
									class="button" /></td>
							</tr>
							<tr>
								<td><label></label></td>
							</tr>
						</tbody>
					</table>
				</form>
			<div style="clear: both;"></div>
		
		</main>
	</div>
	<br/>
	
</body>
</html>