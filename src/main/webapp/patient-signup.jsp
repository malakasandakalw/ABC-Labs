<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Patient - Sign Up</title>
	   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	   <link rel="stylesheet" href="https://unpkg.com/@adminkit/core@latest/dist/css/app.css">
	   <script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>
	   <link href="css/style.css">
	</head>
	<body>
	   <div class="container">
	      <p>${message}</p>
	      <div class="col-md-5 mx-auto bg-white p-5">
	         <form method="post" action="patients">
	            <div class="mb-3">
	               <label class="form-label">Name</label>
	               <input type="text" class="form-control" id="name" name="name" value="${name}" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">E-mail</label>
	               <input type="email" class="form-control" id="email" name="email" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">Contact Number</label>
	               <input type="tel" class="form-control" id="contact_number" name="contact_number" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">Date of Birth</label>
	               <input type="date" class="form-control" id="dob" name="dob" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">Password</label>
	               <input type="password" class="form-control" id="password" name="password" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">Confirm Password</label>
	               <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
	            </div>
	            <input type="hidden" name="type" value="create"/>
	            <button type="submit" class="btn btn-primary">Sign Up</button>
	         </form>
	      </div>
	   </div>
	</body>
</html>