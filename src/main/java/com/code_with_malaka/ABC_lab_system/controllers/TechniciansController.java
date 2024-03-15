package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.code_with_malaka.ABC_lab_system.dao.CommonManager;
import com.code_with_malaka.ABC_lab_system.dao.PasswordManager;
import com.code_with_malaka.ABC_lab_system.dao.TestResultManager;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.CreateResponse;
import com.code_with_malaka.ABC_lab_system.models.FileUploadResponse;
import com.code_with_malaka.ABC_lab_system.models.Technician;
import com.code_with_malaka.ABC_lab_system.models.TestResult;
import com.code_with_malaka.ABC_lab_system.models.TestType;
import com.code_with_malaka.ABC_lab_system.services.AppointmentTestServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.CommonServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.TechnicianServiceImpl;
import com.code_with_malaka.ABC_lab_system.services.TestTypeServiceImpl;

@MultipartConfig(
		location = "C:\\Uploads",
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 11
		)

public class TechniciansController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TechniciansController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		TechnicianServiceImpl technicianService = (TechnicianServiceImpl) TechnicianServiceImpl.getTechnicianServiceInstance();

		if (type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
		}else if(type != null && type.equals("get-tests")) {
			try {
				getTechnicianAppointmentTests(request, response, "");
			} catch (NumberFormatException | ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		} else if (type != null && type.equals("new-technician")) {
			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
			getTestTypesForCreateTechnician(request, response, testTypeService, "");
		}else if(type != null && type.equals("update-specific")) {
			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
    		getSpecificTechnicianForUpdate(request, response, technicianService, testTypeService, "");
    	} else if(type != null && type.equals("login")) {
			login(request, response, "");
		} else if(type != null && type.equals("get-specific-appointment-test")) {
			try {
				getSpecificTechnicianAppointmentTest(request, response, "");
			} catch (NumberFormatException | ClassNotFoundException | SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else{
			getAllTechnicians(request, response, technicianService);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		TechnicianServiceImpl technicianService = (TechnicianServiceImpl) TechnicianServiceImpl.getTechnicianServiceInstance();
		CommonServiceImpl commonServiceImpl = (CommonServiceImpl) CommonServiceImpl.getCommonServiceInstance();
		if(type != null && type.equals("create")) {
    		createTechnician(request, response, technicianService, commonServiceImpl);
    	}
    	else if(type != null && type.equals("update")) {
    		updateTechnician(request, response, technicianService);
    	}
    	else if(type != null && type.equals("update-specific-appointment-test")) {
        	try {
				updateSpecificAppointmentTest(request, response, "");
			} catch (ClassNotFoundException | IOException | ServletException | SQLException e) {
				e.printStackTrace();
			}
    	}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		PasswordManager passwordManager = new PasswordManager();
		TechnicianServiceImpl technicianService = new TechnicianServiceImpl();
		Technician technician = new Technician();
		
		try {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			technician.setEmail(email);
			
			technician = technicianService.getSpecificTechnicianByEmail(email);
			
			if(technician.getId() > -1) {
				if (technician.getPassword().equals(password)) {
					request.setAttribute("technician", technician);
					
					CommonManager commonManager = new CommonManager();
					commonManager.login(request, technician);
					response.sendRedirect("technicians?type=get-tests&session_id=" + request.getSession().getAttribute("auth_technician_id"));
				} else {
					request.setAttribute("message", "Invalid Credentials");
					
			    	RequestDispatcher rd = request.getRequestDispatcher("technician-login.jsp");
			    	rd.forward(request, response);					
				}
			} else {
				request.setAttribute("message", "User Does Not Exists.");
				
		    	RequestDispatcher rd = request.getRequestDispatcher("technician-login.jsp");
		    	rd.forward(request, response);					
			}
			
		} catch (Exception e) {
			message = e.getMessage();			
		}
		
	}
	
	private void getTechnicianAppointmentTests(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException, NumberFormatException, ClassNotFoundException, SQLException {
 		AppointmentTestServiceImpl appointmentTestsService = new AppointmentTestServiceImpl();
 		String technicianIdString = request.getSession().getAttribute("auth_technician_id").toString();
 		System.out.println(technicianIdString);
 		List<AppointmentTest> technicianAppointmentTests = appointmentTestsService.getAppointmentTestsByTechnicianId(Integer.parseInt(technicianIdString));
 		HttpSession session = request.getSession();
 		session.setAttribute("technicianAppointmentTests", technicianAppointmentTests);
    	RequestDispatcher rd = request.getRequestDispatcher("technician-all-tests.jsp");
    	rd.forward(request, response);
	}
	
	private void getSpecificTechnicianAppointmentTest(HttpServletRequest request, HttpServletResponse response, String message) throws NumberFormatException, ClassNotFoundException, SQLException, ServletException, IOException {
		AppointmentTestServiceImpl appointmentTestsService = new AppointmentTestServiceImpl();
		String appoitmentTestId = request.getParameter("appointment_test_id");
		AppointmentTest appointmentTest = appointmentTestsService.getSpecificAppointmentTest(Integer.parseInt(appoitmentTestId));
		HttpSession session = request.getSession();
		session.setAttribute("appointmentTest", appointmentTest);
		session.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("technician-update-appointment-test.jsp");
    	rd.forward(request, response);
	}
	
	private void updateSpecificAppointmentTest(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException, ClassNotFoundException, SQLException {
		TestResultManager testResultManager = new TestResultManager();
		AppointmentTestServiceImpl appointmentTestsService = new AppointmentTestServiceImpl();
		
		Part part = request.getPart("file");
		String cd = part.getHeader("content-disposition");

		AppointmentTest appointmentTest = new AppointmentTest();
		appointmentTest.setId(Integer.parseInt(request.getParameter("appointment_test_id")));
		appointmentTest.setStatus(request.getParameter("appointment_test_status"));
		
		if (!cd.contains("filename=")) {
			appointmentTestsService.updateAppointmentTest(appointmentTest);
		} else {
			
			FileUploadResponse isFileUpoloadedResponse = testResultManager.uploadFile(request);
			
			if (isFileUpoloadedResponse.isUploaded()) {
				
				TestResult testResult = new TestResult(appointmentTest, isFileUpoloadedResponse.getTestResult().getFileUrl());
				
				CreateResponse resultCreatedResponse = testResultManager.createTestResult(testResult);
				testResult.setId(resultCreatedResponse.getId());
				
				appointmentTest.setTestResult(testResult);
				appointmentTest.setTechnician(new Technician(Integer.parseInt(request.getParameter("appointment_technician_id"))));
				
				appointmentTestsService.updateSpecificAppointmentTestByTechnician(appointmentTest);
				
				HttpSession session = request.getSession();
				getSpecificTechnicianAppointmentTest(request, response, "Updated Successfully!");
				
				return;
				
			} else {
				message = "File Upload Failed!";
			}
			
		}
		
		getSpecificTechnicianAppointmentTest(request, response, "Failed");
		
	}
	
	private void updateTechnician(HttpServletRequest request, HttpServletResponse response, TechnicianServiceImpl service) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("technician_id"));
		String name = request.getParameter("technician_name");
		String email = request.getParameter("technician_email");
		String password = request.getParameter("technician_password");
		int isActive = Integer.parseInt(request.getParameter("technician_is_active"));
		
		String[] testTypes = request.getParameterValues("technician_test_types");
		
		if (testTypes != null) {
	    	
	    	List<TestType> specifiedTestTypes = new ArrayList<TestType>();
	    	
	        for (String testType : testTypes) {
	        	TestType tt = new TestType(Integer.parseInt(testType));
	        	specifiedTestTypes.add(tt);
	        }

	    	Technician technician = new Technician();
	    	technician.setId(id);
	    	technician.setEmail(email);
	    	technician.setName(name);
	    	technician.setPassword(password);
	    	technician.setSpecificTestField(specifiedTestTypes);
	    	technician.setIsActive(isActive);
	    	
	    	boolean result;
	    	String message = "";
	    	
	    	try {
				result = service.updateTechnician(technician);
				if(result) {
					message = "Successfully Updated!";
				}
				else {
					message = "Failed!";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}

			TestTypeServiceImpl technicianService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
			getSpecificTechnicianForUpdate(request, response, service, technicianService, message);
	    	
	        
	    } else {
	    	TestTypeServiceImpl technicianService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
	    	getSpecificTechnicianForUpdate(request, response, service, technicianService, "Please Select Test Types!");
	    }
		
	}
	
	private void createTechnician(HttpServletRequest request, HttpServletResponse response, TechnicianServiceImpl service, CommonServiceImpl commonService) throws ServletException, IOException {
		String name = request.getParameter("technician_name");
		String email = request.getParameter("technician_email");
		String password = request.getParameter("technician_password");
		
		String[] testTypes = request.getParameterValues("technician_test_types");
		
	    if (testTypes != null) {
	    	
	    	List<TestType> specifiedTestTypes = new ArrayList<TestType>();
	    	
	        for (String testType : testTypes) {
	        	TestType tt = new TestType(Integer.parseInt(testType));
	        	specifiedTestTypes.add(tt);
	        }

	    	Technician technician = new Technician();
	    	technician.setEmail(email);
	    	technician.setName(name);
	    	technician.setPassword(password);
	    	technician.setSpecificTestField(specifiedTestTypes);
	    	technician.setRole("Technician");
	    	
	    	boolean result;
	    	String message = "";
	    	
	    	try {
	    		
	    		boolean isEmailExists = commonService.checkUserExists(technician);
	    		
	    		if(!isEmailExists) {
	 	    		
					result = service.createTechnician(technician);
					if(result) {
						message = "Successfully Created!";
					}
					else {
						message = "Failed!";
					}
	    			
	    		} else {
					message = "User already exists";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}

			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
			getTestTypesForCreateTechnician(request, response, testTypeService, message);
	    	
	        
	    } else {
			TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
			getTestTypesForCreateTechnician(request, response, testTypeService, "Please Select Test Types!");
	    }
	    
	    
	    
	}

	private void getAllTechnicians(HttpServletRequest request, HttpServletResponse response,
			TechnicianServiceImpl service) throws ServletException, IOException {

		String message = "";
		List<Technician> techniciansList;

		try {
			techniciansList = service.getAllTechnicians();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			techniciansList = new ArrayList<Technician>();
		}

		request.setAttribute("message", message);
		request.setAttribute("techniciansList", techniciansList);

		RequestDispatcher rd = request.getRequestDispatcher("manager-technicians.jsp");
		rd.forward(request, response);
	}

	private void getTestTypesForCreateTechnician(HttpServletRequest request, HttpServletResponse response,
			TestTypeServiceImpl testTypeService, String message) throws ServletException, IOException {
		List<TestType> testTypesList;

		try {
			testTypesList = testTypeService.getAllTestTypes();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			testTypesList = new ArrayList<TestType>();
		}

		request.setAttribute("message", message);
		request.setAttribute("testTypesList", testTypesList);

		RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-technician.jsp");
		rd.forward(request, response);
	}
	
	private void getSpecificTechnicianForUpdate(HttpServletRequest request, HttpServletResponse response, TechnicianServiceImpl technicianService, TestTypeServiceImpl testTypeService, String message) throws ServletException, IOException {
		
		List<TestType> testTypesList;
		
		try {
			testTypesList = testTypeService.getAllTestTypes();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			testTypesList = new ArrayList<TestType>();
		}
		request.setAttribute("testTypesList", testTypesList);
		
		Technician technician;
		
		try {
			int id = Integer.parseInt(request.getParameter("technician_id"));
			technician = technicianService.getSpecificTechnician(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			technician = new Technician();
		}
		
		request.setAttribute("technician", technician);
		
		List<TestType> technicianTestTypesList;
		
		try {
			int id = Integer.parseInt(request.getParameter("technician_id"));
			technicianTestTypesList = testTypeService.getTestTypesByTechnicianId(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			technicianTestTypesList = new ArrayList<TestType>();
		}
		request.setAttribute("technicianTestTypesList", technicianTestTypesList);
		
		request.setAttribute("message", message);
		
    	RequestDispatcher rd = request.getRequestDispatcher("manager-update-technician.jsp");
    	rd.forward(request, response);
		
	}

}
