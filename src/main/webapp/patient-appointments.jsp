<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
						href="patient-appointments">Appointments</a>
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
					  <button type="submit" class="btn btn-primary">Create Appointment</button>
					</form>
	         </div>
	      </div>
	      <hr>
		<table class="table table-stripped">
			<thead>
			</thead>
			<tbody>
			
			</tbody>

		</table>
	</div>
</body>
</html>