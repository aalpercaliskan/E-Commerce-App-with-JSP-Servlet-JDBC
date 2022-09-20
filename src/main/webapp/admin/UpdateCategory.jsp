<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@page import="com.godoro.database.entity.Category"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	String message = "";
	long categoryId = Long.parseLong(request.getParameter("categoryId"));
	
	CategoryClientManager categoryClientManager = new CategoryClientManager();	
	Category category = categoryClientManager.find(categoryId);
	String categoryName = category.getCategoryName();
	if(request.getParameter("save") != null){
		categoryName = request.getParameter("categoryName");
		if(categoryName.length()>0){
			category.setCategoryName(categoryName);
			message =categoryClientManager.update(category);
			
		}
		else{
			message = "Geçerli bir isim giriniz!";
		}
	}
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Kategori Güncelle</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<h2>Kategori Güncelle</h2>
	
	<div id="container">
		
	
		<form action="" method="post" accept-charset="utf-8">
			<!--  <input type="hidden" name="command" value="Ekle"/>-->
			<table>
				<tbody>
					<tr>
						<td><label>Kategori İsmi:</label></td>
						<td><input type="text" name="categoryName" 
								   value="<%=categoryName %>" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<%=message %>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Kaydet" name="save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		<p>
			<a href="CategorySummary.jsp">Kategori Özetine Geri Dön</a>
		</p>
		
	</div>
	
	
</body>
</html>