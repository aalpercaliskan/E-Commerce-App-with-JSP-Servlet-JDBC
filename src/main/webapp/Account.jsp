<%@page import="com.godoro.database.entity.Address"%>
<%@page import="com.godoro.web.client.AddressClientManager"%>
<%@page import="com.godoro.web.client.ConsumerClientManager"%>
<%@page import="com.godoro.database.entity.Consumer"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	ConsumerClientManager consumerClientManager = new ConsumerClientManager();
	long consumerId = (long)session.getAttribute("consumerId");
	
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
	
	String message = "";
	if(request.getParameter("save") != null){
		if(request.getParameter("consumerName") == ""){
			message = "Kullanıcı adı boş bırakılamaz!";
		}
		else if(request.getParameter("consumerPassword") == ""){
			message = "Şifre boş bırakılamaz!";
		}
		else{
			consumerName = request.getParameter("consumerName");
			consumerPassword = request.getParameter("consumerPassword");
			provinceName = request.getParameter("provinceName");
			addressLine1 = request.getParameter("addressLine1");
			addressLine2 = request.getParameter("addressLine2");
			
			consumer.setConsumerName(consumerName);
			session.setAttribute("consumerName", consumerName);
			consumer.setConsumerPassword(consumerPassword);
			consumerClientManager.update(consumer);
			
			address = addressClientManager.findByConsumer(consumerId);
			if(address != null){
				address.setProvinceName(provinceName);
				address.setAddressLine1(addressLine1);
				address.setAddressLine2(addressLine2);
				addressClientManager.update(address);
			}
			else{
				address = new Address(0,provinceName,addressLine1,addressLine2);
				address.setConsumer(consumer);
				addressClientManager.insert(address);
			}
		}		
	}
	
	
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Hesabım</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	
   	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
		
		<form action="" accept-charset="utf-8">
			<!--  <input type="hidden" name="command" value="Ekle"/>-->
			<table>
				<tbody>
					<tr>
						<td><label>Kullanıcı Adı:</label></td>
						<td><input type="text" name="consumerName" value="<%=consumerName%>"/></td>
					</tr>
					<tr>
						<td><label>Şifre:</label></td>
						<td><input type="text" name="consumerPassword" value="<%=consumerPassword%>" /></td>
					</tr>
					
					<tr>
						<td><label>İl:</label></td>
						<td><input type="text" name="provinceName" value="<%=provinceName%>" /></td>
					</tr>
					
					<tr>
						<td><label>Adres Satırı 1:</label></td>
						<td><input type="text" name="addressLine1" value="<%=addressLine1%>" /></td>
					</tr>
					
					<tr>
						<td><label>Adres Satırı 2:</label></td>
						<td><input type="text" name="addressLine2" value="<%=addressLine2%>" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><%=message %></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Kaydet" name="save" class="button" /></td>
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