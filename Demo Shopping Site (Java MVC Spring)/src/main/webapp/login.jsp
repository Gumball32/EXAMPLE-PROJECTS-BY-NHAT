<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<div class="container mb-5">
    <div class="row main-banner-homepage">
        <img src="images/shopeeo-logo.png" alt="Shopeeo Logo" class="col-lg-6">
        <div class="col-lg-6 align-self-center mb-lg-0 mb-4 bg-white p-5">
            <h3 class="fw-light">Login to Account</h3>
            <hr>
            <form action="UserController" method="post">
                <div class="form-group">
                    <input type="text" class="phone-number-input form-control my-4" placeholder="Enter username..." name="username" autocomplete="off">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control my-4" placeholder="Enter a password..." name="password">
                </div>
                <button class="btn btn-outline-danger p-2 mt-3 w-100" type="submit" name="login" value="true">Login</button>
                <div class="a-divider a-divider-break mt-3">
                    <hr class="mt-5 mb-0">
                    <h6 class="mt-0 text-center pt-2">Don't have an account?</h6>
                    <a href="signup.jsp" class="btn btn-outline-success mt-2 p-2 w-100" type="submit" name="Sign up" value="true">Sign up</a>
                </div>
                <input class="hidden" name="action" value="login" style="visibility:hidden">
            </form>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>