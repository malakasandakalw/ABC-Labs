package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.dao.CommonManager;
import com.code_with_malaka.ABC_lab_system.dao.PasswordManager;
import com.code_with_malaka.ABC_lab_system.models.Patient;
import com.code_with_malaka.ABC_lab_system.services.AppointmentServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.PatientServiceImpl;

public class PatientsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PatientsController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		System.out.println("here get");
		System.out.println(type);
		
		if(type != null && type.equals("login")) {
			login(request, response, "");
		}else if(type != null && type.equals("get-appointments")) {
			System.out.println("here get 2");
			getPatientAppointments(request, response, "");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null && type.equals("create")) {
    		try {
				createPatient(request, response, "");
			} catch (ClassNotFoundException | ServletException | IOException | SQLException | ParseException e) {
				e.printStackTrace();
			}
    	}
	}

	private boolean passswordMatcher(String passsword, String confirmPassword) {
		if (passsword.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void createPatient(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException{
		
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		
		boolean isPasswordsMatch = passswordMatcher(password, confirmPassword);
		
		if (isPasswordsMatch) {
			
			CommonManager commonManager = new CommonManager();
			Patient patient = new Patient(request.getParameter("email"));
		 	Boolean isUserExists =  commonManager.checkIfUserExists(patient);
			
		 	if(isUserExists) {
				request.setAttribute("message", "Email already exists.");	
				RequestDispatcher rd = request.getRequestDispatcher("patient-signup.jsp");
				rd.forward(request, response);		 		
		 	} else {
				
		 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 		PasswordManager passwordManager = new PasswordManager();
		 		PatientServiceImpl patientService = new PatientServiceImpl();
		 		
		 		patient.setName(request.getParameter("name"));
		 		patient.setEmail(request.getParameter("email"));
		 		patient.setContactNumber(request.getParameter("contact_number"));
		 		patient.setDob(dateFormat.parse(request.getParameter("dob")));
		 		patient.setPassword(passwordManager.passwordHash(password));
		 		
		 		boolean result;
		 		
		 		try {
					result = patientService.createPatient(patient);
					if(result) {
						message = "Successfully registered";
						request.setAttribute("message", message);
					} else {
						message = "Registration failed";
					}
				} catch (ClassNotFoundException | SQLException e) {
					message = e.getMessage();
				}
		 		
				request.setAttribute("message", message);
				
				RequestDispatcher rd = request.getRequestDispatcher("patient-signup.jsp");
				rd.forward(request, response);
			}
		 	
			
		} else {
			request.setAttribute("message", "Password confirmation mismatch. Try again");	
			RequestDispatcher rd = request.getRequestDispatcher("patient-signup.jsp");
			rd.forward(request, response);
		}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
 		PasswordManager passwordManager = new PasswordManager();
 		PatientServiceImpl patientService = new PatientServiceImpl();
 		Patient patient = new Patient();
 		
		try {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			patient.setEmail(email);
			patient.setPassword(password);
			patient.setRole("Patient");
			
			patient = patientService.getSpecificPatient(patient);
			
			if(patient.getId() > -1) {
				if (patient.getPassword().equals(password)) {
					request.setAttribute("patient", patient);

					CommonManager commonManager = new CommonManager();
					commonManager.login(request, patient);
					
//			    	RequestDispatcher rd = request.getRequestDispatcher("patient-appointments?type='get-appointments'&session_id=" + request.getSession().getAttribute("auth_patient_id"));
					RequestDispatcher rd = request.getRequestDispatcher("patients?type=get-appointments&session_id=" + request.getSession().getAttribute("auth_patient_id"));
			    	rd.forward(request, response);					
				} else {
					request.setAttribute("message", "Invalid Credentials");
					
			    	RequestDispatcher rd = request.getRequestDispatcher("patient-login.jsp");
			    	rd.forward(request, response);					
				}
			} else {
				request.setAttribute("message", "User Does Not Exists.");
				
		    	RequestDispatcher rd = request.getRequestDispatcher("patient-login.jsp");
		    	rd.forward(request, response);					
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
 		
	}
	
	private void getPatientAppointments(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		System.out.println("here");
 		AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
// 		RequestDispatcher rd = request.getRequestDispatcher("patient-appointments.jsp");
//    	rd.forward(request, response);
 		HttpSession session = request.getSession();
 		session.setAttribute("message", "test here");
 		response.sendRedirect("patient-appointments.jsp");
	}
	
}
