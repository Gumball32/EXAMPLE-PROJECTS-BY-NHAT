<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Users</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/bootstrap-icons.css" rel="stylesheet">

<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<%@ page import="beans.User" %>
	<%@ page import="javax.servlet.http.HttpServletRequest" %>
	<%@ page import="javax.servlet.http.HttpServletResponse" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
	<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else if (user.getRole() != 1) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	%>
	<form method="GET" action="UserController">
		<div class="input-group rounded row">
		  <input name="username" value="None" type="text" class="form-control rounded col-lg-4" placeholder="Input Username" aria-label="Search" aria-describedby="search-addon" />
		  <input name="IDCard" value="0" type="text" class="form-control rounded col-lg-4" placeholder="Input ID Card" aria-label="Search" aria-describedby="search-addon" />
		  <button type="submit" class="btn btn-primary col-lg-2">Search</button>
		</div>
		<input class="hidden" name="action" value="searchUser" style="visibility:hidden">	
	</form>
	<form action="UserController" method="GET">
	<c:forEach var="user" items="${list}">
		<div class="card container mt-2">
		  <div class="card-header">
		    User ID: ${user.getID()}
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">${user.firstName} ${user.lastName}</h5>
		    <p class="card-text">${user.username}</p>
		    <p class="card-text">${user.DOB}</p>
		    <p class="card-text">${user.getIDCard()}</p>
		    <a href="delete_confirmation.jsp?url=UserController&action=delete&id=${user.getID()}" class="btn btn-danger">Delete</a>
		    <a href="UserController?action=forgetPassword&username=${user.username}" class="btn btn-warning">Reset Password</a>
		    <a class="btn btn-primary" id="btn btn-edit" href="UserController?action=editAsAdmin&ID=${user.getID()}">Edit</a>
		  </div>
		     <div class="form-check">
			  <input class="form-check-input" type=checkbox name="check" value="${user.getID()}" id="checkDelete">
			  <label class="form-check-label" for="checkDelete">
			    Check to delete
			  </label>
			</div>
		</div>
	</c:forEach>
      <div class="row justify-content-center mb-2 mt-2">
		<div class="col-3 col-lg-2 col-xl-1">
 			<c:forEach var="i" begin="1" end="${noOfPage}">
 				<c:choose>
 					<c:when test="${currentPage eq i}">
 						<button class="btn btn-outline-info" disabled>${i}</button>
 					</c:when>
 					<c:otherwise>
 						<a class="btn btn-outline-info" href="UserController?page=${i}&action=users">${i}</a>
 					</c:otherwise>
 				</c:choose>
 			</c:forEach>
		</div>
		</div>
			<div class="container my-5">
	            <div class="row" style="justify-content: center;">
	   			<button class="btn btn-info" type="submit">Delete All</button>
	   			</div>
	   		</div>
	   		<input class="hidden" name="action" value="showDeleteAll" style="visibility:hidden">
	   		<input class="hidden" name="deleteAllType" value="UserController" style="visibility:hidden">
 		</form>
	<%@ include file="footer.jsp" %>
</body>
</html>