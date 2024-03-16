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
	               	<a class="nav-link" href="receptionists?type=get-appointments&session_id=${ auth_receptionist_id }">Appointments</a>
	               </li>
	            </ul>
	         </div>
	      </div>
	   </nav>
	   <div class="container">
	      <p>${message}</p>
	      ${ auth_receptionist_id }
	      <div class="d-flex align-items-center mb-3">
	         <div class="me-auto">
	            <h3 class="title">All Appointments</h3>
	         </div>
	      </div>
	      <hr>
	      <div class="table-container">
	         <table class="table table-stripped">
	            <thead>
	               <tr>
	                  <th>Appointment Number</th>
	                  <th>Status</th>
	                  <th>Actions</th>
	               </tr>
	            </thead>
	            <tbody>
	               <tag:forEach var="appointment" items="${appointmentsList}">
	                  <tr>
	                     <td>${appointment.id}</td>
	                     <td>${appointment.status}</td>
	                     <td>
	                        <div class="d-flex gap-2">
		                        <div class="action-div">
			                        <form method="get" action="receptionists">
			                           <input type="hidden" name="appointment_id" value="${appointment.id}" required>
			                           <input type="hidden" name="type" value="get-specific-appointment"/>
			                           <button type="submit" class="btn btn-success">View</button>
			                        </form>
		                        </div>
	                        </div>
	                     </td>
	                  </tr>
	               </tag:forEach>
	            </tbody>
	         </table>
	      </div>
	   </div>
	</body>
</html>