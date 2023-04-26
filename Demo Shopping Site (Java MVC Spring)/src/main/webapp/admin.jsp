<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="header.jsp" %>
<%-- <% 
if (request.getAttribute("orderList") == null) 
	{
		response.sendRedirect("ProductController");
		return;
	}
%> --%>
<% 
if (user1 == null) 
	{
			response.sendRedirect("ProductController");
			return;

	}
else
	{
		if (user1.getId() != 2) {
			response.sendRedirect("ProductController");
			return;
		}
	}
%>
 <div class="container-fluid bg-oldlace">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                    <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                        <a href="#" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <span class="fs-5 d-none d-sm-inline">Admin</span>
                        </a>
                        <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                            <li class="nav-item">
                                <a href="admin.php?view=home" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                                </a>
                            </li>
                            <li>
                                <a href="admin.php?view=Upload_product" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-upload"></i> <span class="ms-1 d-none d-sm-inline">Upload Product</span></a>
                            </li>
                            <li>
                                <a href="admin.php?view=order" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Orders</span></a>
                            </li>
                        </ul>
                        <hr>

                    </div>
                </div>
                <div class="col py-3 overflow-auto">
                            <div class="list overflow-auto" style="max-height: 88vh;">
                                <h1 class="text-center pb-4">Order List</h1>
                                <table class="table table-striped fs-4 admin-order-table">
                                    <thead>
                                        <tr class="fs-6">
                                            <th scope="col">Order ID</th>
                                            <th scope="col">Customer ID</th>
                                            <th scope="col">Total</th>
                                            <th scope="col">Time</th>
                                            <th scope="col">Status</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                    	<c:forEach var="order" items="${orderList}">
                                        <tr class="fs-6">
                                            <td scope="col">${order.getId()}</td>
                                            <td scope="col">${order.userId}</td>
                                            <td scope="col">${order.totalCost}</td>
                                            <td scope="col">${order.orderDate}</td>
                                            <td scope="col" class="btn p-2 mt-1 verify-admin-button" style="background-color: #52D452;">
                                            <c:if test="${order.status == 2}">
                                            	 Pending
                                            </c:if>
                                            <c:if test="${order.status == 1}">
                                            	 Approved
                                            </c:if>
                                            <a class="btn btn-primary" href="OrderController?action=detail&id=${order.getId()}">Detail</a>
                                            </td>
                                            
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                    
                                </table>
                            </div>
                            
                        	<div class="row justify-content-center mb-5">
						 		<div class="col-3 col-lg-2 col-xl-1">
						 			<c:forEach var="i" begin="1" end="${noOfPage}">
						 				<c:choose>
						 					<c:when test="${currentPage eq i}">
						 						<button class="btn btn-outline-info" disabled>${i}</button>
						 					</c:when>
						 					<c:otherwise>
						 						<a class="btn btn-outline-info" href="OrderController?page=${i}&action=allOrders">${i}</a>
						 					</c:otherwise>
						 				</c:choose>
						 			</c:forEach>
						 		</div>
 							</div>
                </div>
            </div>
        </div>
<%@ include file="footer.jsp" %>
