<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
   Object sessionAttribute = session.getAttribute("auth_patient_id");
   
   if (sessionAttribute != null) {
   	%>
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
                     href="patients?type=get-appointments&session_id=${auth_patient_id}">Appointments</a>
                  </li>
                  <li class="nav-item"><a class="nav-link"
                     href="patients?type=get-specific-patient-for-update&session_id=${auth_patient_id}">Update Profile</a>
                  </li>
                  <li class="nav-item">
                  	<form method="post" action="patients">
                        <input type="hidden" name="auth_patient_id" value="${auth_patient_id}" required>
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
         <div class="col-md-10 mx-auto bg-white p-5">
		    	<h4>Appointment Details</h4>
			    	<hr>
	      		<div class="mb-3">
				    <label class="form-label">Appointment Number : </label>
				    <input type="text" value="${appointment.id}" class="form-control" readonly>
			    </div>
			    <div class="row">
			    	<div class="col-md-6">
					    <div class="mb-3">
						    <div class="form-label">Contact Number : </div>
				    		<input type="text" value="${appointment.contactNumber}" class="form-control" readonly>
					    </div>
			    	</div>
			    	<div class="col-md-6">
					    <div class="mb-3">
						    <div class="form-label">Email : </div>
				    		<input type="text" value="${appointment.email}" class="form-control" readonly>
					    </div>
			    	</div>
			    </div>
			    <div class="mb-3">
				    <div class="form-label">Doctor Details :</div>
				    <textarea rows="" cols="" class="form-control" readonly>${appointment.details}</textarea>
			    </div>
			    
			    <div class="row">
				    <div class="col-md-4">
					    <div class="mb-3">
						    <div class="form-label">Appointment Date :</div>
						    <input type="text" value="${appointment.date}" class="form-control" readonly>
					    </div>
				    </div>
				    <div class="col-md-4">
					    <div class="mb-3">
						    <div class="form-label">Appointment Status : </div>
						    <input type="text" value="${appointment.status}" class="form-control" readonly>
					    </div>
				    </div>
				    <div class="col-md-4">
					    <div class="mb-3">
						    <div class="form-label">Total Price (Rs.) : </div>
						    <input type="text" value="${appointment.totalPrice}" class="form-control" readonly>
					    </div>
				    </div>
				    <div class="col-md-12">
					    <div class="mb-3">
						    <div class="form-label">Created at: </div>
						    <input type="text" value="${appointment.createdAt}" class="form-control" readonly>
					    </div>
				    </div>
			    </div>
			    
			    <tag:if test='${appointment.status eq "Payment Done" || appointmentTest.status eq "Done"}}'>
			    	<form method="get" action="appointments-payment-recipt">
				    	<input type="hidden" class="form-control" id="appointment_id" name="appointment_id" value="${appointment.id}" required>
				        <input type="hidden" value="download" name="type" required>
				        <button type="submit" class="btn btn-primary">Download Payment Receipt</button>
				    </form>
			    </tag:if>
			    
			    <tag:if test='${appointment.status eq "Confirmed, Waiting for payment"}'>
				    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#paymentModal">
					  Pay Now!
					</button>
					<div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Pay Online</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      
					    <form method="post" action="patients">
					      <div class="modal-body">
					      	<div class="mb-3">
							    <label class="form-label">Card Number</label>
							    <input type="number" class="form-control" required>
						    </div>
					      	<div class="mb-3">
							    <label class="form-label">Card Holder Name</label>
							    <input type="text" class="form-control" required>
						    </div>
					      	<div class="mb-3">
							    <label class="form-label">CVC</label>
							    <input type="password" class="form-control" required>
						    </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						    	<input type="hidden" class="form-control" id="appointment_id" name="appointment_id" value="${appointment.id}" required>
					   			<input type="hidden" class="form-control" value="${appointment.contactNumber}" name="appointment_contact_number" id="appointment_contact_number" required>
					   			<input type="hidden" class="form-control" id="appointment_price" name="appointment_price" value="${appointment.totalPrice}" required>
						        <input type="hidden" value="payment" name="type" required>
						        <button type="submit" class="btn btn-primary">Pay</button>
						    
					      </div>
					      </form>
					    </div>
					  </div>
					</div>
				</tag:if>
				
				<div class="d-block mt-5">
			    	<h4>Payment Details</h4>
			    	<hr>
			    	<table class="table table-stripped">
			    		<thead>
			               <tr>
			                  <th>Reference Number</th>
			                  <th>Amount</th>
			                  <th>Paid At</th>
			               </tr>
			            </thead>
			            <tbody>
			               <tr>
			                  <td>${appointment.paymentRecipt.id}</td>
			                  <td>${appointment.paymentRecipt.totalPrice}</td>
			                  <td>${appointment.paymentRecipt.createdAt}</td>
			               </tr>
			            </tbody>
			    	</table>
				</div>
				
	      	<div class="mb-3 mt-5">
				    	<h4>Tests</h4>
				    	<hr>
				    
				    <table class="table table-stripped">
				    	<thead>
			               <tr>
			                  <th>Test</th>
			                  <th>Status</th>
			                  <th>Price</th>
			                  <th>Actions</th>
			               </tr>
			            </thead>
	            		<tbody>
						    <tag:forEach var="appointmentTest" items="${appointment.appointmentTests}">
						    	<tr>
						    		<td>${appointmentTest.testType.name}</td>
						    		<td>${appointmentTest.status}</td>
						    		<td>${appointmentTest.testType.price}</td>
						    		<td>
										<tag:if test='${appointmentTest.status eq "Conducted"}'>
										    <form method="get" action="appointmentTest">
										        <input type="hidden" value="${appointmentTest.testType.id}" id="test_type_id" name="test_type_id" required>
										        <input type="hidden" value="${appointment.id}" id="test_appointment_id" name="test_appointment_id" required>
										        <input type="hidden" value="get-specific-by-appointment" name="type" required>
										        <a href="view?fileName=${ appointmentTest.getTestResult().getFileUrl()}">View Result</a>
										    </form>
										</tag:if>
						    		</td>
						    	</tr>
						    </tag:forEach>
	            		</tbody>
				    </table>
				    
			    </div>
	      </div>
      </div>
   </body>
</html>
<%
   } else {
      response.sendRedirect("patient-login.jsp");
      }
   %>