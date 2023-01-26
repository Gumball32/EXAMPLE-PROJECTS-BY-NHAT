<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of all donated post</title>

<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/bootstrap-icons.css" rel="stylesheet">

<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<!-- <form method="GET" action="DonationController">
		<div class="input-group rounded row">
		  <input name="username" value="None" type="text" class="form-control rounded col-lg-4" placeholder="Input Username" aria-label="Search" aria-describedby="search-addon" />
		  <input name="IDCard" value="0" type="text" class="form-control rounded col-lg-4" placeholder="Input ID Card" aria-label="Search" aria-describedby="search-addon" />
		  <button type="submit" class="btn btn-primary col-lg-2">Search</button>
		</div>
		<input class="hidden" name="action" value="searchUser" style="visibility:hidden">	
	</form> -->
	<div class="text-center mb-1 mt-1">
		<h2>Total Amount Donated: ${totalAmount}</h2>
	</div>
	<form action="DonationController" method="GET">
	<c:forEach var="donate" items="${list}">
		<div class="card container mt-2">
		  <div class="card-header">
		    Post ID: ${donate.getPostID()}
		  </div>
		  <div class="card-body">
		    <%-- <h5 class="card-title">Donate ID: ${user.firstName}</h5> --%>
		    <p class="card-text">Donated Date: ${donate.getCreatedDate()}</p>
		    <p class="card-text">Description: ${donate.getDescription()}</p>
		    <a href="delete_confirmation.jsp?url=DonationController&action=delete&userid=${donate.getUserID()}&postid=${donate.getPostID()}" class="btn btn-danger">Delete</a>
		  </div>
		     <div class="form-check">
			  <input class="form-check-input" type=checkbox name="check" value="${donate.getUserID()}&${donate.getPostID()}" id="checkDelete">
			  <label class="form-check-label" for="checkDelete">
			    Check to delete
			  </label>
			</div>
		</div>
	</c:forEach>
      <div class="row justify-content-center mb-5">
	 		<div class="col-3 col-lg-2 col-xl-1">
	 			
		 			<c:forEach var="i" begin="1" end="${noOfPage}">
		 				<c:choose>
		 					<c:when test="${currentPage eq i}">
		 						<button class="btn btn-outline-info" disabled>${i}</button>
		 					</c:when>
		 					<c:otherwise>
		 						<a class="btn btn-outline-info" href="DonationController?page=${i}&action=all&userid=${user.getID()}">${i}</a>
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
	   		<input class="hidden" name="deleteAllType" value="DonationController" style="visibility:hidden">
 		</form>
	<%@ include file="footer.jsp" %>
</body>
</html>