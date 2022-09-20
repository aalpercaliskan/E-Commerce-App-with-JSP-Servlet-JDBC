<%@page import="com.godoro.database.entity.Address"%>
<%@page import="com.godoro.web.client.AddressClientManager"%>
<%@page import="com.godoro.web.client.ConsumerClientManager"%>
<%@page import="com.godoro.database.entity.Consumer"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	ConsumerClientManager consumerClientManager = new ConsumerClientManager();
	long consumerId = Long.parseLong(request.getParameter("consumerId"));
	Consumer consumer = consumerClientManager.find(consumerId);
	
	String consumerName = consumer.getConsumerName();
	String consumerPassword = consumer.getConsumerPassword();
	
	AddressClientManager addressClientManager = new AddressClientManager();
	Address address = addressClientManager.findByConsumer(consumerId);
	String provinceName = "";
	String addressLine1 = "";
	String addressLine2 = "";
	if(address != null){
		provinceName = address.getProvinceName();
		addressLine1 = address.getAddressLine1();
		addressLine2 = address.getAddressLine2();
	}
	
	
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Hesap Detayı</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<h2>Hesap Detayı</h2>
   	
	<div id="container">
		<table>
			<tbody>
				<tr>
					<td><label>Kullanıcı Adı:</label></td>
					<td><input type="text" name="consumerName" value="<%=consumerName%>" readonly/></td>
				</tr>
				<tr>
					<td><label>Şifre:</label></td>
					<td><input type="text" name="consumerPassword" value="<%=consumerPassword%>" readonly/></td>
				</tr>
				
				<tr>
					<td><label>İl:</label></td>
					<td><input type="text" name="provinceName" value="<%=provinceName%>" readonly/></td>
				</tr>
					
				<tr>
					<td><label>Adres Satırı 1:</label></td>
					<td><input type="text" name="addressLine1" value="<%=addressLine1%>" readonly/></td>
				</tr>
					
				<tr>
					<td><label>Adres Satırı 2:</label></td>
					<td><input type="text" name="addressLine2" value="<%=addressLine2%>" readonly/></td>
				</tr>
					
			</tbody>
		</table>
		<div style="clear: both;"></div>
		
	</div>
	
	
</body>
</html>