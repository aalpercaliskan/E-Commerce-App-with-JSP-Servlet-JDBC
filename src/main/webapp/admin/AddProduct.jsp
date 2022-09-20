<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@page import="com.godoro.database.entity.Category"%>
<%@page import="com.godoro.database.entity.Product"%>
<%@page import="com.godoro.web.client.ProductClientManager"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	String message="";
	long categoryId = Long.parseLong(request.getParameter("categoryId"));
	if(request.getParameter("add") != null){
		if(request.getParameter("productName").length()>0 &&  request.getParameter("salesPrice").length()>0){
			String productName = request.getParameter("productName");
			double salesPrice = Double.parseDouble(request.getParameter("salesPrice"));
			
			ProductClientManager productClientManager = new ProductClientManager();
			Product product = new Product(0,productName, salesPrice);
			CategoryClientManager categoryClientManager = new CategoryClientManager();
			Category category = categoryClientManager.find(categoryId);
			
			product.setCategory(category);
			message = productClientManager.insert(product);
			
			
		}
		else{
			message = "Geçerli değerler giriniz!";
		}
	}
	
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Ürün Ekle</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	 <jsp:include page="Menu.jsp"></jsp:include>
	<h2>Ürün Ekle</h2>
	
	<div id="container">	
		<form action="" method="post" accept-charset="utf-8">
			<table>
				<tbody>
					<tr>
						<td><label>Ürün İsmi:</label></td>
						<td><input type="text" name="productName" /></td>
					</tr>
					<tr>
						<td><label>Satış Fiyatı:</label></td>
						<td><input type="text" name="salesPrice" /></td>
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
		
		<c:url var="productSummaryLink" value="ProductSummary.jsp">
			  <c:param name="categoryId" value="<%=Long.toString(categoryId)%>"></c:param>
		</c:url>
		<p>
			<a href="${productSummaryLink}">Ürün Özetine Geri Dön</a>
		</p>
		
	</div>
	
	
</body>
</html>