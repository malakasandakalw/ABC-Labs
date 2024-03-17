<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
Object sessionAttribute = session.getAttribute("auth_manager_id");

if (sessionAttribute != null) {

  	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager - Create Technician</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://unpkg.com/@adminkit/core@latest/dist/css/app.css">
<script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>
<link href="css/style.css">
</head>
<body>

	<nav class="navbar navbar-expand-md bg-body-tertiary">
		<div class="container">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						href="managers?type=get-dashboard&session_id=${auth_manager_id}">Dashboard</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-managers">Managers</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-receptionists">Receptionists</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-technicians">Technicians</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-test-types">Test Types</a></li>
						
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
            <div class="me-auto">
               <h3 class="title">Create Technician</h3>
            </div>
            <hr>
		<div class="col-md-5 mx-auto bg-white p-5">

			<form method="post" action="technicians">
			  <div class="mb-3">
			    <label class="form-label">Technician Name</label>
			    <input type="text" class="form-control" id="technician_name" name="technician_name" required>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Email</label>
			    <input type="email" class="form-control" id="technician_email" name="technician_email" required>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Password (default password)</label>
			    <input type="password" class="form-control" id="technician_password" name="technician_password" required>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Specified Tests</label>
			    <select class="form-select" id="technician_test_types" name="technician_test_types" required multiple>
			    	<tag:forEach var="testType" items="${testTypesList}">	
					  <option value="${testType.id}">${testType.name} - ${testType.isActive == 1 ? 'Active' : 'Inactive'}</option>					
					</tag:forEach>
			    </select>
			  </div>			  
			  <input type="hidden" name="type" value="create"/>
			  <button type="submit" class="btn btn-primary">Create Technician</button>
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