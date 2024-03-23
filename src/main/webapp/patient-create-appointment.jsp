<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
	
	<%
   Object sessionAttribute = session.getAttribute("auth_patient_id");
   
   if (sessionAttribute != null) {
   	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient - Create Appointment</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
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
	                     href="patients?type=get-appointments&session_id=${auth_patient_id}">Appointments</a>
	                  </li>
                  <li class="nav-item"><a class="nav-link"
                     href="patients?type=get-specific-patient-for-update&session_id=${auth_patient_id}">Update Profile</a>
                  </li>
                  <li class="nav-item">
                  	<form method="post" action="patients">
                        <input type="hidden" name="auth_patient_id" value="${auth_patient_id}" required>
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
	            <h3 class="title">Create Appointment</h3>
	         </div>
	      </div>
	      <hr>
		<div class="col-md-5 mx-auto bg-white p-5">
			<form method="post" action="appointments">
			  <div class="mb-3">
			    <label class="form-label">Recommended Doctor details <small class="text-danger">(* required)</small></label>
			    <textarea class="form-control" id="details" name="details" required></textarea>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Required Tests <small class="text-danger">(* required)</small></label>
			    <select class="form-select" id="tests" name="tests" required multiple>
			    	<tag:forEach var="testType" items="${testTypesList}">	
					  <option value="${testType.id}">${testType.name} - Rs. ${testType.price}</option>					
					</tag:forEach>
			    </select>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Email <small class="text-danger">(* required)</small></label>
			    <input type="email" class="form-control" id="email" name="email" required>
			  </div>			  
			  <div class="mb-3">
			    <label class="form-label">Contact Number <small class="text-danger">(* required)</small></label>
			    <input type="tel" class="form-control" id="contact_number" name="contact_number" required>
			  </div>			  
			  <input type="hidden" name="type" value="create"/>
              <input type="hidden" name="session_id_type" value="patient"/>
              <input type="hidden" name="session_id" value="${auth_patient_id}"/>
			  <button type="submit" class="btn btn-primary">Create Appointment</button>
			</form>

		</div>
	</div>
</body>
</html>
<%
   } else {
      response.sendRedirect("patient-login.jsp");
      }
   %>