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

import com.code_with_malaka.ABC_lab_system.models.Manager;
import com.code_with_malaka.ABC_lab_system.services.ManagerServiceImpl;

public class ManagersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String type = request.getParameter("type");
    	ManagerServiceImpl managerService = (ManagerServiceImpl) ManagerServiceImpl.getManagerServiceInstance();
    	
    	if(type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
    	}
    	else {
        	getAllManagers(request, response, managerService);
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private void getAllManagers(HttpServletRequest request, HttpServletResponse response, ManagerServiceImpl service) throws ServletException, IOException {
		
		String message = "";
		List<Manager> managersList;
		
		try {
			managersList = service.getAllManagers();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			managersList = new ArrayList<Manager>();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("managersList", managersList);
		
    	RequestDispatcher rd = request.getRequestDispatcher("manager-managers.jsp");
    	rd.forward(request, response);
	}
	
}
