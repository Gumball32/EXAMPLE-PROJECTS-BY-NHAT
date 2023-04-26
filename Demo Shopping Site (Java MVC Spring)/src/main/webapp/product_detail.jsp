<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<%@ page import="Model.Product" %>
<%
	Product product = (Product) session.getAttribute("product");

	if (product == null) {
		response.sendRedirect("ProductController");
	}
%>
<div class="container">
    <div class="detail-product-page mt-5 bg-white">
        <div class="row ">
            <div class="detail-picture col-lg-6 card">
                <img src="${product.getImage()}" alt="lotso-detail">
            </div>
            <div class="col-lg-6 mt-5">
                <div class="row">
                    <div class="info-product col-lg-12 mx-3 mb-5">
                        <h3>Name: ${product.getName()}</h3>
                        <h3>Description: ${product.getDescription()}</h3>
                        <h3>Price: ${product.getPrice()}</h3>
                        <h3>Stock: ${product.getAmount()}</h3>
                    </div>
                    <div class="detail-payment-button col-lg-12 mt-5">
                       <!--  <div class="d-flex">
                            <h3>Quantity:</h3>
                            <div class="amount-product d-flex border-radius-25px col-9">
                                <input name="amount-product" type='text' class="amount-product-spinner form-control pe-none left-border-radius-25px h-100 ps-4" value="0" />
                                <button type="button" class="btn btn-secondary p-2 rounded-0 stepUp"><i class="fa-solid fa-arrow-up"></i></button>
                                <button type="button" class="btn btn-secondary p-2 right-border-radius-25px stepDown"><i class="fa-solid fa-arrow-down"></i></button>
                            </div>
                        </div> -->
                        <% if (user1 != null) { %>
                        <div class="detail-product-button my-3 ">
                            <a href="CartController?image=${product.getImage()}&action=addItem&id=${product.getId()}&name=${product.getName()}&price=${product.getPrice()}&description=${product.getDescription()}&amount=1" class="btn btn-outline-success col-lg-4 m-1" >
                                <i class="fa-solid fa-basket-shopping px-1"></i>Add card             
                            </a>
                            <button type="button" class="btn btn-info col-lg-4 m-1" >
                                <h4> Buy now</h4>             
                            </button>
                            <% if (user1.getId() == 2) { %>
	                            <button type="button" class="btn btn-outline-warning col-lg-4 m-1" data-bs-toggle="modal" data-bs-target="#editModal" data-bs-whatever="@mdo">
	                                <h4> <i class="fa-solid fa-pen-to-square px-1"></i>Edit</h4>             
	                            </button>  
	                            <button type="button" class="btn btn-outline-danger col-lg-4 m-1" data-bs-toggle="modal" data-bs-target="#editModal-01" data-bs-whatever="@mdo">
	                                <h4><i class="fa-solid fa-trash-can px-1"></i>Delete</h4>             
	                            </button>
                            <% } %>
                        </div>
                        <% } %>
                    </div>
                </div>  
            </div>
        </div>
    </div>
</div>

<form action = "ProductController" method="POST" id="editProductForm" name="edit" role="form" class="col-lg-0" enctype='multipart/form-data'>
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
                    <div class="modal-header position-relative">
                        <h1 class="modal-title fs-5 position-absolute top-50 start-50 translate-middle" id="exampleModalLabel">Edit</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                                            
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="recipient-name" class="col-form-label">Name</label>
                        <input value="${product.getName()}" type="text" class="form-control" name="name">
                    </div>
                    <div class="mb-3">
                        <label for="recipient-L_name" class="col-form-label">Description</label>
                        <input value="${product.getDescription()}" type="text" class="form-control" name="description">
                    </div>
                    <div class="mb-3">
                        <label id="recipient-address" class="col-form-label">Price</label>
                        <input value="${product.getPrice()}" type="number" class="form-control" name="price">
                    </div>
                    <div class="mb-3">
                        <label id="recipient-address" class="col-form-label">Amount</label>
                        <input value="${product.getAmount()}" type="number" class="form-control" name="amount">
                    </div>
                    <div class="mb-3">
	                    <img src="${product.getImage()}" alt="Avatar" class="avatar rounded mx-auto my-3 d-inline-block d-flex" style="width: 300px; height: 300px;">
	                    <input type="text" class="hidden" name="current_image" value="${product.getImage()}" style="visibility:hidden">
	                    <input type="file" class="form-control my-2 phone-number-input " name="image">
	                </div>
	                <input class="hidden" name="id" value="${product.getId()}" style="visibility:hidden">
	                <input class="hidden" name="action" value="update" style="visibility:hidden">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" name="update">Update</button>
                </div>
            </div>
        </div>
    </div>
</form>

<form action = "detail_product.php" method="POST" id="editProductForm" name="edit" role="form" class="col-lg-0">
    <div class="modal fade" id="editModal-01" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
                    <div class="modal-header position-relative">
                        <h1 class="modal-title fs-5 position-absolute top-50 start-50 translate-middle" id="exampleModalLabel">Delete Product</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                                            
                <div class="modal-body">
                    <div class="mb-3">
                        <h4>Are you sure you want to delete this product?</h4>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <a href="ProductController?action=delete&id=${product.getId()}" type="submit" class="btn btn-danger" name="delete">Delete</a>
                </div>
            </div>
        </div>
    </div>
</form>
<%@ include file="footer.jsp" %>