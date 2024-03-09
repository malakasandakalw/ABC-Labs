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
		
    	if(type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
    	}
    	else {
        	getAllTestTypes(request, response, testTypeService);
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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

}
