<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Receptionist - Login</title>
	   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	   <link rel="stylesheet" href="https://unpkg.com/@adminkit/core@latest/dist/css/app.css">
	   <script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>
	   <link href="css/style.css">
	</head>
	<body>
	   <div class="container">
	      <p>${message}</p>
	      <p>${auth_receptionist_id}</p>
	      <div class="col-md-5 mx-auto bg-white p-5">
	         <form method="get" action="receptionists">
	            <div class="mb-3">
	               <label class="form-label">E-mail</label>
	               <input type="email" class="form-control" id="email" name="email" required>
	            </div>
	            <div class="mb-3">
	               <label class="form-label">Password</label>
	               <input type="password" class="form-control" id="password" name="password" required>
	            </div>
	            <input type="hidden" name="type" value="login"/>
	            <button type="submit" class="btn btn-primary">Login</button>
	         </form>
	      </div>
	   </div>
	</body>
</html>