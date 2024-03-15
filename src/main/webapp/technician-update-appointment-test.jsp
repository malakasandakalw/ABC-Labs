<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="ISO-8859-1">
      <title>Technician Appointment Test</title>
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
                     href="">Appointment Tests</a>
                  </li>
               </ul>
            </div>
         </div>
      </nav>
      <div class="container">
         <p>${message}</p>
         <p>${auth_technician_id}</p>
         <div class="col-md-10 mx-auto">
	      		<div class="mb-3">
				    <label class="form-label">Appointment Number : ${appointmentTest.appointment.id}</label>
			    </div>
			    <div class="row">
			    	<div class="col-md-12">
					    <div class="mb-3">
						    <div class="form-label">Test Number : ${appointmentTest.id}</div>
					    </div>
			    	</div>
			    	<div class="col-md-12">
					    <div class="mb-3">
						    <div class="form-label">Test : ${appointmentTest.testType.name}</div>
					    </div>
			    	</div>
				    <div class="col-md-12">
					    <div class="mb-3">
						    <div class="form-label">Appointment Date : ${appointmentTest.appointment.date}</div>
					    </div>
				    </div>
				    <div class="col-md-12">
					    <div class="mb-3">
						    <div class="form-label">Appointment Status : ${appointmentTest.appointment.status}</div>
					    </div>
				    </div>
				    <div class="col-md-12">
				    	<h4>Patient Details</h4>
				    	<hr>
					    <div class="mb-3">
						    <div class="form-label">Name : ${appointmentTest.patient.name}</div>
					    </div>
					    <div class="mb-3">
						    <div class="form-label">Contact Number : ${appointmentTest.patient.contactNumber}</div>
					    </div>
					    <div class="mb-3">
						    <div class="form-label">Doctor Details : ${appointmentTest.appointment.details}</div>
					    </div>
				    </div>
				    <div class="col-md-12">
				    	<h4>Test Details</h4>
				    	<hr>
				    
					<form action="technicians" method="post" enctype="multipart/form-data">
					    <div class="mb-3">
						    <label class="form-label">Test Status</label>
						    <select class="form-select" id="appointment_test_status" name="appointment_test_status" required>
						    	<option value="Cancel" ${appointmentTest.status eq 'Cancel' ? 'selected' : ''}>Cancel</option>
						    	<option value="Processing" ${appointmentTest.status eq 'Processing' ? 'selected' : ''}>Processing</option>
						    	<option value="Confirmed" ${appointmentTest.status eq 'Confirmed' ? 'selected' : ''}>Confirmed</option>
						    	<option value="Conducted" ${appointmentTest.status eq 'Conducted' ? 'selected' : ''}>Conducted</option>
						    </select>
					    </div>
				    	<div class="mb-3">
						    <label class="form-label">Test Result</label>
						    <div class="">
		        				<input type="file" name="file">
						    </div>
					    </div>
						<input type="hidden" name="appointment_test_id" value="${appointmentTest.id}"/>
						<input type="hidden" name="appointment_technician_id" value="${auth_technician_id}"/>
						
						
						<input type="hidden" name="type" value="update-specific-appointment-test"/>
				        <button type="submit" class="btn btn-primary">Update</button>
				    </form>
				    
			    </div>
	      </div>
      </div>
   </body>
</html>