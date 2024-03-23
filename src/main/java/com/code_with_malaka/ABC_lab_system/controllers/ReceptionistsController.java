package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.dao.CommonManager;
import com.code_with_malaka.ABC_lab_system.dao.PasswordManager;
import com.code_with_malaka.ABC_lab_system.models.Appointment;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.Message;
import com.code_with_malaka.ABC_lab_system.models.Receptionist;
import com.code_with_malaka.ABC_lab_system.models.Technician;
import com.code_with_malaka.ABC_lab_system.services.AppointmentServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.AppointmentTestServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.MessageService;
import com.code_with_malaka.ABC_lab_system.services.ReceptionistServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.TechnicianServiceImpl;


public class ReceptionistsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceptionistsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		boolean isAuthenticated = isAuthenticated(session);		
		
		String type = request.getParameter("type");
		ReceptionistServiceImpl receptionistService = (ReceptionistServiceImpl) ReceptionistServiceImpl.getReceptionistServiceInstance();
		
    	if(type != null && type.equals("login")) {
    		login(request, response, "");
    	} else if (type != null && type.equals("get-appointments")) {
    		
    		if(!isAuthenticated) {
    			logout(request, response, "");
    		} else {
    			try {
    				getAppointments(request, response, "");
    			} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
    				e.printStackTrace();
    			}
			}
		} else if (type != null && type.equals("get-specific-appointment")) {
			
			if(!isAuthenticated) {
				logout(request, response, "");
			} else {
	    		getSpecificAppointment(request, response, "");
			}
			
		} else if (type != null && type.equals("get-specific-test-by-appointment")){
			if(!isAuthenticated) {
				logout(request, response, "");
			} else {
				getSpecificAppointmentTestByAppointmentId(request, response, "");				
			}
			
		} else if (type != null && type.equals("update-specific")) {
			getReceptionistForUpdate(request, response, receptionistService,"");
		}
    	else {
        	getAllReceptionists(request, response, receptionistService);
    	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		
		boolean isAuthenticated = isAuthenticated(session);	
		
		if(type != null && type.equals("update-appointment")) {
			
			if(!isAuthenticated) {
				logout(request, response, "");
			} else {
	    		try {
					updateAppointment(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			
    	}else if (type != null && type.equals("update-appointment-test")) {
    		if(!isAuthenticated) {
				logout(request, response, "");    			
    		} else {
    			updateSpecificAppointmentTest(request, response, "");
			}
			
		} else if (type != null && type.equals("create-receptionist")) {
			try {
				createReceptionist(request, response, "");
			} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException | ServletException
					| IOException e) {
				e.printStackTrace();
			}
		} else if (type != null && type.equals("update-receptionist")) {
			try {
				updateReceptionist(request, response, "");
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if (type != null && type.equals("logout")) {
			logout(request, response, "");
		}
	}
	
	private boolean isAuthenticated(HttpSession session) {
		if (session.getAttribute("auth_receptionist_id") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		Receptionist receptionist = new Receptionist();
		receptionist.setRole("Receptionist");
		CommonManager commonManager = new CommonManager();
		commonManager.logout(request, receptionist);
		request.setAttribute(message, "Logged out successfully!");
    	response.sendRedirect("receptionist-login.jsp");	
	}
	
	public static PasswordManager getPasswordManager() {
		PasswordManager passwordManager = PasswordManager.getPasswordManagerInstance();
		return passwordManager;
	}
	
	
	private void login(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		Receptionist receptionist = new Receptionist();
		ReceptionistServiceImpl receptionistService = new ReceptionistServiceImpl();
		PasswordManager passwordManager = getPasswordManager();
		
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			receptionist = receptionistService.getSpecifcReceptionistByEmail(email);
			
			if(receptionist.getId() > -1) {
				if (passwordManager.verify(password, receptionist.getPassword())) {
					request.setAttribute("receptionist", receptionist);
					
					CommonManager commonManager = new CommonManager();
					commonManager.login(request, receptionist);
					response.sendRedirect("receptionists?type=get-appointments&session_id=" + request.getSession().getAttribute("auth_receptionist_id"));
				} else {
					request.setAttribute("message", "Invalid Credentials");
					
			    	RequestDispatcher rd = request.getRequestDispatcher("receptionist-login.jsp");
			    	rd.forward(request, response);					
				}
			} else {
				request.setAttribute("message", "User Does Not Exists.");
				
		    	RequestDispatcher rd = request.getRequestDispatcher("receptionist-login.jsp");
		    	rd.forward(request, response);					
			}
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		
	}
	
	private void createReceptionist(HttpServletRequest request, HttpServletResponse response, String message) throws ClassNotFoundException, SQLException, ServletException, IOException, NoSuchAlgorithmException {
		CommonManager commonManager = new CommonManager();
		Receptionist receptionist = new Receptionist(request.getParameter("email"));
		boolean isUserExists = commonManager.checkIfUserExists(receptionist);
		
		if(isUserExists) {
			request.setAttribute("message", "Receptionist already exists.");	
			RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-receptionist.jsp");
			rd.forward(request, response);			
		} else {
	 		PasswordManager passwordManager = getPasswordManager();
			ReceptionistServiceImpl receptionistService = new ReceptionistServiceImpl();
			
			receptionist.setName(request.getParameter("name"));
			receptionist.setEmail(request.getParameter("email"));
			receptionist.setPassword(passwordManager.passwordHash(request.getParameter("password")));
			
			boolean result;
			
			try {
				result = receptionistService.createReceptionist(receptionist);
				if(result) {
					message = "Successfully Created";
					request.setAttribute("message", message);
				} else {
					message = "Registration failed";
				}
			} catch (Exception e) {
				message = e.getMessage();
			}
			
			request.setAttribute("message", message);
			
			RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-receptionist.jsp");
			rd.forward(request, response);
			
		}
		
	}
	
	private void updateReceptionist(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException, ClassNotFoundException, SQLException {
		int id = Integer.parseInt(request.getParameter("receptionist_id"));
		String name = request.getParameter("receptionist_name");
		String email = request.getParameter("receptionist_email");
		String password = request.getParameter("receptionist_password");
		int isActive = Integer.parseInt(request.getParameter("receptionist_is_active"));	
		
		Receptionist receptionist = new Receptionist();
		receptionist.setId(id);
		receptionist.setEmail(email);
		receptionist.setName(name);
		receptionist.setPassword(password);
		receptionist.setIsActive(isActive);
		
    	boolean result;
    	message = "";
		
    	ReceptionistServiceImpl receptionistService = new ReceptionistServiceImpl();
    	result = receptionistService.updateReceptionist(receptionist);
		if(result) {
			message = "Successfully Updated!";
		}
		else {
			message = "Failed!";
		}
		
		getReceptionistForUpdate(request, response, receptionistService, message);
    	
	}
	
	private void getAllReceptionists(HttpServletRequest request, HttpServletResponse response, ReceptionistServiceImpl service) throws ServletException, IOException {
		
		String message = "";
		List<Receptionist> receptionistsList;
		
		try {
			receptionistsList = service.getAllReceptionists();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			receptionistsList = new ArrayList<Receptionist>();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("receptionistsList", receptionistsList);
		
    	RequestDispatcher rd = request.getRequestDispatcher("manager-receptionists.jsp");
    	rd.forward(request, response);
	}
	
	private void getAppointments(HttpServletRequest request, HttpServletResponse response, String message) throws ClassNotFoundException, SQLException, ServletException, IOException {
		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
		List<Appointment> appointmentsList = appointmentService.getAllAppointments();
		request.setAttribute("appointmentsList", appointmentsList);
    	RequestDispatcher rd = request.getRequestDispatcher("receptionist-all-appointments.jsp");
    	rd.forward(request, response);
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
				Timestamp timestamp = new Timestamp(date.getTime());
				Message messageObj = new Message(contactNumber, "Your appointment number " + id + " is updated as " + status + ". And your appointment scheduled on " + timestamp);
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
	
	private void getSpecificAppointmentTestByAppointmentId(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		AppointmentTestServiceImpl appointmentTestsService = (AppointmentTestServiceImpl) AppointmentTestServiceImpl.getAppointmentTestServiceInstance();
		TechnicianServiceImpl technicianService = (TechnicianServiceImpl) TechnicianServiceImpl.getTechnicianServiceInstance();
		
		AppointmentTest appointmentTest;
		List<Technician> techniciansList;
		
		int testTypeId = Integer.parseInt(request.getParameter("test_type_id"));
		int appointmentTestId = Integer.parseInt(request.getParameter("appointment_test_id"));
		
		try {
			appointmentTest = appointmentTestsService.getSpecificAppointmentTest(appointmentTestId);
			
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
	
	private void getReceptionistForUpdate(HttpServletRequest request, HttpServletResponse response, ReceptionistServiceImpl service,String message) throws ServletException, IOException {
		Receptionist receptionist = new Receptionist();
		try {
			int id = Integer.parseInt(request.getParameter("receptionist_id"));
			receptionist = service.getSpecificReceptionist(id);
		} catch (Exception e) {
			message = e.getMessage();
			receptionist = new Receptionist();
		}
		request.setAttribute("message", message);
		request.setAttribute("receptionist", receptionist);
    	RequestDispatcher rd = request.getRequestDispatcher("manager-update-receptionist.jsp");
    	rd.forward(request, response);
	}

}
