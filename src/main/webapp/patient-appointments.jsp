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
      <title>Patient Appointments</title>
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
               <h3 class="title">Appointments</h3>
            </div>
            <div class="ms-auto">
               <form method="get" action="appointments">
                  <input type="hidden" name="type" value="new-appointment"/>
                  <input type="hidden" name="session_id_type" value="patient"/>
                  <input type="hidden" name="session_id" value="${auth_patient_id}"/>
                  <button type="submit" class="btn btn-primary">Create Appointment</button>
               </form>
            </div>
         </div>
         <div class="table-container mt-3">
         	<input class="form-control" type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search by appointment number.." title="">
         	
	         <table class="table table-stripped mt-3" id="dataTable">
	            <thead>
	               <tr>
	                  <th>Appointment Number</th>
	                  <th>Created At</th>
	                  <th>Status</th>
	                  <th class="text-end pe-5">Price (Rs.)</th>
	                  <th class="ps-5">Actions</th>
	               </tr>
	            </thead>
	            <tbody>
	               <tag:forEach var="appointment" items="${patientsAppointmentsList}">
	                  <tr class="filter">
	                     <td>${appointment.id}</td>
	                     <td>${appointment.createdAt}</td>
	                     <td>${appointment.status}</td>
	                     <td class="text-end pe-5">${appointment.totalPrice}</td>
	                     <td class="ps-5">
	                        <div class="d-flex gap-2">
		                        <div class="action-div">
			                        <form method="get" action="patients">
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
      <script>
		function searchFunction() {
		  var input, filter, table, tr, td, i, txtValue;
		  
		  input = document.getElementById("searchInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("dataTable");
		  tr = table.getElementsByClassName("filter");
		  
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[0];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		  
		}
		</script>
   </body>
</html>
<%
   } else {
      response.sendRedirect("patient-login.jsp");
      }
   %>