<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.godoro.database.entity.Category"%>
<%
	long categoryId = Long.parseLong(request.getParameter("categoryId"));
	CategoryClientManager categoryClientManager = new CategoryClientManager();
	categoryClientManager.delete(categoryId);
	response.sendRedirect("CategorySummary.jsp");
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