<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	Object sessionAttribute = session.getAttribute("auth_manager_id");
	
	if (sessionAttribute != null) {

   	%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Manager Test Types</title>
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
                        <input type="hidden" name="auth_manager_id" value="${auth_manager_id}" required>
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
	            <h3 class="title">Test types</h3>
	         </div>
	         <div class="ms-auto">
	            <a class="btn btn-primary" href="manager-add-new-test-type.jsp">Create Test Type</a>
	         </div>
	      </div>
	      <hr>
	      <div class="table-container">
	      <input class="form-control" type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search by name.." title="">
	         <table class="table table-stripped" id="dataTable">
	            <thead>
	               <tr>
	                  <th>Test Type</th>
	                  <th>Price</th>
	                  <th>Active Status</th>
	                  <th>Actions</th>
	               </tr>
	            </thead>
	            <tbody>
	               <tag:forEach var="testType" items="${testTypesList}">
	                  <tr class="filter">
	                     <td>${testType.name}</td>
	                     <td>${testType.price}</td>
	                     <td>${testType.isActive == 1 ? 'Active' : 'Inactive'}</td>
	                     <td>
	                        <div class="d-flex gap-2">
		                        <div class="action-div">
			                        <form method="get" action="testType">
			                           <input type="hidden" name="test_type_id" value="${testType.id}" required>
			                           <input type="hidden" name="type" value="update-specific"/>
			                           <button type="submit" class="btn btn-success">Update</button>
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
	      response.sendRedirect("manager-login.jsp");
	      }

   %>