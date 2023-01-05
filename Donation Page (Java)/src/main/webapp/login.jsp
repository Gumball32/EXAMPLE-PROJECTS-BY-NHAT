<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/login/style.css">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-12 col-lg-10">
					<div class="wrap d-md-flex">
						<div class="text-wrap p-4 p-lg-5 text-center d-flex align-items-center order-md-last">
							<div class="text w-100">
								<h2>Welcome to login</h2>
								<p>Don't have an account?</p>
								<a href="register.jsp" class="btn btn-white btn-outline-white">Sign Up</a>
							</div>
			      </div>
						<div class="login-wrap p-4 p-lg-5">
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">Sign In</h3>
			      		</div>
								<div class="w-100">
									<p class="social-media d-flex justify-content-end">
										<a href="#" class="social-icon d-flex align-items-center justify-content-center"><span class="fa fa-facebook"></span></a>
										<a href="#" class="social-icon d-flex align-items-center justify-content-center"><span class="fa fa-twitter"></span></a>
									</p>
								</div>
			      	</div>
						<form action="UserController" class="signin-form" method="GET">
				      		<div class="form-group mb-3">
				      			<label class="label" for="name">Username</label>
				      			<input type="text" class="form-control" placeholder="Username" name="username" required>
				      		</div>
				            <div class="form-group mb-3">
				            	<label class="label" for="password">Password</label>
				              <input type="password" class="form-control" placeholder="Password" name="password" required>
				            </div>
				            <div class="form-group">
				            	<button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
				            	<input class="hidden" name="action" value="login" style="visibility:hidden">
				            </div>
				            <div class="form-group d-md-flex">
				            	<div class="w-50 text-left">
									<label class="checkbox-wrap checkbox-primary mb-0">Remember Me
									<input type="checkbox" name="check" checked>
									<span class="checkmark"></span>
									</label>
								</div>
								<div class="w-50 text-md-right">
									<a id="forget-btn" href="forgetPassword.jsp">Forgot Password</a>
								</div>
				            </div>
							<div>
								<a href="index.html" class="btn btn-primary">Home</a>
							</div>
			          </form>
		        </div>
		      </div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/jquery.min.js"></script>
	<script src="js/popper.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
	
	<%@ include file="footer.jsp" %>
</body>
</html>