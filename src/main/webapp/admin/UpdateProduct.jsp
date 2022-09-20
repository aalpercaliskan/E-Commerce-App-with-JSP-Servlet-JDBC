<%@page import="com.godoro.web.client.ProductClientManager"%>
<%@page import="com.godoro.database.entity.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	String message = "";
	long productId = Long.parseLong(request.getParameter("productId"));
	long categoryId = Long.parseLong(request.getParameter("categoryId"));
	
	ProductClientManager productClientManager = new ProductClientManager();
	Product product = productClientManager.find(productId);
	
	String productName = product.getProductName();
	double salesPrice = product.getSalesPrice();
	if(request.getParameter("save") != null){
		productName = request.getParameter("productName");
		String stringSalesPrice =request.getParameter("salesPrice");
		if(productName.length()>0  && stringSalesPrice.length()>0){
			salesPrice = Double.parseDouble(stringSalesPrice);
			
			product.setProductName(productName);
			product.setSalesPrice(salesPrice);
			message =productClientManager.update(product);
			
		}
		else{
			message = "Geçerli değerler giriniz!";
		}
	}
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Ürün Güncelle</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<h2>Ürün Güncelle</h2>
	
	<div id="container">
		<form action="" method="post" accept-charset="utf-8">
			<table>
				<tbody>
					<tr>
						<td><label>Ürün İsmi:</label></td>
						<td><input type="text" name="productName" 
								   value="<%=productName %>" /></td>
					</tr>
					<tr>
						<td><label>Satış Fiyatı:</label></td>
						<td><input type="text" name="salesPrice" 
								   value="<%=salesPrice%>" /></td>
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
		<c:url var="productSummaryLink" value="ProductSummary.jsp">
			  <c:param name="categoryId" value="<%=Long.toString(categoryId)%>"></c:param>
		</c:url>
		<p>
			<a href="${productSummaryLink}">Ürün Özetine Geri Dön</a>
		</p>
		
	</div>
	
	
</body>
</html>