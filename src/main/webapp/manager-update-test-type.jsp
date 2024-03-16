<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="ISO-8859-1">
      <title>Manager - Update Test Type</title>
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
         <p>${message}</p>
         <div class="d-flex align-items-center mb-3">
            <div class="me-auto">
               <h3 class="title">Update Test Type</h3>
            </div>
         </div>
         <hr>
         <div class="col-md-5 mx-auto p-5 bg-white">
            <form method="post" action="testType">
               <div class="mb-3">
                  <label class="form-label">Test Name</label>
                  <input type="text" class="form-control" id="test_name" name="test_name" value="${testType.name}" required>
               </div>
               <div class="mb-3">
                  <label class="form-label">Price</label>
                  <input type="number" class="form-control" id="test_price" name="test_price" value="${testType.price}" required>
               </div>
               <div class="mb-3">
                  <tag:set var="isActive" value="${testType.isActive}" />
                  <label class="form-label">Active Status</label>			
                  <select class="form-select" id="test_is_active" name="test_is_active" >
                  <option value="1" ${isActive == 1 ? 'selected' : ''}>Active</option>
                  <option value="0" ${isActive == 0 ? 'selected' : ''}>Inactive</option>
                  </select>
               </div>
               <input type="hidden" name="test_type_id" value="${testType.id}"/>
               <input type="hidden" name="type" value="update"/>
               <button type="submit" class="btn btn-primary">Update Test</button>
            </form>
         </div>
      </div>
   </body>
</html>