<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="header.jsp" %>
 <head>
  <link rel="stylesheet" type="text/css" href="css/orderDetail.css">
</head>
<%
	String method = (String) session.getAttribute("method");
	Order order = (Order) session.getAttribute("order");
	System.out.println(order);
	if (method.equals("bank")) {
		BankPayment payment = (BankPayment) session.getAttribute("payment");
		System.out.println(payment);
	}
	if (method.equals("cash")) {
		CODPayment payment = (CODPayment) session.getAttribute("payment");
		System.out.println(payment);
	}
	
%>
<main>
	<c:if test="${method.equals('bank')}">
	  <div class="product-image container">
	    <img src="${payment.image}" alt="Product Image">
	  </div>
	</c:if>
  <div class="product-info text-center">
    <h2>OrderId: ${order.id}</h2>
    <p>Total Fee: ${order.totalCost}</p>
    <p>Method: ${method}</p>
    <p>Address: ${payment.address}</p>
    <p>Phone Number: ${payment.phoneNumber}</p>
    <a class="btn btn-success" href="OrderController?action=update&id=${order.id}&status=1">Confirm</a>
  </div>
  
</main>
<%@ include file="footer.jsp" %>