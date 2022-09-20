<%@page import="com.godoro.web.client.ProductClientManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.godoro.database.entity.Category"%>

<%
	long productId = Long.parseLong(request.getParameter("productId"));
	long categoryId = Long.parseLong(request.getParameter("categoryId"));
	
	ProductClientManager productClientManager = new ProductClientManager();
	String result = productClientManager.delete(productId);
	response.sendRedirect("http://localhost:8080/Website/admin/ProductSummary.jsp?categoryId="+categoryId);
	
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