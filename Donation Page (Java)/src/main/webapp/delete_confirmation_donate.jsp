<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation</title>
<link href="css/confirmation.css" rel="stylesheet">
</head>
<body>
<%
	String url = request.getParameter("url");
	String action = request.getParameter("action");
	String postid = request.getParameter("postid");
	String userid = request.getParameter("userid");
	pageContext.setAttribute("url", url);
	pageContext.setAttribute("action", action);
	pageContext.setAttribute("postid", postid);
	pageContext.setAttribute("userid", userid);
%>
<!-- Modal HTML -->
<div id="myModal" class="modal fade">
  <div class="modal-dialog modal-confirm">
    <div class="modal-content">
      <div class="modal-header">
        <div class="icon-box">
          <i class="material-icons">&#xE5CD;</i>
        </div>
        <h4 class="modal-title">Are you sure?</h4>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      </div>
      <div class="modal-body">
        <p>Do you really want to delete these records? This process cannot be undone.</p>
      </div>
      <div class="modal-footer">
        <a href="index.jsp" class="btn btn-info" data-dismiss="modal">Cancel</a>
        <a href="${url}?action=${action}&userid=${userid}&postid=${postid}" class="btn btn-danger">Delete</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>