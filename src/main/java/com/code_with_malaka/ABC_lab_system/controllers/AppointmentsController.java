package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.models.Appointment;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.TestType;
import com.code_with_malaka.ABC_lab_system.services.AppointmentServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.TestTypeServiceImpl;

public class AppointmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AppointmentsController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
		} else if (type != null && type.equals("new-appointment")) {
			getTestTypesForCreateAppointment(request, response, "");
		}else if(type != null && type.equals("update-specific")) {
//			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
//    		getSpecificTechnicianForUpdate(request, response, technicianService, testTypeService, "");
    	} else {
//			getAllTechnicians(request, response, technicianService);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null && type.equals("create")) {
    		createAppointment(request, response);
    	}
    	else if(type != null && type.equals("update")) {
//    		updateTechnician(request, response, technicianService);
    	}
    	else if(type != null && type.equals("delete-specific")) {
//        	deleteTestType(request, response, testTypeService);
    	}
		
	}
	
	private void getTestTypesForCreateAppointment(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
		
		List<TestType> testTypesList;

		try {
			testTypesList = testTypeService.getAllTestTypes();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			testTypesList = new ArrayList<TestType>();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("testTypesList", testTypesList);		
		session.setAttribute("message", message);
		response.sendRedirect("patient-create-appointment.jsp");
	
	}

	private void createAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AppointmentServiceImpl appointmentService = (AppointmentServiceImpl) AppointmentServiceImpl.getAppointmentServiceInstance();
		
		String details = request.getParameter("details");
		String email = request.getParameter("email");
		String contactNumber = request.getParameter("contact_number");
		
		String[] testTypes = request.getParameterValues("tests");
		
		if (testTypes != null) {
			
			List<AppointmentTest> appointmentTests = new ArrayList<AppointmentTest>();
			
			for (String testType : testTypes) {
				TestType tt = new TestType(Integer.parseInt(testType));
				AppointmentTest at = new AppointmentTest();
				at.setTestType(tt);
				appointmentTests.add(at);
			}
			
			Appointment appointment = new Appointment();
			appointment.setDetails(details);
			appointment.setEmail(email);
			appointment.setContactNumber(contactNumber);
			appointment.setAppointmentTests(appointmentTests);
			
	    	boolean result;
	    	String message = "";
	    	
	    	try {
	    		
	    		result = appointmentService.createAppointment(appointment);
				if(result) {
					message = "Successfully Created!";
				}
				else {
					message = "Failed!";
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
	    	
			request.setAttribute("message", message);
			
			getTestTypesForCreateAppointment(request, response, message);
			
		} else {
			
			getTestTypesForCreateAppointment(request, response, "Select Tests You Want To do!");
			
		}
	}
	
}
