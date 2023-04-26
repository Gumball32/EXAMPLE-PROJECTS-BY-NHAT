<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Shopeeo</title>

  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" rel="stylesheet" />
  <!-- BootStrap 5 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

  <!-- CSS Files -->
  <link href="css/style.css" rel="stylesheet">

  <!-- Font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

  <link href="http://fonts.cdnfonts.com/css/telegrafico" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Thasadith&display=swap" rel="stylesheet">

  <!-- jquery script -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="script/import plugin/jquery.twbsPagination.js"></script>
  <script type="text/javascript" src="script/import plugin/jquery.twbsPagination.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-toaster@4.0.1/css/bootstrap-toaster.css"/>
  <script src="script/jqueryPassiveaAddEventListenner.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js" type="text/javascript"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
</head>

<body>
     <%
      	String msg = (String) session.getAttribute("msg");
     	
     	if (msg != null) {
     
      	if (msg.equals("Successfully Updated") || msg.equals("Logged In") || msg.equals("Successfully Created") || msg.equals("Successfully Donated")) { %>
      		<div class="alert alert-success">
			  <%= msg %>
			</div>	      	
      <% } else if (msg.equals("Failed") || msg.equals("Logged Out") 
    		  || msg.equals("This account is not created") || msg.equals("Wrong Password")
    		  || msg.equals("The account is deleted")) { %>
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
      <% session.setAttribute("msg", null);
      User user1 = (User) session.getAttribute("user");
	%>
  
  
  <!-- Header,NavBar -->
  <nav class="navbar navbar-expand-xl">
    <div class="container">
      <!-- hamburguer button -->
      <button class="navbar-toggler navbar-light" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggler" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <a class="nav-item navbar-brand nav-link px-2 title" href="ProductController">Shoppeo</a>

      <div class="collapse navbar-collapse justify-content-end" id="navbarToggler">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active pe-3 home" aria-current="page" href="ProductController">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link home pe-3" aria-current="page" href="CartController?action=showItems">GO BUY!</a>
          </li>
          <% if (user1 != null) { %>
	          <% if (user1.getId() == 2) { %>
	           		<li class="nav-item">
		             <a class="nav-link home Login-BT" aria-current="page" href="OrderController?action=allOrders">Admin</a>
		           </li>
	          <% } %>
          <% } %>
          <li class="nav-item">
          <% if (user1 != null) { %>
          	<a class="nav-link home Login-BT mx-3" href="UserController?action=logout">Logout<span class="sr-only">(current)</span></a>
          	<a class="nav-link home Login-BT mx-3" href="profile.jsp">Profile</a>
          <% } %>
           </li>
           <% if (user1 == null) { %>
	           <li class="nav-item">
	             <a class="nav-link home me-3 SignUp-BT mb-3 mb-xl-0" aria-current="page" href="signup.jsp">Sign-up</a>
	           </li>
	           <li class="nav-item">
	             <a class="nav-link home Login-BT" aria-current="page" href="login.jsp">Login</a>
	           </li>
           <% } %>
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Header -->
  