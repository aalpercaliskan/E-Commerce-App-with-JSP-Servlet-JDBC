<%@page import="com.godoro.database.entity.CartProduct"%>
<%@page import="com.godoro.database.entity.Cart"%>
<%@page import="com.godoro.web.client.CartClientManager"%>
<%@page import="com.godoro.web.client.CartProductClientManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.godoro.database.entity.Category"%>

<%
	long cartProductId = Long.parseLong(request.getParameter("cartProductId"));	
	CartProductClientManager CartProductClientManager = new CartProductClientManager();
	CartProduct cartProduct = CartProductClientManager.find(cartProductId);
	CartProductClientManager.delete(cartProductId);
	
	CartClientManager cartClientManager = new CartClientManager();
	Cart cart = cartClientManager.find((long)session.getAttribute("cartId"));
	cart.decreaseTotalAmount(cartProduct.getCartProductTotalAmount());
	cartClientManager.update(cart);
	response.sendRedirect("CartView.jsp");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

</body>
</html>