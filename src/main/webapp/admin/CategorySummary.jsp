<%@page import="com.godoro.web.client.CategoryClientManager"%>
<%@page import = "java.util.List,com.godoro.database.entity.Category"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	if(session.getAttribute("isAdmin") == null){
		response.sendRedirect("../AdminLogin.jsp");
	}
	CategoryClientManager categoryClientManager = new CategoryClientManager();
	List<Category> categoryList = categoryClientManager.list();
	pageContext.setAttribute("categoryList", categoryList);	

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Kategoriler</title>
	<style><%@ include file="css/style.css"%></style>
</head>
<body>
	 <jsp:include page="Menu.jsp"></jsp:include>
	<h2>Kategoriler</h2>
	
	<div id="container">
		<div id="content">
			
			
			<input type="button" value="Kategori Ekle" 
				   onclick="window.location.href='AddCategory.jsp'; return false;"
				   class="add-button"
			/>
			<table>
				<tr>
					<th>Category İsmi</th>
					<th>Aksiyon</th>
				</tr>
				
				<c:forEach var="tempCategory" items="${categoryList}">
				
					<c:url var="tempLink" value="UpdateCategory.jsp">
						<c:param name="categoryId" value="${tempCategory.categoryId}"></c:param>
					</c:url>
					
					<c:url var="deleteLink" value="DeleteCategory.jsp">
						<c:param name="categoryId" value="${tempCategory.categoryId}"></c:param>
					</c:url>
					
					<c:url var="productSummaryLink" value="ProductSummary.jsp">
						<c:param name="categoryId" value="${tempCategory.categoryId}"></c:param>
					</c:url>
					
					
					
					<tr>
						<td>${tempCategory.categoryName}</td>
						<td>
							<a href="${tempLink}">Güncelle</a>
							 |
							<a href="${deleteLink}" 
							   onclick="if(!(confirm('Silmek istediğine emin misin?'))) return false">Sil</a>
							 | 	
							 <a href="${productSummaryLink}">Ürünler</a>	
							
						</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>
</body>
</html>