<%@page import="com.godoro.database.entity.CartProduct"%>
<%@page import="java.util.List"%>
<%@page import="com.godoro.database.entity.Cart"%>
<%@page import="com.godoro.web.client.CartClientManager"%>
<%@page import="com.godoro.web.client.CartProductClientManager"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	boolean isLogin = false;
	boolean isEmpty = true;
	CartProductClientManager cartProductClientManager = new CartProductClientManager();
	if(session.getAttribute("cartId") != null){
		long cartId = (long)session.getAttribute("cartId");
		List<CartProduct> cartProductList = cartProductClientManager.list(cartId);
		if(!cartProductList.isEmpty()){
			isEmpty = false;
			
		}
		CartClientManager cartClientManager = new CartClientManager();
		Cart cart = cartClientManager.find(cartId);
		pageContext.setAttribute("totalAmount", cart.getTotalAmount());
		pageContext.setAttribute("cartProductList", cartProductList);
		isLogin = true;
	}
	pageContext.setAttribute("isLogin", isLogin);
	pageContext.setAttribute("isEmpty", isEmpty);
	

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sepetim</title>
	<style><%@ include file="css/menu.css"%></style>
	<style><%@ include file="css/style.css"%></style>
	
</head>
<body>
	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
	
	<c:if test="${isEmpty}">
		<td><h3>Sepetinizde ürün yok.</h3></td>
		
		<c:if test="${!isLogin}">
			<td><input type="submit" value="Giriş Yap" 
				       onclick="window.location.href='ConsumerLogin.jsp'; return false;" class="button" /></td>
			<td><input type="submit" value="Üye Ol" 
				       onclick="window.location.href='ConsumerRegister.jsp'; return false;" class="button" /></td>
		</c:if>
	</c:if>
	
	<c:if test="${!isEmpty && isLogin}">
		<h2>Sepetim</h2>
		<div id="container">
			<div id="content">
				<input type="button" value="Sipariş Ver" 
				   onclick="window.location.href='Checkout.jsp'; return false;"
				   class="checkout-button"
				/>
				<table>
					<tr>
						<th>Ürün İsmi</th>
						<th>Satış Fiyatı</th>
						<th>Adet</th>
						<th>Toplam Tutar</th>
						<th>Aksiyon</th>
					</tr>
					
					<c:forEach var="tempCartProduct" items="${cartProductList}">
						
						<c:url var="productLink" value="ProductView.jsp">
							<c:param name="productId" value="${tempCartProduct.product.productId}"></c:param>
							<c:param name="categoryId" value="${tempCartProduct.product.category.categoryId}"></c:param>
						</c:url>	
						
						<c:url var="updateLink" value="CartView.jsp">
							<c:param name="salesQuantity" value="${salesQuantity}"></c:param>
							<c:param name="productId" value="${tempCartProduct.product.productId}"></c:param>
						</c:url>
						
						<c:url var="deleteLink" value="DeleteCartProduct.jsp">
							<c:param name="cartProductId" value="${tempCartProduct.cartProductId}"></c:param>
						</c:url>			
					
						<tr>
							<td>
							<a href="${productLink}">${tempCartProduct.product.productName}</a>
							</td>
							<td>${tempCartProduct.salesPrice}</td>
			 				<td>${tempCartProduct.salesQuantity}</td> 
							<td>${tempCartProduct.salesPrice * tempCartProduct.salesQuantity}</td>
							<td>
								<a href="${deleteLink}" 
								   onclick="if(!(confirm('Silmek istediğine emin misin?'))) return false">Sil</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<h3>Toplam Tutar: ${totalAmount} TL</h3>
			</div>
		</div>
	</c:if>
	</main>
		<footer><jsp:include page="Footer.jsp"></jsp:include></footer>
	</div>


</body>
</html>