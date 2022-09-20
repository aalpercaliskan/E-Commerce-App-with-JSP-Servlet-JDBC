<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@page import="com.godoro.web.client.ProductClientManager"%>
<%@page import="com.godoro.database.entity.Category"%>
<%@page import = "java.util.List, com.godoro.database.entity.Product"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ProductClientManager productClientManager = new ProductClientManager();
	long categoryId = 0;
	try {
			categoryId = Long.parseLong(request.getParameter("categoryId"));
		}
		catch(Exception e) {
		  	response.sendRedirect("CategorySummary.jsp");
		} 
	List<Product> productList = productClientManager.list(categoryId);
	pageContext.setAttribute("productList", productList);
	CategoryClientManager categoryClientManager = new CategoryClientManager();
	Category category = categoryClientManager.find(categoryId);
	String categoryName = category.getCategoryName();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><%=categoryName %> Ürünleri</title>
	<style><%@ include file="css/style.css"%></style>
</head>
<body>
	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
			<h2><%=categoryName %> Ürünleri</h2>
			<table>
				<tr>
					<th>Ürün İsmi</th>
					<th>Satış Fiyatı</th>
					<th></th>
				</tr>

				<c:forEach var="tempProduct" items="${productList}">

					<c:url var="viewLink" value="ProductView.jsp">
						<c:param name="productId" value="${tempProduct.productId}"></c:param>
						<c:param name="categoryId" value="<%=Long.toString(categoryId)%>"></c:param>

					</c:url>

					<tr>
						<td>${tempProduct.productName}</td>
						<td>${tempProduct.salesPrice}TL</td>
						<td><a href="${viewLink}">Detay</a></td>
					</tr>
				</c:forEach>
			</table>

		</main>
		<footer><jsp:include page="Footer.jsp"></jsp:include></footer>
	</div>
</body>
</html>