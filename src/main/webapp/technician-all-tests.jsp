<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	Object sessionAttribute = session.getAttribute("auth_technician_id");
	
	if (sessionAttribute != null) {

   	%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="ISO-8859-1">
      <title>Technician Tests</title>
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
                     href="technicians?type=get-tests&session_id=${auth_technician_id}">Appointment Tests</a>
                  </li>
                  <li class="nav-item">
                  	<form method="post" action="technicians">
                        <input type="hidden" name="auth_technician_id" value="${auth_technician_id}"" required>
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
         <p>${auth_technician_id}</p>
         <div class="d-flex align-items-center mb-3">
            <div class="me-auto">
               <h3 class="title">Tests</h3>
            </div>
         </div>
         <hr>
         <div class="table-container">
	         <table class="table table-stripped">
	            <thead>
	               <tr>
	                  <th>Appointment Number</th>
	                  <th>Appointment Status</th>
	                  <th>Test Status</th>
	                  <th>Actions</th>
	               </tr>
	            </thead>
	            <tbody>
	               <tag:forEach var="technicianAppointmentTest" items="${technicianAppointmentTests}">
	                  <tr>
	                     <td>${technicianAppointmentTest.appointment.id}</td>
	                     <td>${technicianAppointmentTest.appointment.status}</td>
	                     <td>${technicianAppointmentTest.status}</td>
	                     <td>
	                        <div class="d-flex gap-2">
		                        <div class="action-div">
			                        <form method="get" action="technicians">
			                           <input type="hidden" name="appointment_test_id" value="${technicianAppointmentTest.id}" required>
			                           <input type="hidden" name="type" value="get-specific-appointment-test"/>
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
<%

	   } else {
	      response.sendRedirect("technician-login.jsp");
	      }

   %>