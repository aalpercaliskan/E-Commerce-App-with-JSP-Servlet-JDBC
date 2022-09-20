<%@page import="java.util.List"%>
<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@page import = "com.godoro.database.entity.Category"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	CategoryClientManager categoryClientManager = new CategoryClientManager();
	List<Category> categoryList = categoryClientManager.list();
	boolean isActive = false;
	if(session.getAttribute("consumerName") != null){
		isActive = true;
	}
	pageContext.setAttribute("categoryList", categoryList);	
	pageContext.setAttribute("isActive", isActive);
	
	
	
%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Alışveriş Sitesi</title>
	<style><%@ include file="css/menu.css"%></style>
	

	
</head>
<body>
 
	<ul>
		<li><a class="dark" href="MainPage.jsp">Anasayfa</a></li>
	
		<c:forEach var="tempCategory" items="${categoryList}">
			<c:url var="categoryLink" value="ProductList.jsp">
				<c:param name="categoryId" value="${tempCategory.categoryId}"></c:param>
			</c:url>
		  	<li><a href="${categoryLink}">${tempCategory.categoryName}</a></li>
		</c:forEach>
		
		<li><a class="dark" href="CartView.jsp">Sepetim</a></li>
		<c:if test="${!isActive}">
		
			<li><a class="dark" href="ConsumerLogin.jsp">Giriş Yap</a></li>
			<li><a class="dark" href="ConsumerRegister.jsp">Üye Ol</a></li>
		</c:if>
		
		<c:if test="${isActive}">
			<li><a class="dark" href="Account.jsp">Hesabım</a></li>
			<li><a class="dark" href="ConsumerLogout.jsp">Çıkış Yap</a></li>
		</c:if>
		
	</ul>
	

  

</body>
</html>