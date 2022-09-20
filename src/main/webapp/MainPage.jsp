<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.godoro.web.client.ProductClientManager"%>
<%@page import="com.godoro.database.entity.Product"%>
<%@page import="com.godoro.database.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.godoro.web.client.CategoryClientManager"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	ProductClientManager productClientManager = new ProductClientManager();
	CategoryClientManager categoryClientManager = new CategoryClientManager();
	
	List<Category> categoryList = categoryClientManager.list();
	HashMap<String,List<Product>> map=new HashMap<String,List<Product>>();
	List<Product> productList = null;
	for(Category category : categoryList){
		
		productList = productClientManager.list(category.getCategoryId());
		map.put(category.getCategoryName(),productList);
		
	}
	
	pageContext.setAttribute("map", map);
	
	
%>

<html>
<head>
<meta charset="UTF-8">
<title>Alışveriş Sitesi</title>
	<style><%@ include file="css/style.css"%></style>
</head>
<body>

	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
			<c:forEach var="entry" items="<%= map.entrySet() %>">
				<h2>${entry.key}</h2>
				<table>
					<tr>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="product" items="${entry.value}">
						<c:url var="viewLink" value="ProductView.jsp">
							<c:param name="productId" value="${product.productId}"></c:param>
							<c:param name="categoryId" value="${product.category.categoryId}"></c:param>
						</c:url>

						<tr>
							<td>${product.productName}</td>
							<td>${product.salesPrice}TL</td>
							<td><a href="${viewLink}">Detay</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:forEach>

		</main>
		<footer><jsp:include page="Footer.jsp"></jsp:include></footer>
	</div>
</body>
</html>