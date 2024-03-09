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

import com.code_with_malaka.ABC_lab_system.models.Technician;
import com.code_with_malaka.ABC_lab_system.services.TechnicianServiceImpl;

public class TechniciansController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TechniciansController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		TechnicianServiceImpl technicianService = (TechnicianServiceImpl) TechnicianServiceImpl
				.getTechnicianServiceInstance();

		if (type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
		} else {
			getAllTechnicians(request, response, technicianService);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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

}
