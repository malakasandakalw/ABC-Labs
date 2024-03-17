<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
   Object sessionAttribute = session.getAttribute("auth_receptionist_id");
   
   if (sessionAttribute != null) {
   	%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Receptionist - Appointment Test</title>
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
	               	<a class="nav-link" href="receptionists?type=get-appointments&session_id=${ auth_receptionist_id }">Appointments</a>
	               </li>
                  <li class="nav-item">
                  	<form method="post" action="receptionists">
                        <input type="hidden" name="auth_receptionist_id" value="${auth_receptionist_id}" required>
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
	            <h3 class="title">Appointment Test</h3>
	         </div>
	      </div>
	      <hr>
	      <div class="col-md-5 mx-auto bg-white p-5">
	      	<form method="post" action="receptionists">
	      		<div class="mb-3">
				    <label class="form-label">Test Number</label>
				    <input type="text" class="form-control" id="appointment_test_id" name="appointment_test_id" value="${appointmentTest.id}" required readonly>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Test</label>
				    <input type="text" class="form-control" value="${appointmentTest.testType.name}" required readonly>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Test Status</label>
				    <select class="form-select" id="appointment_test_status" name="appointment_test_status" required>
				    	<option value="Cancel" ${appointmentTest.status eq 'Cancel' ? 'selected' : ''}>Cancel</option>
				    	<option value="Processing" ${appointmentTest.status eq 'Processing' ? 'selected' : ''}>Processing</option>
				    	<option value="Conducted" ${appointmentTest.status eq 'Conducted' ? 'selected' : ''}>Conducted</option>
				    </select>
			    </div>
			    
			    <div class="mb-3">
				    <label class="form-label">Specified Technicians</label>
				    
				    <select class="form-select" id="appointment_test_technicians" name="appointment_test_technicians">
						<tag:forEach var="technician" items="${TestTypeTechnicians}">
							<option value="${technician.id}" ${technician.id eq appointmentTest.technician.id ? 'selected' : ''}>${technician.name}</option>
						</tag:forEach>
					</select>
				    
			    </div>
			    
			    <div class="mb-3">
				    <label class="form-label">Price</label>
				    <input type="number" class="form-control"value="${appointmentTest.testType.price}" required readonly>
			    </div>
		
			    <input type="hidden" id="test_type_id" name="test_type_id" value="${appointmentTest.testType.id}"/>
			    <input type="hidden" id="appointment_test_id" name="appointment_test_id" value="${appointmentTest.id}"/>
			    <input type="hidden" name="type" value="update-appointment-test"/>
			    
			    <button type="submit" class="btn btn-primary">Update Appointment Test</button>
	      	</form>
	      </div>
	   </div>
	</body>
</html>
<%
   } else {
      response.sendRedirect("receptionist-login.jsp");
      }
   %>