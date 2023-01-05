<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<title>Delete All Confirmation</title>
</head>
<body>
	<div class="text-center">
	  <h1>List</h1>
	</div>
	<form action="${type}" method="GET">
      	<c:forEach var="id" items="${ids}">
      		<div class="card">
			  <h5 class="card-header">ID: ${id}</h5>
			  <input class="hidden" id="checkDelete" type=checkbox name="check" value="${id}" style="visibility:hidden" checked>
			</div>
      	</c:forEach>
     	<input class="hidden" name="action" value="deleteAll" style="visibility:hidden">     		
   		<div class="d-flex justify-content-around">
   			<button class="btn btn-info" type="submit">Delete All</button>
   			<a class="btn btn-primary" href="index.jsp">Home</a>
   		</div>
	</form>
</body>
</html>