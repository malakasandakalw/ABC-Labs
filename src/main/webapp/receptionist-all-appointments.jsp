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
	               	<a class="nav-link active" href="receptionists?type=get-appointments&session_id=${ auth_receptionist_id }">Appointments</a>
	               </li>
	               <li class="nav-item">
	               	<a class="nav-link" href="receptionist-update-password.jsp">Change Password</a>
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
	            <h3 class="title">All Appointments</h3>
	         </div>
	      </div>
	      <hr>
	      <div class="table-container">
	      <input class="form-control" type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search by appointment number.." title="">
	         <table class="table table-stripped" id="dataTable">
	            <thead>
	               <tr>
	                  <th>Appointment Number</th>
	                  <th>Created At</th>
	                  <th>Price (Rs.)</th>
	                  <th>Status</th>
	                  <th>Actions</th>
	               </tr>
	            </thead>
	            <tbody>
	               <tag:forEach var="appointment" items="${appointmentsList}">
	                  <tr class="filter">
	                     <td>${appointment.id}</td>
	                     <td>${appointment.createdAt}</td>
	                     <td>${appointment.totalPrice}</td>
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
      response.sendRedirect("receptionist-login.jsp");
      }
   %>