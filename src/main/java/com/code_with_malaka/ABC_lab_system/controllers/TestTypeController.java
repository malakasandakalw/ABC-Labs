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
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.models.TestType;
import com.code_with_malaka.ABC_lab_system.services.TestTypeServiceImpl;

public class TestTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestTypeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
		
		System.out.println(type);
		
    	if(type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
    	} else if(type != null && type.equals("update-specific")) {
    		getSpecificTestForUpdate(request, response, testTypeService);
    	}
    	else {
        	getAllTestTypes(request, response, testTypeService);
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String type = request.getParameter("type");
		
		TestTypeServiceImpl testTypeService = (TestTypeServiceImpl) TestTypeServiceImpl.getTestTypeServiceInstance();
		
		System.out.println(type);
		
		if(type != null && type.equals("create")) {
    		createTestType(request, response, testTypeService);
    	}
    	else if(type != null && type.equals("update")) {
    		try {
				updateTestType(request, response, testTypeService);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	else if(type != null && type.equals("delete-specific")) {
        	deleteTestType(request, response, testTypeService);
    	}
		
	}
	
	private void createTestType(HttpServletRequest request, HttpServletResponse response, TestTypeServiceImpl service) throws ServletException, IOException {
		
		if ((request.getParameter("test_name") == null || request.getParameter("test_name") == "") || (request.getParameter("test_price") == null) || request.getParameter("test_price") == "") {
			
			request.setAttribute("message", "Name and Price field cannot be empty!");
			
			RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-test-type.jsp");
			rd.forward(request, response);
			
		} else {
			
			System.out.println("Validation Success");
			
			String name = request.getParameter("test_name");
			double price = Double.parseDouble(request.getParameter("test_price"));
			
			TestType testType =  new TestType(name, price);
			
			boolean result;
			
			String message = "";
			
			try {
				result = service.createTestType(testType);
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
			
			RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-test-type.jsp");
			rd.forward(request, response);
			
		}
		
	}
	
	private void updateTestType(HttpServletRequest request, HttpServletResponse response, TestTypeServiceImpl service) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		if ((request.getParameter("test_name") == null || request.getParameter("test_name") == "") || (request.getParameter("test_price") == null) || request.getParameter("test_price") == "") {
			
			int id = Integer.parseInt(request.getParameter("test_type_id"));
			TestType testType = service.getSpecificTestType(id);
			
			request.setAttribute("message", "Name and Price field cannot be empty!");
			request.setAttribute("testType", testType);
			
	    	RequestDispatcher rd = request.getRequestDispatcher("manager-update-test-type.jsp");
	    	rd.forward(request, response);
			
		} else {
			
			Integer id = Integer.parseInt(request.getParameter("test_type_id"));
			String name = request.getParameter("test_name");
			double price = Double.parseDouble(request.getParameter("test_price"));
			
			TestType testType =  new TestType(id, name, price);
			
			boolean result;
			
			String message = "";
			
			try {
				result = service.updateTestType(testType);
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
			request.setAttribute("testType", testType);
			
			RequestDispatcher rd = request.getRequestDispatcher("manager-update-test-type.jsp");
			rd.forward(request, response);
			
		}
	}
	
	private void deleteTestType(HttpServletRequest request, HttpServletResponse response, TestTypeServiceImpl service) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("test_type_id"));
		boolean result;
		
		String message = "";
		
		try {
			result = service.deleteTestType(id);
			if(result) {
				message = "Successfully Deleted!";
			}
			else {
				message = "Failed!";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		HttpSession session = request.getSession();
		
		try {
			List<TestType> testTypesList = service.getAllTestTypes();
			session.setAttribute("testTypesList", testTypesList);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		session.setAttribute("message", message);
		response.sendRedirect("manager-test-types.jsp");
		
	}
	
	private void getAllTestTypes(HttpServletRequest request, HttpServletResponse response, TestTypeServiceImpl service) throws ServletException, IOException {
		
		String message = "";
		List<TestType> testTypesList;
		
		try {
			testTypesList = service.getAllTestTypes();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			testTypesList = new ArrayList<TestType>();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("testTypesList", testTypesList);
		
    	RequestDispatcher rd = request.getRequestDispatcher("manager-test-types.jsp");
    	rd.forward(request, response);
	}
	
	private void getSpecificTestForUpdate(HttpServletRequest request, HttpServletResponse response, TestTypeServiceImpl service) throws ServletException, IOException {
		String message = "";
		TestType testType;
		
		try {
			int id = Integer.parseInt(request.getParameter("test_type_id"));
			testType = service.getSpecificTestType(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			testType = new TestType();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("testType", testType);
		
    	RequestDispatcher rd = request.getRequestDispatcher("manager-update-test-type.jsp");
    	rd.forward(request, response);
	}

}
