<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
	               	<a class="nav-link" href="manager-dashboard">Dashboard</a>
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
	            <a class="btn btn-primary" href="manager-add-new-test-type.jsp">Create New Test</a>
	         </div>
	      </div>
	      <hr>
	      <div class="table-container">
	         <table class="table table-stripped">
	            <thead>
	               <tr>
	                  <th>Test Type</th>
	                  <th>Price</th>
	                  <th>Actions</th>
	               </tr>
	            </thead>
	            <tbody>
	               <tag:forEach var="testType" items="${testTypesList}">
	                  <tr>
	                     <td>${testType.name}</td>
	                     <td>${testType.price}</td>
	                     <td>
	                        <div class="d-flex gap-2">
		                        <div class="action-div">
			                        <form method="get" action="testType">
			                           <input type="hidden" name="test_type_id" value="${testType.id}" required>
			                           <input type="hidden" name="type" value="update-specific"/>
			                           <button type="submit" class="btn btn-success">Update</button>
			                        </form>
		                        </div>
		                        <div class="action-div">
		                           <tag:choose>
		                              <tag:when test="${testType.isActive == 1}">
		                                 <form method="post" action="testType">
		                                    <input type="hidden" name="test_type_id" value="${testType.id}" required>
		                                    <input type="hidden" name="type" value="deactivate-specific"/>
		                                    <button type="submit" class="btn btn-danger">Deactivate</button>
		                                 </form>
		                              </tag:when>
		                              <tag:otherwise>
		                                 <form method="post" action="testType">
		                                    <input type="hidden" name="test_type_id" value="${testType.id}" required>
		                                    <input type="hidden" name="type" value="activate-specific"/>
		                                    <button type="submit" class="btn btn-success">Activate</button>
		                                 </form>
		                              </tag:otherwise>
		                           </tag:choose>
		                        </div>
		                        <div class="action-div">
		                           <form method="post" action="testType">
		                              <input type="hidden" name="test_type_id" value="${testType.id}" required>
		                              <input type="hidden" name="type" value="delete-specific"/>
		                              <button type="submit" class="btn btn-danger">Delete</button>
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
	</body>
</html>