<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="header.jsp" %>
<%@ page import="Model.Cart" %>
<% 
/* 	Cart items = (Cart) session.getAttribute("cart");
	System.out.println(items.getItems());
	System.out.println(items.getItems().get(0)); */
%>

<div class="main-bg-order-product">
    <div class="container text-center text-white">
        <img src="../image/shopeeo-logo.png" alt="">
    </div>
</div>

<div class="selection-categories mt-5">
    <div class="container text-center">
        <h1>Categories</h1>
    </div>
    <div class="container py-2">
    	<c:forEach var="item" items="${cartList}">
	        <div class=" card-box">
	            <div class="card px-2" style="width: 15rem; height: 25rem;">
	                    <img class="card-img-top" src="${item.image}" alt="Card image cap">
	                <div class="card-body">
	                    <p class="card-text text-center">Name: ${item.name}</p>
	                    <p class="card-text text-center ">Price: ${item.price}</p>
	                    <p class="card-text text-center ">Amount: ${item.amount}</p>
	                    <div class="row">
	                    </div>
	                </div>
	            </div>
        	</div>
		</c:forEach>
    </div>
    <a href="payment.jsp" class="btn btn-success p-0 w-100 add-shopping-cart border-radius-25px"><i class="fa-solid fa-plus"></i>Next</a> 
</div>
<%@ include file="footer.jsp" %>