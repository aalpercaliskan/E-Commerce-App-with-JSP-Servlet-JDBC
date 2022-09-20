<%@page import="com.godoro.database.entity.Consumer"%>
<%@page import="com.godoro.web.client.ConsumerClientManager"%>
<%@page import = "java.util.List"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	ConsumerClientManager consumerClientManager = new ConsumerClientManager();
	List<Consumer> consumerList = consumerClientManager.list();
	pageContext.setAttribute("consumerList", consumerList);	

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hesaplar</title>
	<style><%@ include file="css/style.css"%></style>
</head>
<body>
	 <jsp:include page="Menu.jsp"></jsp:include>
	<h2>Hesaplar</h2>
	
	<div id="container">
		<div id="content">
			
			<table>
				<tr>
					<th>Kullanıcı Adı</th>
					<th>Aksiyon</th>
				</tr>
				
				<c:forEach var="consumer" items="${consumerList}">
		
					<c:url var="consumerDetail" value="ConsumerDetail.jsp">
						<c:param name="consumerId" value="${consumer.consumerId}"></c:param>
					</c:url>
					
					<tr>
						<td>${consumer.consumerName}</td>
						<td>
							<a href="${consumerDetail}">Görüntüle</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>
</body>
</html>