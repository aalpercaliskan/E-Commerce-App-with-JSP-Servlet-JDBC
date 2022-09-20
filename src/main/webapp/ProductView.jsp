<%@page import="com.godoro.database.entity.CartProduct"%>
<%@page import="com.godoro.web.client.CartProductClientManager"%>
<%@page import="com.godoro.database.entity.Cart"%>
<%@page import="com.godoro.web.client.CartClientManager"%>
<%@page import="com.godoro.web.client.ProductClientManager"%>
<%@page import="com.godoro.database.entity.Product"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String message = "";
	
	long productId = Long.parseLong(request.getParameter("productId"));
	long categoryId = Long.parseLong(request.getParameter("categoryId"));

	ProductClientManager productClientManager = new ProductClientManager();
	Product product = productClientManager.find(productId);
	String productName = product.getProductName();
	double salesPrice = product.getSalesPrice();
	
	if (request.getParameter("add") != null) {
		if (session.getAttribute("consumerName") != null) {
			int salesQuantity = Integer.parseInt(request.getParameter("salesQuantity"));
			long cartId = (long)session.getAttribute("cartId");
			CartClientManager cartClientManager = new CartClientManager();
			Cart cart = cartClientManager.find(cartId);
			
			CartProductClientManager cartProductClientManager = new CartProductClientManager();
			CartProduct cartProduct = cartProductClientManager.find(productId, cartId);
			if(cartProduct != null){
				cart.increaseTotalAmount(salesPrice*salesQuantity);
				salesQuantity += cartProduct.getSalesQuantity();
				cartProduct.setSalesQuantity(salesQuantity);
				cartProductClientManager.update(cartProduct);
			}
			else{
				cartProduct = new CartProduct(0, salesQuantity, salesPrice);
				cartProduct.setProduct(product);
				cartProduct.setCart(cart);
				cartProductClientManager.insert(cartProduct);
				cart.increaseTotalAmount(salesPrice*salesQuantity);
			}
			
			cartClientManager.update(cart);
			message = "Sepete Eklendi";
		} 
		else {
			session.setAttribute("returnProductView", true);
			session.setAttribute("productId", productId);
			session.setAttribute("categoryId", categoryId);
			response.sendRedirect("ConsumerLogin.jsp");
		}
		
	}
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Ürün Detayı</title>
	<style><%@ include file="css/style.css"%></style>
	
</head>
<body>
	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>

			<table>
				<tr>
					<th>Ürün İsmi</th>
					<th>Satış Fiyatı</th>
				</tr>
				<tr>
					<td><%=productName %></td>
					<td><%=salesPrice %> TL</td>
				</tr>
			</table>
			<br />
			<br />
			<form action="" method="post" accept-charset="utf-8">
				Ürün Sayısı: <select name="salesQuantity">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select> <br /> <input type="submit" value="Sepete Ekle" name="add"
					class=button /> <label><%=message %></label> <br />
			</form>

			<div style="clear: both;"></div>

			<c:url var="productListLink" value="ProductList.jsp">
				<c:param name="categoryId" value="<%=Long.toString(categoryId)%>"></c:param>
			</c:url>
			<p>
				<br /> <br /> 
				<a href="${productListLink}">İlgili Kategoriye Git</a>
			</p>


		</main>
		
	</div>

</body>
</html>