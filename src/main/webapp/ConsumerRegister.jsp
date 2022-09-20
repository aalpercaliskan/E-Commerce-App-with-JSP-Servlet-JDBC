<%@page import="com.godoro.web.client.CartClientManager"%>
<%@page import="com.godoro.web.client.ConsumerClientManager"%>
<%@page import="com.godoro.database.entity.Cart"%>
<%@page import="com.godoro.database.entity.Consumer"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String message="";
	
	if(request.getParameter("register") != null){
		ConsumerClientManager consumerClientManager = new ConsumerClientManager();
		String consumerName = request.getParameter("consumerName");
		Consumer consumer = consumerClientManager.find(consumerName);
		if(consumer != null){
			message = "Kullanıcı Adı Kullanılmakta!";
		}
		else{
			String consumerPassword = request.getParameter("consumerPassword");
			if(consumerName.length()>0 && consumerPassword.length()>0){
				consumer = new Consumer(0,consumerName,consumerPassword);
				
				consumerClientManager.insert(consumer);
				
				consumer = consumerClientManager.find(consumerName);
				Cart cart = new Cart(0,0);
				cart.setConsumer(consumer);
				CartClientManager cartClientManager = new CartClientManager();
				cartClientManager.insert(cart);
				
				message = "Üye Olundu";
			}
			else{
				message = "Geçerli Değerler Giriniz!";
			}
		}
		
	}

	
%>


<html>
<head>
	<meta charset="UTF-8">
	<title>Kayıt Ol</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
	
</head>
<body>

	<div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
	<h2>Üye Ol</h2>
	
		<form action="" accept-charset="utf-8">
			<table>
				<tbody>
					<tr>
						<td><label>Kullanıcı Adı:</label></td>
						<td><input type="text" name="consumerName" /></td>
					</tr>
					<tr>
						<td><label>Şifre:</label></td>
						<td><input type="password" name="consumerPassword" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><%=message %></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Üye Ol" name="register" class="button"/></td>
						
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