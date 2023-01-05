<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page import="beans.User" %>
	<% 
	User user1 = (User) session.getAttribute("user");
	%>

<header class="site-header">
        <div class="container">
            <div class="row">

                <div class="col-lg-8 col-12 d-flex flex-wrap">
                    <p class="d-flex me-4 mb-0">
                        <i class="bi-geo-alt me-2"></i>
                        Akershusstranda 20, 0150 Oslo, Norway
                    </p>

                    <p class="d-flex mb-0">
                        <i class="bi-envelope me-2"></i>

                        <a href="mailto:info@company.com">
                            info@company.com
                        </a>
                    </p>
                </div>

                <div class="col-lg-3 col-12 ms-auto d-lg-block d-none">
                    <ul class="social-icon">
                        <li class="social-icon-item">
                            <a href="#" class="social-icon-link bi-twitter"></a>
                        </li>

                        <li class="social-icon-item">
                            <a href="#" class="social-icon-link bi-facebook"></a>
                        </li>

                        <li class="social-icon-item">
                            <a href="#" class="social-icon-link bi-instagram"></a>
                        </li>

                        <li class="social-icon-item">
                            <a href="#" class="social-icon-link bi-youtube"></a>
                        </li>

                        <li class="social-icon-item">
                            <a href="#" class="social-icon-link bi-whatsapp"></a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </header>

    <nav class="navbar navbar-expand-lg bg-light shadow-lg">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/logo.png" class="logo img-fluid" alt="Forever Love Charity">
                <span>
                    Forever Love Charity
                    <small>Non-profit Organization</small>
                </span>
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link click-scroll" href="index.jsp">Home</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link click-scroll" href="#section_2">About</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link click-scroll" aria-current="page" href="CharityPostController?action=all">Posts</a>
                    </li>
                    <% 					
					if (user1 != null && user1.getRole() == 1) { %>
                    <li class="nav-item">
                        <a class="nav-link click-scroll" aria-current="page" href="UserController?action=users">Users</a>
                    </li>
					
                    <li class="nav-item">
                        <a class="nav-link click-scroll" href="create_donate.jsp">Create</a>
                    </li>
					<% } %>
                    <!-- <li class="nav-item dropdown">
                        <a class="nav-link click-scroll dropdown-toggle" href="#section_5"
                            id="navbarLightDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">Charities</a>

                        <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="navbarLightDropdownMenuLink">
                            <li><a class="dropdown-item" href="donations.jsp">News Listing</a></li>

                            <li><a class="dropdown-item" href="donation-detail.jsp">News Detail</a></li>
                        </ul>
                    </li> -->

                    <li class="nav-item">
                        <a class="nav-link click-scroll" href="#section_6">Contact</a>
                    </li>
					<% 
					if (user1 != null) { %>
	                    <li class="nav-item ms-3">
	                        <a class="nav-link custom-btn custom-border-btn btn" href="profile.jsp">Profile</a>
	                    </li>
                    <% } else { %>
                     	<li class="nav-item ms-3">
	                        <a class="nav-link custom-btn custom-border-btn btn" href="login.jsp">Login</a>
	                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>
    
     <%
      	String msg = (String) session.getAttribute("msg");
     	
     	if (msg != null) {
     
      	if (msg.equals("Successfully Updated") || msg.equals("Logged In") || msg.equals("Successfully Created") || msg.equals("Successfully Donated")) { %>
      		<div class="alert alert-success">
			  <%= msg %>
			</div>	      	
      <% } else if (msg.equals("Failed") || msg.equals("Logged Out")) { %>
      		<div class="alert alert-danger">
			  <%= msg %>
			</div>
      <% } 
      else { %>
			<div class="alert alert-warning">
			  <%= msg %>
			</div>
	<% }
     	} else { %>
      		<div></div>
      <% } %>
      <% session.setAttribute("msg", null); %>
      