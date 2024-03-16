package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.dao.CommonManager;
import com.code_with_malaka.ABC_lab_system.dao.PasswordManager;
import com.code_with_malaka.ABC_lab_system.models.Dashboard;
import com.code_with_malaka.ABC_lab_system.models.Manager;
import com.code_with_malaka.ABC_lab_system.services.DashboardServiceImpl;
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
    	HttpSession session = request.getSession();
    	boolean isAuthenticated = isAuthenticated(session);
    	
    	if(type != null && type.equals("specific")) {
//    		getSpecificProduct(request, response, managerService);
    	}else if(type != null && type.equals("login")) {
    		
			login(request, response, "");
			
		}else if(type != null && type.equals("get-dashboard")) {
    		
			try {
				dashboard(request, response, "");
			} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
    	else {
        	getAllManagers(request, response, managerService);
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type != null && type.equals("create-manager")) {
    		try {
				createManager(request, response, "");
			} catch (ClassNotFoundException | SQLException | ServletException | IOException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
    	}
		
	}

	private boolean isAuthenticated(HttpSession session) {
		if (session.getAttribute("auth_manager_id") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String message) {
		PasswordManager passwordManager = new PasswordManager();
		ManagerServiceImpl managerService = new ManagerServiceImpl();
		Manager manager = new Manager();
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			manager.setEmail(email);
			
			manager = managerService.getSpecificManagerByEmail(email);
			
			if(manager.getId() > -1) {
				if (passwordManager.verify(password, manager.getPassword())) {
					request.setAttribute("manager", manager);
					
					CommonManager commonManager = new CommonManager();
					commonManager.login(request, manager);
					response.sendRedirect("managers?type=get-dashboard&session_id=" + request.getSession().getAttribute("auth_technician_id"));
				} else {
					request.setAttribute("message", "Invalid Credentials");
					
			    	RequestDispatcher rd = request.getRequestDispatcher("manager-login.jsp");
			    	rd.forward(request, response);					
				}
			} else {
				request.setAttribute("message", "User Does Not Exists.");
				
		    	RequestDispatcher rd = request.getRequestDispatcher("manager-login.jsp");
		    	rd.forward(request, response);					
			}
			
		} catch (Exception e) {
			message = e.getMessage();
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		Manager manager = new Manager();
		manager.setRole("Manager");
		CommonManager commonManager = new CommonManager();
		commonManager.logout(request, manager);
		request.setAttribute(message, "Logged out successfully!");
    	response.sendRedirect("manager-login.jsp");	
	}
	
	private void createManager(HttpServletRequest request, HttpServletResponse response, String message) throws ClassNotFoundException, SQLException, ServletException, IOException, NoSuchAlgorithmException {
		CommonManager commonManager = new CommonManager();
		Manager manager = new Manager(request.getParameter("email"));
		boolean isUserExists = commonManager.checkIfUserExists(manager);
		
		if(isUserExists) {
			request.setAttribute("message", "Manager already exists.");	
			RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-manager.jsp");
			rd.forward(request, response);			
		} else {
			PasswordManager passwordManager = new PasswordManager();
			ManagerServiceImpl managerService = new ManagerServiceImpl();
			
			manager.setName(request.getParameter("name"));
			manager.setEmail(request.getParameter("email"));
			manager.setPassword(passwordManager.passwordHash(request.getParameter("password")));
			
			boolean result;
			
			try {
				result = managerService.createManager(manager);
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
			
			RequestDispatcher rd = request.getRequestDispatcher("manager-add-new-manager.jsp");
			rd.forward(request, response);
			
		}
		
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
	
	private void dashboard(HttpServletRequest request, HttpServletResponse response, String message) throws ClassNotFoundException, SQLException, ServletException, IOException {
		DashboardServiceImpl dahDashboardService = new DashboardServiceImpl();
		Dashboard dashboard = dahDashboardService.getDashboard();
		request.setAttribute("dashboard", dashboard);
    	RequestDispatcher rd = request.getRequestDispatcher("manager-dashboard.jsp");
    	rd.forward(request, response);
	}
	
}
