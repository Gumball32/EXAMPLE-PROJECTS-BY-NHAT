<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Title Page-->
<title>Register</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">

<!-- Icons font CSS-->
<link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
<!-- Font special for pages-->
<link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Vendor CSS-->
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="css/register/main.css" rel="stylesheet" media="all">

<style>
	.navbar-expand-lg .navbar-nav .nav-link.custom-btn {
    color: var(--custom-btn-bg-color);
    margin-top: 8px;
    padding: 1px 26px;
}
	
</style>

</head>
<body>
	<%@ include file="header.jsp" %>
	<%@ page import="java.text.SimpleDateFormat" %>
	<%@ page import="java.util.Date" %>
	<% 
	try {
		User user = null;
		if (session.getAttribute("userByAdmin") != null) {
			user = (User) session.getAttribute("userByAdmin");
		} else {
			user = (User) session.getAttribute("user");	
		}		
		pageContext.setAttribute("user", user);
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(user.getDOB());
		pageContext.setAttribute("date", date);
	} catch (Exception e) {
		request.setAttribute("msg", "Failed");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	%>	
	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Edit Form</h2>
                    <form method="POST" action="UserController?action=edit_profile" enctype='multipart/form-data'>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Username</label>
                                    <input value="${user.getUsername()}" class="input--style-4" type="text" name="username" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Old Password</label>
                                    <input class="input--style-4" type="password" name="oldpassword" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">New Password</label>
                                    <input class="input--style-4" type="password" name="newpassword" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Re-enter New Password</label>
                                    <input class="input--style-4" type="password" name="re-newpassword" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">first name</label>
                                    <input value="${user.getFirstName()}" class="input--style-4" type="text" name="first-name" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">last name</label>
                                    <input value="${user.getLastName()}" class="input--style-4" type="text" name="last-name" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-12" style="width: 100%;">
                                <div class="input-group">
                                    <label class="label">ID card number</label>
                                    <input value="${user.getIDCard()}" class="input--style-4" type="text" name="id-card" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Birthday</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="date" name="birthday" value="${user.getDOB()}" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Gender</label>
                                    <div class="p-t-10">
                                        <label class="radio-container m-r-45">Male
                                            <input type="radio" name="gender" value="male" required>
                                            <span class="checkmark"></span>
                                        </label>
                                        <label class="radio-container">Female
                                            <input type="radio" name="gender" value="female" required>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Email</label>
                                    <input value="${user.getEmail()}" class="input--style-4" type="email" name="email" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Phone Number</label>
                                    <input value="${user.getPhoneNumber()}" class="input--style-4" type="text" name="phone" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                        	<img alt="" src="${user.getMainImage()}">
                        	<input class="hidden" name="currentImage" value="${user.getMainImage()}" style="visibility:hidden">
                        </div>
                        <div class="row row-space">
                        	<div class="form-row">
			                         <div class="name">Upload Image</div>
			                         <div class="value">
		                             <div class="input-group">
		                                 <input class="input--style-5" type="file" name="image">
		                             </div>
		                         </div>
	                        </div>
                        </div>
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--green" type="submit">Submit</button>
                            <a class="btn btn--radius-2 btn--blue" href="index.jsp">Home</a>
                        </div>
                        <input class="hidden" name="action" value="edit_profile" style="visibility:hidden">
                        <input class="hidden" name="id" value="${user.getID()}" style="visibility:hidden">
                    </form>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="footer.jsp" %>
	 <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="js/global.js"></script>
</body>
</html>