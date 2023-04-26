<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<div class="container mb-5">
    <div class="row main-banner-signup">
        <img src="images/shopeeo-logo.png" alt="Shopeeo Logo" class="col-lg-6">
        <div class="col-lg-6 align-self-center mb-lg-0 mb-4 bg-white p-4">
            <h3 class="fw-light">Create Account</h3>
            <hr>
            <form action="UserController" method="post"  enctype='multipart/form-data'>
                <div class="form-group">
                    <label for="F_name" style="font-weight:700">Full Name</label>
                    <input type="text" class="form-control my-2" name="fullname_input" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="F_name" style="font-weight:700">Username</label>
                    <input type="text" class="form-control my-2" name="username_input" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="email" style="font-weight:700">Email</label>
                    <input type="text" class="form-control my-2 phone-number-input " name="email_input" onkeydown="return event.keyCode !== 69">
                </div>
                <div class="form-group">
                    <label for="password" style="font-weight:700">Password</label>
                    <input type="password" class="form-control my-2" placeholder="At least 5 characters" name="password_input">
                    <div class="invalid-feedback">
                        Password invalid!
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" style="font-weight:700">Email</label>
                    <input type="file" class="form-control my-2 phone-number-input " name="image_input">
                </div>
                <button class="btn btn-outline-danger p-2 mt-3 w-100" type="submit" name="create" value="true">Continue</button>
                <div class="a-divider a-divider-break mt-3">
                    <hr class="mt-5">
                    <h6 class="mt-3">Already have an account? <a href="login.jsp" class="text-decoration-none ms-1">Login <i class="fa-solid fa-caret-right"></i></a></h6>
                </div>
                <input class="hidden" name="action" value="create" style="visibility:hidden">
            </form>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>