<%@page import="com.godoro.database.entity.Category"%>
<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	String message="";
	if(request.getParameter("add") != null){
		String categoryName = request.getParameter("categoryName");
		if(categoryName.length()>0){
			CategoryClientManager categoryClientManager = new CategoryClientManager();
			 message = categoryClientManager.insert(new Category(0,categoryName));
		}
		else{
			message = "Geçerli bir isim giriniz!";
		}
	}
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Kategori Ekle</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
 	<jsp:include page="Menu.jsp"></jsp:include>
	
	<h2>Kategori Ekle</h2>
		
	
	<div id="container">
		
		<form action="" method="post" accept-charset="utf-8">
			<table>
				<tbody>
					<tr>
						<td><label>Kategori İsmi:</label></td>
						<td><input type="text" name="categoryName" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<%=message %>
					</tr>
					
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Ekle" name="add" class="add" /></td>
					</tr>
										
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
	</div>
	
	
</body>
</html>