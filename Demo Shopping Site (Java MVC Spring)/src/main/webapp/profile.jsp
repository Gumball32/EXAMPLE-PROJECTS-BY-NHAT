<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<%@ page import="Model.User" %>
<% 
if (user1 == null) 
	{
		response.sendRedirect("ProductController");
		return;
	}
%>
<div class="container">
    <div class="row">
        <div class="user col-lg-3 me-3 mt-3 text-center">
            <img src="<%= ((User)user1).getImage() %>" alt="Avatar" class="avatar rounded mx-auto my-3 d-inline-block" style="width: 300px; height: 300px;">
            <h3 class="name orange-400 mt-1 text-center"><%= ((User)user1).getUsername() %></h3>
            <h3 class="name orange-400 mt-1 text-center" id="email"><%= ((User)user1).getEmail() %></h3>
            <h3 class="name orange-400 mt-1 text-center" id="fullname"><%= ((User)user1).getFullname() %></h3>
            <div class="justify-content-center py-5" id="contact" >
                <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#editModal" data-bs-whatever="@mdo">Edit</button>
                <% if (user1.getId() == 2) { %>
               		<button type="button" class="upload-btn" data-bs-toggle="modal" data-bs-target="#uploadModal" data-bs-whatever="@mdo">Upload</button>
               	<% } %>
            </div>

            <!------------------------------- Edit name using bootstrap modal----------------------->
            <form action = "UserController" method="POST" id="editUserForm" name="edit" role="form" enctype='multipart/form-data'>
                <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header position-relative">
                                <h1 class="modal-title fs-5 position-absolute top-50 start-50 translate-middle" id="exampleModalLabel">Edit</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                        
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label">Full name:</label>
                                    <input required value="<%= ((User)user1).getFullname() %>" type="text" class="form-control" name="fullname_input">
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-L_name" class="col-form-label">Username:</label>
                                    <input required value="<%= ((User)user1).getUsername() %>" type="text" class="form-control" name="username_input">
                                </div>
                                <div class="mb-3">
                                    <label id="recipient-address" class="col-form-label">Email:</label>
                                    <input required value="<%= ((User)user1).getEmail() %>" type="text" class="form-control" name="email_input">
                                </div>
                                <div class="mb-3">
                                    <label id="password" class="col-form-label">Password:</label>
                                    <input required value="<%= ((User)user1).getPassword() %>" type="text" class="form-control" name="password_input">
                                </div>
                                <div class="mb-3">
                                    <img src="<%= ((User)user1).getImage() %>" alt="Avatar" class="avatar rounded mx-auto my-3 d-inline-block d-flex" style="width: 300px; height: 300px;">
                                    <input type="text" class="hidden" name="current_image" value="<%= ((User)user1).getImage() %>" style="visibility:hidden">
                                    <input type="file" class="form-control my-2 phone-number-input " name="image_input">
                                </div>
                                <input class="hidden" name="action" value="update" style="visibility:hidden">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" name="update">Accept</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form action="ProductController" method="POST" id="UploadProductForm" name="upload" role="form" enctype='multipart/form-data'>
                <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header position-relative">
                                <h1 class="modal-title fs-5 position-absolute top-50 start-50 translate-middle" id="exampleModalLabel">Upload</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                        
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="recipient-L_name" class="col-form-label">Product Name</label>
                                    <input type="text" class="form-control" name="full_name">
                                </div>
                                <div class="mb-3">
                                    <label id="recipient-address" class="col-form-label">Description</label>
                                    <input type="text" class="form-control" name="description">
                                </div>
                                <div class="mb-3">
                                    <label id="recipient-address" class="col-form-label">Price</label>
                                    <input type="number" class="form-control" name="price">
                                </div>
                                <div class="mb-3">
                                	<label id="recipient-address" class="col-form-label">Image</label>
                                    <input type="file" class="form-control my-2 phone-number-input " name="image">
                                </div>
                                <div class="mb-3">
                                    <label id="recipient-address" class="col-form-label">Amount</label>
                                    <input type="number" class="form-control" name="amount">
                                </div>
                                <input class="hidden" name="action" value="upload" style="visibility:hidden">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" name="update">Upload</button>
                            </div>
                        
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-------------------------------End of Edit Name------------------------->

    <!--------------------------------------------------View history----------------------------------------------------------->   
                <div class="modal-content mb-3 mt-2 border-none">
                    <div class="modal-body ">
                        <table class="food-list-added-table table table-striped history-table fs-4">
                            <thead>
                                <tr>
                                    <th scope="col">Product Id</th>
                                    <th scope="col">Product name</th>
                                    <th scope="col">Ordering date</th>       
                                    <th scope="col">Amount</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                    </table>
                </div>
                <hr>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>