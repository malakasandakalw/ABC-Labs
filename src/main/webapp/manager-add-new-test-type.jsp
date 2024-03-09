<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager - Add Test Type</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link href="css/style.css">
</head>
<body>

	<nav class="navbar navbar-expand-md bg-body-tertiary">
		<div class="container">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						href="manager-dashboard">Dashboard</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-managers">Managers</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-receptionists">Receptionists</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-technicians">Technicians</a></li>
					<li class="nav-item"><a class="nav-link"
						href="manager-test-types">Test Types</a></li>
				</ul>
			</div>
	</nav>
	<div class="container">
	<p>${message}</p>
		<div class="col-md-5 mx-auto">
			<form method="post" action="testType">
			  <div class="mb-3">
			    <label class="form-label">Test Name</label>
			    <input type="text" class="form-control" id="test_name" name="test_name" required>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Price</label>
			    <input type="number" class="form-control" id="test_price" name="test_price" required>
			  </div>
			  <input type="hidden" name="type" value="create"/>
			  <button type="submit" class="btn btn-primary">Create Test</button>
			</form>
		</div>
	</div>
</body>
</html>