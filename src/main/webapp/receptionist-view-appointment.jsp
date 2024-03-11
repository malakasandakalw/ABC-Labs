<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Receptionist - Appointments</title>
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
	               	<a class="nav-link" href="receptionist-all-appointments">Appointments</a>
	               </li>
	            </ul>
	         </div>
	      </div>
	   </nav>
	   <div class="container">
	      <p>${message}</p>
	      <div class="d-flex align-items-center mb-3">
	         <div class="me-auto">
	            <h3 class="title">All Appointments</h3>
	         </div>
	      </div>
	      <hr>
	      <div class="col-md-5 mx-auto">
	      	<form method="post" action="appointments">
	      		<div class="mb-3">
				    <label class="form-label">Appointment Number</label>
				    <input type="text" class="form-control" id="appointment_id" name="appointment_id" value="${appointment.id}" required readonly>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Patient's Contact Number</label>
				    <input type="text" class="form-control" value="${appointment.contactNumber}" required readonly>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Patient's Email</label>
				    <input type="text" class="form-control" value="${appointment.email}" required readonly>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Doctor Details</label>
				    <p>${appointment.details}</p>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Appointment Date</label>
				    <input type="date" class="form-control" id="appointment_date" name="appointment_date" value="${appointment.date}" required>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Appointment Status</label>
				    <select class="form-select" id="appointment_status" name="appointment_status" required>
				    	<option value="Cancel" ${appointment.status eq 'Cancel' ? 'selected' : ''}>Done</option>
				    	<option value="Processing" ${appointment.status eq 'Processing' ? 'selected' : ''}>Processing</option>
				    	<option value="Confirmed, Waiting for payment" ${appointment.status eq 'Confirmed, Waiting for payment' ? 'selected' : ''}>Confirmed, Waiting for payment</option>
				    	<option value="Payment Done" ${appointment.status eq 'Payment Done' ? 'selected' : ''}>Payment Done</option>
				    	<option value="Done" ${appointment.status eq 'Done' ? 'selected' : ''}>Done</option>
				    </select>
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Appointment Tests</label>
				    
				    <table class="table table-stripped">
				    	<thead>
			               <tr>
			                  <th>Test</th>
			                  <th>Status</th>
			                  <th>Actions</th>
			               </tr>
			            </thead>
	            		<tbody>
						    <tag:forEach var="appointmentTest" items="${appointment.appointmentTests}">
						    	<tr>
						    		<td>${appointmentTest.testType.name}</td>
						    		<td>${appointmentTest.status}</td>
						    		<td>
						    			<form>
			    							<button type="submit" class="btn btn-primary">Update Appointment</button>
						    			</form>
						    		</td>
						    	</tr>
						    </tag:forEach>
	            		</tbody>
				    </table>
				    
			    </div>
			    <div class="mb-3">
				    <label class="form-label">Total Price</label>
				    <input type="number" class="form-control" id="appointment_price" name="appointment_price" value="${appointment.totalPrice}" required>
			    </div>
			    <p>Created at: ${appointment.createdAt}</p>
			    <input type="hidden" name="type" value="update"/>
			    <button type="submit" class="btn btn-primary">Update Appointment</button>
	      	</form>
	      </div>
	   </div>
	</body>
</html>