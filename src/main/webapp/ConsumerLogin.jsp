<%@page import="com.godoro.database.entity.Cart"%>
<%@page import="com.godoro.web.client.CartClientManager"%>
<%@page import="com.godoro.web.client.ConsumerClientManager"%>
<%@page import="com.godoro.database.entity.Consumer"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String message="";
	String consumerName="";
	if(request.getParameter("login") != null){
		consumerName = request.getParameter("consumerName");
		ConsumerClientManager consumerClientManager = new ConsumerClientManager();
		Consumer consumer = consumerClientManager.find(consumerName);

		if(consumer != null){
			String consumerPassword = request.getParameter("consumerPassword");
			
			if(consumer.getConsumerPassword().equals(consumerPassword)){
				long consumerId = consumer.getConsumerId();
				CartClientManager cartClientManager = new CartClientManager();
				Cart cart = cartClientManager.findByConsumer(consumerId);
				
				session.setAttribute("consumerId", consumerId);
				session.setAttribute("consumerName", consumerName);
				session.setAttribute("cartId", cart.getCartId());
				
				if(session.getAttribute("returnProductView") != null){
					long categoryId = (long)session.getAttribute("categoryId");
					long productId = (long)session.getAttribute("productId");
					session.removeAttribute("returnProductView");
					session.removeAttribute("categoryId");
					session.removeAttribute("productId");
					response.sendRedirect("http://localhost:8080/Website/ProductView.jsp?productId=" + productId +"&categoryId=" + categoryId);
					
				}
				else{
					response.sendRedirect("MainPage.jsp");
				}
				
			}
			else{
				message = "Şifre Yanlış!";
			}
		}
		else{
			message="Kullanıcı Adı Yanlış!";
		}
		
	}
	
	
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Giriş Yap</title>
	<style><%@ include file="css/style.css"%></style>
	<style><%@ include file="css/formStyle.css"%></style>
</head>
<body>
	
   <div class="wrapper">
		<header>
			<jsp:include page="Header.jsp"></jsp:include>
		</header>

		<main>
   	<h2>Giriş Yap</h2>	
		<form action="" accept-charset="utf-8">
			
			<table>
				<tbody>
					<tr>
						<td><label>Kullanıcı Adı:</label></td>
						<td><input type="text" name="consumerName" value="<%=consumerName%>"/></td>
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
						<td><input type="submit" value="Giriş Yap" name="login" class="button" /></td>
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