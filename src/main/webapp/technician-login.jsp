<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="ISO-8859-1">
	   <title>Technician - Login</title>
	   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	   <link rel="stylesheet" href="https://unpkg.com/@adminkit/core@latest/dist/css/app.css">
	   <script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>
	   <link href="css/style.css">
	   	   <style>
        h1 {
            font-size: 42px;
            font-weight: 700;
        }

        h5 {
            line-height: 26px;
            font-size: 16px;
        }

        .form-container {
            background-color: white;
            border-radius: 6px;
        }
    </style>
	</head>
	<body>
	    <nav class="navbar navbar-expand-lg navbar-light bg-light">
	        <div class="container">
	            <a class="navbar-brand" href="/">ABC Labs Online</a>
	            <div class="collapse navbar-collapse" id="navbarSupportedContent">
	                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
	                    <li class="nav-item me-2">
	                        <a class="btn btn-secondary" href="patient-login.jsp">Login</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="btn btn-warning" href="patient-signup.jsp">Sign up</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </nav>
	   <div class="container mt-5 mb-5">
	   
	   		<div class="row">
	            <div class="col-md-6 d-flex align-items-center">
	                <div class="mx-auto">
	                    <h1>Technician Login</h1>
	                </div>
	            </div>
	            <div class="col-md-6">
	      			<p>${message}</p>
	                <div class="form-container p-5">
	                     <form method="get" action="technicians">
				            <div class="mb-3">
				               <label class="form-label">E-mail <small class="text-danger">(* required)</small></label>
				               <input type="email" class="form-control" id="email" name="email" required>
				            </div>
				            <div class="mb-3">
				               <label class="form-label">Password <small class="text-danger">(* required)</small></label>
				               <input type="password" class="form-control" id="password" name="password" required>
				            </div>
				            <input type="hidden" name="type" value="login"/>
				            <button type="submit" class="btn btn-primary">Login</button>
				         </form>
	                </div>
	            </div>
	        </div>
	   </div>
	   <footer class="bg-dark pt-3 pb-3">
        <div class="container">
            <div class="d-flex">
                <p class="text-white mb-0">ABC Labs Online</p>
                <div class="ms-auto d-flex">
                    <a href="manager-login.jsp" class="text-white me-3 mb-0">Manager Login</a>
                    <a href="receptionist-login.jsp" class="text-white me-3 mb-0">Receptionist Login</a>
                    <a href="technician-login.jsp" class="text-white mb-0">Technician Login</a>
                </div>
            </div>
        </div>
    </footer>
	</body>
</html>