<%@page import="com.godoro.database.entity.Consumer"%>
<%@page import="com.godoro.web.client.ConsumerClientManager"%>
<%@page import="com.godoro.database.manager.CartProductManager"%>
<%@page import="com.godoro.database.entity.Address"%>
<%@page import="com.godoro.web.client.AddressClientManager"%>
<%@page import="com.godoro.database.entity.Cart"%>
<%@page import="com.godoro.web.client.CartClientManager"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String message = "";
	String provinceName = "";
	String addressLine1 = "";
	String addressLine2 = "";
	long consumerId = (long)session.getAttribute("consumerId");
	AddressClientManager addressClientManager = new AddressClientManager();
	Address address = addressClientManager.findByConsumer(consumerId);
	if(address != null){
		provinceName = address.getProvinceName();
		addressLine1 = address.getAddressLine1();
		addressLine2 = address.getAddressLine2();
	}
	
	if(request.getParameter("save") != null){
		
		if(request.getParameter("provinceName") == "" ||  request.getParameter("addressLine1") =="" ){
			message = "Geçerli adres bilgisi giriniz!";
			
		}
		else{
			provinceName = request.getParameter("provinceName");
			addressLine1 = request.getParameter("addressLine1");
			addressLine2 = request.getParameter("addressLine2");
			
			address = addressClientManager.findByConsumer(consumerId);
			if(address != null){
				address.setProvinceName(provinceName);
				address.setAddressLine1(addressLine1);
				address.setAddressLine2(addressLine2);
				addressClientManager.update(address);
			}
			else{
				address = new Address(0,provinceName,addressLine1,addressLine2);
				ConsumerClientManager  consumerClientManager = new ConsumerClientManager();
				Consumer consumer = consumerClientManager.find(consumerId);
				address.setConsumer(consumer);
				addressClientManager.insert(address);
			}
			
			
			CartProductManager cartProductManager = new CartProductManager();
			cartProductManager.deleteAtCart((long)session.getAttribute("cartId"));
			CartClientManager cartClientManager = new CartClientManager();
			Cart cart = cartClientManager.find((long)session.getAttribute("cartId"));
			cart.setTotalAmount(0);
			cartClientManager.update(cart);
			
			response.sendRedirect("CartView.jsp");
		}
	
		
	}
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Sipariş Ver</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
			<h2>Adres Bilgisi</h2>

				<form action="" accept-charset="utf-8">

					<table>
						<tbody>
							<tr>
								<td><label>İl:</label></td>
								<td><input type="text" name="provinceName"
									value="<%=provinceName%>" /></td>
							</tr>
							<tr>
								<td><label>Adres Satırı 1:</label></td>
								<td><input type="text" name="addressLine1"
									value="<%=addressLine1%>" /></td>
							</tr>

							<tr>
								<td><label>Adres Satırı 2:</label></td>
								<td><input type="text" name="addressLine2"
									value="<%=addressLine2%>" /></td>
							</tr>
							<tr>
								<td><label></label></td>
								<%=message%>
							</tr>

							<tr>
								<td><label></label></td>
								<td><input type="submit" value="Sipariş Ver" name="save"
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

</body>
</html>