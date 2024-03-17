<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	Object sessionAttribute = session.getAttribute("auth_manager_id");
	
	if (sessionAttribute != null) {

   	%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Manager - Add Test Type</title>
	   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	   <link rel="stylesheet" href="https://unpkg.com/@adminkit/core@latest/dist/css/app.css">
	   <script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>
	   <link href="css/style.css">
	</head>
	<body>
	   <nav class="navbar navbar-expand-md bg-body-tertiary">
	      <div class="container">
	         <div class="collapse navbar-collapse" id="navbarSupportedContent">
	            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
	               <li class="nav-item">
	                  <a class="nav-link" href="managers?type=get-dashboard&session_id=${auth_manager_id}">Dashboard</a>
	               </li>
	               <li class="nav-item">
	                  <a class="nav-link" href="manager-managers">Managers</a>
	               </li>
	               <li class="nav-item">
	                  <a class="nav-link" href="manager-receptionists">Receptionists</a>
	               </li>
	               <li class="nav-item">
	                  <a class="nav-link" href="manager-technicians">Technicians</a>
	               </li>
	               <li class="nav-item">
	                  <a class="nav-link active" href="manager-test-types">Test Types</a>
	               </li>
						
                  <li class="nav-item">
                  	<form method="post" action="managers">
                        <input type="hidden" name="auth_manager_id" value="${auth_manager_id}"" required>
                        <input type="hidden" name="type" value="logout"/>
                        <button type="submit" class="btn btn-danger">Logout</button>
                     </form>
                  </li>
	            </ul>
	         </div>
	      </div>
	   </nav>
	   <div class="container">
	      <p>${message}</p>
	      <div class="d-flex align-items-center mb-3">
	         <div class="me-auto">
	            <h3 class="title">Create Test Type</h3>
	         </div>
	      </div>
	      <hr>
	      <div class="col-md-5 mx-auto bg-white p-5">
	         <form method="post" action="testType">
	            <div class="mb-3">
	               <label class="form-label">Test Name</label>
	               <input type="text" class="form-control" id="test_name" name="test_name" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">Price (Rs.)</label>
	               <input type="number" class="form-control" id="test_price" name="test_price" required>
	            </div>
	            <input type="hidden" name="type" value="create"/>
	            <button type="submit" class="btn btn-primary">Create Test</button>
	         </form>
	      </div>
	   </div>
	</body>
</html>
<%

	   } else {
	      response.sendRedirect("manager-login.jsp");
	      }

   %>