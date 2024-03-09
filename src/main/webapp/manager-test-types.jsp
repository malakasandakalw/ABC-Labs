<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Test Types</title>
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
	<a href="manager-add-new-test-type.jsp">Create New Test</a>
		<table class="table table-stripped">
			<thead>
			</thead>
			<tbody>
				<tag:forEach var="testType" items="${testTypesList}">
					<tr>
						<td>${testType.name}</td>
						<td>${testType.price}</td>
						<td>
							<form method="get" action="testType">
							  <input type="hidden" name="test_type_id" value="${testType.id}" required>
							  <input type="hidden" name="type" value="update-specific"/>
							  <button type="submit" class="btn btn-success">Update</button>
							</form>
						</td>
						<td>
							<form method="post" action="testType">
							  <input type="hidden" name="test_type_id" value="${testType.id}" required>
							  <input type="hidden" name="type" value="delete-specific"/>
							  <button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
				</tag:forEach>
			</tbody>

		</table>
	</div>
</body>
</html>