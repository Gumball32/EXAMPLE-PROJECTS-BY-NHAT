<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="header.jsp" %>
<% 
if (request.getAttribute("productList") == null) 
	{
		response.sendRedirect("ProductController");
		return;
	}
%>
<div class="container mb-5">
    <div class="row main-banner-homepage">
        <div class="col-lg-7 align-self-center mb-lg-0 mb-4">
            <p>Welcome To Our <span>Ecommerce</span> <p>Website !</p>
            <div class="row">
                <a href="CartController?action=showItems" class="col-6"><button class="btn btn-outline-warning h-100 w-100">Buy Now</button></a>
            </div>
        </div>
        <img src="images/shopeeo-logo.png" alt="Shopeeo Logo" class="col-lg-5">
    </div>
</div> <!-- End Of Main Banner -->
<!-- Explore Food Area -->
<div class="explore-food mt-5">
    <div class="container">
        <h2 class="text-center">Best Seller this month</h2>
        <div class="d-flex justify-content-center">
            <hr class="w-10">
        </div>
        <form class="form-outline d-flex justify-context-between mb-3" action="ProductController?action=searchName"  method="POST">
		  <input type="search" id="form1" class="form-control" placeholder="Type query" aria-label="Search" name="seacrh" required />
		  <button type="submit" class="btn btn-primary">Search</button>
		</form>
        <div class="signature-food-showing-area">
            <div class="row main-coursed">
            	<c:forEach var="product" items="${productList}">
	                <div class="col-lg-4 col-md-4 col-sm-6 mb-3">
	                    <div class="card">
	                        <img src="${product.image}?timestamp=123456" class="card-img-top" alt="Banh Mi images">
	                        <div class="card-body text-center">
	                            <p class="card-text fw-bold text-orange">${product.price}</p>
	                            <h5 class="card-title">${product.name}</h5>
	                            <h5 class="card-title">${product.amount}</h5>
	                            <a class="btn btn-success" href="ProductController?action=detail-product&id=${product.id}">Detail</a>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
            </div>
        </div>
        <div class="row justify-content-center mb-5">
	 		<div class="col-3 col-lg-2 col-xl-1">
	 			<c:forEach var="i" begin="1" end="${noOfPage}">
	 				<c:choose>
	 					<c:when test="${currentPage eq i}">
	 						<button class="btn btn-outline-info" disabled>${i}</button>
	 					</c:when>
	 					<c:otherwise>
	 						<a class="btn btn-outline-info" href="ProductController?page=${i}&action=all">${i}</a>
	 					</c:otherwise>
	 				</c:choose>
	 			</c:forEach>
	 		</div>
	 	</div>
    </div>
</div> <!-- End Of Explore Food Area -->


<%@ include file="footer.jsp" %>
