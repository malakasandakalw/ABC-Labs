<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Dashboard</title>
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
<style>
	h1 {
	margin-bottom: 0px;
	}
	.count-circle {
		width: max-content;
		padding-left: 20px;
		padding-right: 20px;
	    height: 50px;
	    border-radius: 100px;
	    background: #f9f9f9;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	}
</style>
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
		<div class="d-flex align-items-center mt-3">
	         <div class="me-auto">
	            <h3 class="title">Dashboard</h3>
	         </div>
	      </div>
	      <hr>
	      <div class="table-container">
	      
	      	<div class="row">
	      		<div class="col-md-4">
	      			<div class="card">
					  <div class="card-body">
					  	<div class="count-circle">
					  		<h1>${dashboard.totalAppointmentsCount}</h1>
					  	</div>
					  	<div class="count mt-3">
					  		<h4>Total Appointments</h4>
					  	</div>
					  </div>
					</div>
	      		</div>
	      		<div class="col-md-4">
	      			<div class="card">
					  <div class="card-body">
					  	<div class="count-circle">
					  		<h1>${dashboard.doneAppointmentsCount}</h1>
					  	</div>
					  	<div class="count mt-3">
					  		<h4>Done Appointments</h4>
					  	</div>
					  </div>
					</div>
	      		</div>
	      		<div class="col-md-4">
	      			<div class="card">
					  <div class="card-body">
					  	<div class="count-circle">
					  		<h1>${dashboard.cancelledAppointmentsCount}</h1>
					  	</div>
					  	<div class="count mt-3">
					  		<h4>Cancelled Appointments</h4>
					  	</div>
					  </div>
					</div>
	      		</div>
	      		<div class="col-md-4">
	      			<div class="card">
					  <div class="card-body">
					  	<div class="count-circle">
					  		<h1>${dashboard.processingAppointmentsCount}</h1>
					  	</div>
					  	<div class="count mt-3">
					  		<h4>Processing Appointments</h4>
					  	</div>
					  </div>
					</div>
	      		</div>
	      		<div class="col-md-4">
	      			<div class="card">
					  <div class="card-body">
					  	<div class="count-circle">
					  		<h1>${dashboard.totalPatients}</h1>
					  	</div>
					  	<div class="count mt-3">
					  		<h4>Total Patients</h4>
					  	</div>
					  </div>
					</div>
	      		</div>
	      		<div class="col-md-4">
	      			<div class="card">
					  <div class="card-body">
					  	<div class="count-circle">
					  		<h1>${dashboard.totalEarning}</h1>
					  	</div>
					  	<div class="count mt-3">
					  		<h4>Total Earnings (Rs.)</h4>
					  	</div>
					  </div>
					</div>
	      		</div>
	      	</div>	         
	      </div>
	</div>

</body>
</html>