package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.dao.CommonManager;
import com.code_with_malaka.ABC_lab_system.models.Appointment;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.Message;
import com.code_with_malaka.ABC_lab_system.models.Patient;
import com.code_with_malaka.ABC_lab_system.models.PaymentRecipt;
import com.code_with_malaka.ABC_lab_system.models.TestType;
import com.code_with_malaka.ABC_lab_system.models.User;
import com.code_with_malaka.ABC_lab_system.services.AppointmentServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.AppointmentTestServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.MessageService;
import com.code_with_malaka.ABC_lab_system.services.PaymentReciptServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.TestTypeServiceImpl;

public class AppointmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AppointmentsController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type != null && type.equals("get-specific")) {
    		getSpecificAppointment(request, response, "");
		} else if (type != null && type.equals("new-appointment")) {
			getTestTypesForCreateAppointment(request, response, "");
		}else if(type != null && type.equals("get-by-patient")) {
//			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
//    		getSpecificTechnicianForUpdate(request, response, technicianService, testTypeService, "");
    	} else {
			getAllAppointments(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null && type.equals("create")) {
    		createAppointment(request, response);
    	}
    	else if(type != null && type.equals("update")) {
    		try {
				updateAppointment(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	else if(type != null && type.equals("payment")) {
        	try {
				payment(request, response, "");
			} catch (NumberFormatException | ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
    	}
		
	}
	
	private void getAllAppointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentServiceImpl appointmentService = (AppointmentServiceImpl) AppointmentServiceImpl.getAppointmentServiceInstance();
		String message = "";
		List<Appointment> appointmentsList;
		
		try {
			appointmentsList = appointmentService.getAllAppointments();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			appointmentsList = new ArrayList<Appointment>();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("appointmentsList", appointmentsList);
		
    	RequestDispatcher rd = request.getRequestDispatcher("receptionist-all-appointments.jsp");
    	rd.forward(request, response);
	}
	
	private void getSpecificAppointment(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		AppointmentServiceImpl appointmentService = (AppointmentServiceImpl) AppointmentServiceImpl.getAppointmentServiceInstance();
		AppointmentTestServiceImpl appointmentTestsService = (AppointmentTestServiceImpl) AppointmentTestServiceImpl.getAppointmentTestServiceInstance();
	
		Appointment appointment;
		
		try {
			int id = Integer.parseInt(request.getParameter("appointment_id"));
			appointment = appointmentService.getSpecificAppointment(id);
			List<AppointmentTest> appointmentTests = appointmentTestsService.getAppointmentTestsByAppointmentId(id);
			appointment.setAppointmentTests(appointmentTests);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			appointment = new Appointment();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("appointment", appointment);
		
    	RequestDispatcher rd = request.getRequestDispatcher("receptionist-view-appointment.jsp");
    	rd.forward(request, response);
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
		
		String sesssionUserType = request.getParameter("session_id_type");
		String sessionUserId = request.getParameter("session_id");
		
		User user = null;
		
		if(sesssionUserType.equals("patient")) {
			user = new Patient();
			user.setId(Integer.parseInt(sessionUserId));
			user.setRole("Patient");
		}
		
		CommonManager commonManager = new CommonManager();
		commonManager.login(request, user);
		
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
	    		System.out.println(request.getParameter("session_id"));
	    		result = appointmentService.createAppointment(appointment, Integer.parseInt(request.getParameter("session_id")));
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
	
	private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		AppointmentServiceImpl appointmentService = (AppointmentServiceImpl) AppointmentServiceImpl.getAppointmentServiceInstance();
		MessageService messageService = MessageService.getMessagerServiceInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		int id = Integer.parseInt(request.getParameter("appointment_id"));
		Date date = dateFormat.parse(request.getParameter("appointment_date"));
		String status = request.getParameter("appointment_status");
		Double price = Double.parseDouble(request.getParameter("appointment_price"));
		String contactNumber = request.getParameter("appointment_contact_number");
		
		Appointment appointment = new Appointment(id, date, status, price);
		
		boolean result;
		
		String message = "";
		
		try {
			result = appointmentService.updateAppointment(appointment);
			if(result) {
				message = "Successfully Updated!";
				
				Message messageObj = new Message(contactNumber, "Your appointment number " + id + " is" + status);
				boolean isMessageSent = messageService.sendMessage(messageObj);
				if(isMessageSent) {
					message = "Successfully Updated! Message Sent";
				}
			}
			else {
				message = "Failed!";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("message", message);
		
		getSpecificAppointment(request,response, message);
		
		
	}
	
	private void payment(HttpServletRequest request, HttpServletResponse response, String message) throws NumberFormatException, ClassNotFoundException, IOException, SQLException {
		AppointmentServiceImpl appointmentService = (AppointmentServiceImpl) AppointmentServiceImpl.getAppointmentServiceInstance();
		MessageService messageService = MessageService.getMessagerServiceInstance();
		PaymentReciptServiceImpl paymentReciptService = PaymentReciptServiceImpl.getPaymentReciptServiceInstance();
		
		int id = Integer.parseInt(request.getParameter("appointment_id"));
		String status = "Payment Done";
		String contactNumber = request.getParameter("appointment_contact_number");
		Appointment appointment = new Appointment(id, status);
		PaymentRecipt paymentRecipt = new PaymentRecipt();
		paymentRecipt.setTotalPrice(Double.parseDouble(request.getParameter("appointment_price")));
		
		boolean result;
		
		try {
			result = appointmentService.updateAppointmentStatus(appointment);
			if(result) {
				paymentReciptService.createPaymentRecipt(paymentRecipt, id);
				message = "Paid Successfully!";
				Message messageObj = new Message(contactNumber, "Your payment for appointment number " + id + " is done.");
				boolean isMessageSent = messageService.sendMessage(messageObj);
				if(isMessageSent) {
					message = "Successfully Updated! Message Sent";
				}
			}
			else {
				message = "Failed!";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}

		request.setAttribute("message", message);
		
		
	}
	
}
