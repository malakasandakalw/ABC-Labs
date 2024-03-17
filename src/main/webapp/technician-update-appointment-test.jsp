<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="ISO-8859-1">
      <title>Technician Appointment Test</title>
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
                     href="technicians?type=get-tests&session_id=${auth_technician_id}">Appointment Tests</a>
                  </li>
               </ul>
            </div>
         </div>
      </nav>
      <div class="container">
         <p>${message}</p>
         <div class="d-flex align-items-center mb-3">
            <div class="me-auto">
               <h3 class="title">Appointment Tests</h3>
            </div>
         </div>
         <hr>
         <div class="col-md-10 mx-auto bg-white p-5">
         <div class="row">
				    	<h4>Major Details</h4>
				    	<hr>
          	<div class="col-md-4">
          		<div class="mb-3">
				    <label class="form-label">Appointment Number : </label>
				    <input type="text" class="form-control" value="${appointmentTest.appointment.id}" required readonly>
			    </div>
          	</div>
          	<div class="col-md-4">
          		<div class="mb-3">
				    <label class="form-label">Test Number :</label>
				    <input type="text" class="form-control" value="${appointmentTest.id}" required readonly>
			    </div>
          	</div>
          	<div class="col-md-4">
          		<div class="mb-3">
				    <label class="form-label">Test : </label>
				    <input type="text" class="form-control" value="${appointmentTest.testType.name}" required readonly>
			    </div>
          	</div>
          	<div class="col-md-4">
          		<div class="mb-3">
				    <label class="form-label">Appointment Date : </label>
				    <input type="text" class="form-control" value="${appointmentTest.appointment.date}" required readonly>
			    </div>
          	</div>
          	<div class="col-md-4">
          		<div class="mb-3">
				    <label class="form-label">Appointment Status : </label>
				    <input type="text" class="form-control" value="${appointmentTest.appointment.status}" required readonly>
			    </div>
          	</div>
         </div>
	      		
			    <div class="row mt-5">
				    <div class="col-md-12">
				    	<h4>Patient Details</h4>
				    	<hr>
				    	<div class="row">
				    		<div class="col-md-4">
							    <div class="mb-3">
								    <label class="form-label">Name : </label>
								    <input type="text" class="form-control" value="${appointmentTest.patient.name}" required readonly>
							    </div>
				    		</div>
				    		<div class="col-md-4">
							    <div class="mb-3">
								    <label class="form-label">Contact Number :</label>
								    <input type="text" class="form-control" value="${appointmentTest.appointment.contactNumber}" required readonly>
							    </div>
				    		</div>
				    		<div class="col-md-4">
							    <div class="mb-3">
								    <label class="form-label">Age :</label>
								    <input type="text" class="form-control" value="${appointmentTest.patient.age}" required readonly>
							    </div>
				    		</div>
				    		<div class="col-md-4">
							    <div class="mb-3">
								    <label class="form-label">Gender :</label>
								    <input type="text" class="form-control" value="${appointmentTest.patient.gender}" required readonly>
							    </div>
				    		</div>
				    		<div class="col-md-12">
							    <div class="mb-3">
							    	<label class="form-label">Doctor Details :</label>
							    	<textarea class="form-control" required readonly>
							    		${appointmentTest.appointment.details}
							    	</textarea>
							    </div>				    		
				    		</div>
				    	</div>
				    </div>
				    <div class="col-md-12 mt-5">
				    	<h4>Test Details</h4>
				    	<hr>
				    
					<form action="technicians" method="post" enctype="multipart/form-data">
					    <div class="mb-3">
						    <label class="form-label">Test Status</label>
						    <select class="form-select" id="appointment_test_status" name="appointment_test_status" required>
						    	<option value="Cancel" ${appointmentTest.status eq 'Cancel' ? 'selected' : ''}>Cancel</option>
						    	<option value="Processing" ${appointmentTest.status eq 'Processing' ? 'selected' : ''}>Processing</option>
						    	<option value="Conducted" ${appointmentTest.status eq 'Conducted' ? 'selected' : ''}>Conducted</option>
						    </select>
					    </div>
				    	
						<div class="mb-3">
						    <label class="form-label">Already Uploaded File</label>
						    <div class="">
						    
						    <tag:if test="${not empty appointmentTest.getTestResult().getFileUrl()}">
					        	<a href="view?fileName=${ appointmentTest.getTestResult().getFileUrl()}">View Attached File</a>
					        </tag:if>

								<input type="hidden" name="appointment_test_result_file_url" value="${appointmentTest.testResult.fileUrl}" readonly/>
						    </div>
					    </div>
				    	
				    	<div class="mb-3">
						    <label class="form-label">Test Result</label>
						    <div class="">
		        				<input type="file" name="file" accept="image/png, image/gif, image/jpeg">
						    </div>
					    </div>
					    
					    
						<input type="hidden" name="appointment_test_id" value="${appointmentTest.id}"/>
						<input type="hidden" name="appointment_technician_id" value="${auth_technician_id}"/>
						
						
						<input type="hidden" name="patient_contact_number" value="${appointmentTest.patient.contactNumber}"/>
						
						
						<input type="hidden" name="appointment_test_result_id" value="${appointmentTest.testResult.id}"/>
						
						<input type="hidden" name="type" value="update-specific-appointment-test"/>
				        <button type="submit" class="btn btn-primary">Update</button>
				    </form>
				    
			    </div>
	      </div>
      </div>
   </body>
</html>