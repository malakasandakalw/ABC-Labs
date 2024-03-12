package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.Technician;
import com.code_with_malaka.ABC_lab_system.services.AppointmentTestServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.TechnicianServiceImpl;

/**
 * Servlet implementation class AppointmentTests
 */
public class AppointmentTests extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AppointmentTests() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type != null && type.equals("get-specific")) {
//    		getSpecificAppointmentTest(request, response, "");
		}else if (type != null && type.equals("get-specific-by-appointment")) {
			getSpecificAppointmentTestByAppointmentId(request, response, "");
		} else if (type != null && type.equals("new-appointment")) {
//			getTestTypesForCreateAppointment(request, response, "");
		}else if(type != null && type.equals("update-specific")) {
//			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
//    		getSpecificTechnicianForUpdate(request, response, technicianService, testTypeService, "");
    	} else {
//			getAllAppointments(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type != null && type.equals("update")) {
			updateSpecificAppointmentTest(request, response, "");
		}
	}
	
	private void updateSpecificAppointmentTest(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		AppointmentTestServiceImpl appointmentTestService = (AppointmentTestServiceImpl) AppointmentTestServiceImpl.getAppointmentTestServiceInstance();
		
		int id = Integer.parseInt(request.getParameter("appointment_test_id"));
		Technician technician = new Technician(Integer.parseInt(request.getParameter("appointment_test_technicians")));
		String status = request.getParameter("appointment_test_status"); 
		
		AppointmentTest appointmentTest = new AppointmentTest(id, technician, status);
		boolean result;
		
		try {
			result = appointmentTestService.updateAppointmentTest(appointmentTest);
			if(result) {
				message = "Successfully Updated!";
			}
			else {
				message = "Failed!";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("message", message);
		getSpecificAppointmentTestByAppointmentId(request, response, message);
		
	}
	
	private void getSpecificAppointmentTestByAppointmentId(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		AppointmentTestServiceImpl appointmentTestsService = (AppointmentTestServiceImpl) AppointmentTestServiceImpl.getAppointmentTestServiceInstance();
		TechnicianServiceImpl technicianService = (TechnicianServiceImpl) TechnicianServiceImpl.getTechnicianServiceInstance();
		
		AppointmentTest appointmentTest;
		List<Technician> techniciansList;
		
		int testTypeId = Integer.parseInt(request.getParameter("test_type_id"));
		int testAppointmentId = Integer.parseInt(request.getParameter("test_appointment_id"));
		
		try {
			appointmentTest = appointmentTestsService.getSpecificAppointmentTestByAppointmentId(testTypeId, testAppointmentId);
			
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			appointmentTest = new AppointmentTest();
		}
		
		
		try {
			techniciansList = technicianService.getTechniciansByTestTypeId(testTypeId);
		} catch (Exception e) {
			message = e.getMessage();
			techniciansList = new ArrayList<Technician>();			
		}
		
		request.setAttribute("message", message);
		request.setAttribute("appointmentTest", appointmentTest);
		request.setAttribute("TestTypeTechnicians", techniciansList);
		
		
    	RequestDispatcher rd = request.getRequestDispatcher("receptionist-view-appointment-test.jsp");
    	rd.forward(request, response);
		
	}

}
