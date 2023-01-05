<%@page import="beans.PostImage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<title>Kind Heart Charity - News Listing</title>

   <!-- CSS FILES -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/bootstrap-icons.css" rel="stylesheet">

<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<%@ page import="beans.PostImage" %>
	<%@ page import="DAO.CharityPostDAO" %>
	<% 
	User user = (User) session.getAttribute("user");
	if (user != null) {
		pageContext.setAttribute("user", user);
	}
	%>
	<main>
		        <section class="news-detail-header-section text-center">
            <div class="section-overlay"></div>

            <div class="container">
                <div class="row">

                    <div class="col-lg-12 col-12">
                        <h1 class="text-white">Charity Listing</h1>
                    </div>

                </div>
            </div>
        </section>
        
        <form action="CharityPostController" method="GET">
			<div class="input-group mt-2">
			  <input value="Enter name to search" type="text" name="name" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
			  <button type="submit" class="btn btn-outline-primary">Search</button>
			</div>
			<input class="hidden" name="action" value="searchPost" style="visibility:hidden">
		</form>

        <section class="news-section section-padding">
        	<form action="CharityPostController" method="GET">
	        	<c:forEach var="post" items="${postList}">
		            <div class="container my-5">
		                <div class="row" style="justify-content: center;">
		                    <div class="col-lg-7 col-12">
		                        <div class="news-block">
		                            <div class="news-block-top">
		                                <a href="donation-detail.html">
		                                    <img src="${post.mainImage}"
		                                        class="rounded mx-auto d-block" alt="" style="
		                                        width: 100%;
									height: 90vh;
									object-fit: cover;
									object-position: 0 0;
		                                        ">
		                                </a>
		
		                                <div class="news-category-block">
		                                    <a href="#" class="category-block-link">
		                                        Lifestyle,
		                                    </a>
		
		                                    <a href="#" class="category-block-link">
		                                        Clothing Donation
		                                    </a>
		                                </div>
		                            </div>
		
		                            <div class="news-block-info">
		                                <div class="d-flex mt-2">
		                                    <div class="news-block-date">
		                                        <p>
		                                            <i class="bi-calendar4 custom-icon me-1"></i>
		                                            Start Date: ${post.startDate}
		                                        </p>
		                                    </div>
		
		                                    <div class="news-block-author mx-5">
		                                        <p>
		                                            <i class="bi-person custom-icon me-1"></i>
		                                            By Admin
		                                        </p>
		                                    </div>
		
		                                    <div class="news-block-comment">
		                                        <p>
		                                            <i class="bi-chat-left custom-icon me-1"></i>
		                                            End Date: ${post.endDate}
		                                        </p>
		                                    </div>
		                                </div>
		
		                                <div class="news-block-title mb-2">
		                                    <h4><a href="news-detail.html" class="news-block-title-link">
		                                    	${post.name}
		                                    </a></h4>
		                                </div>
		
		                                <div class="news-block-body">		                                    
		                                    <%-- <a href="DonationController?action=showDonate&id=${post.ID}" class="btn btn-success">Donate now</a> --%>
		                                    <a href="CharityPostController?action=detailDonate&id=${post.ID}" class="btn btn-success">Detail</a>
		                                	<%-- <a href="CharityPostController?action=edit&id=${post.ID}" class="btn btn-primary">Edit</a>
		                                	<a href="CharityPostController?action=delete&id=${post.ID}" class="btn btn-danger">Delete</a> --%>
		                                </div>
		                                <% if (user != null && user.getID() == 1) { %>
		                                <div class="form-check">
										  <input class="form-check-input" type=checkbox name="check" value="${post.ID}" id="checkDelete">
										  <label class="form-check-label" for="checkDelete">
										    Check to delete
										  </label>
										</div>
										<% } %>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
	            </c:forEach>
        		<input class="hidden" name="action" value="showDeleteAll" style="visibility:hidden">
        		<input class="hidden" name="deleteAllType" value="CharityPostController" style="visibility:hidden">
        		<% if (user != null && user.getID() == 1) { %>
        		<div class="container my-5">
		                <div class="row" style="justify-content: center;">
        		<button class="btn btn-info" type="submit">Delete All</button>
        			</div>
        		</div>
        		<% } %>
	 		</form>
        </section>
        
        <div class="row justify-content-center mb-5">
	 		<div class="col-3 col-lg-2 col-xl-1">
	 			
		 			<c:forEach var="i" begin="1" end="${noOfPage}">
		 				<c:choose>
		 					<c:when test="${currentPage eq i}">
		 						<button class="btn btn-outline-info" disabled>${i}</button>
		 					</c:when>
		 					<c:otherwise>
		 						<a class="btn btn-outline-info" href="CharityPostController?page=${i}&action=all">${i}</a>
		 					</c:otherwise>
		 				</c:choose>
		 			</c:forEach>
		 			
	 		</div>
	 	</div>
    </main>
	<%@ include file="footer.jsp" %>
</body>
</html>