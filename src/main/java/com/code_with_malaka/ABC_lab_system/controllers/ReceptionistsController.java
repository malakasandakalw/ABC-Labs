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

import com.code_with_malaka.ABC_lab_system.models.Receptionist;
import com.code_with_malaka.ABC_lab_system.services.ReceptionistServiceImpl;


public class ReceptionistsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceptionistsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ReceptionistServiceImpl receptionistService = (ReceptionistServiceImpl) ReceptionistServiceImpl.getReceptionistServiceInstance();
		
    	if(type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
    	}
    	else {
        	getAllReceptionists(request, response, receptionistService);
    	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
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

}
