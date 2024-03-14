package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.code_with_malaka.ABC_lab_system.models.User;

public class CommonManager {
	
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public boolean checkIfUserExists(User user) throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection(); 
		
		String table = "";
		
		if(user.getRole() == "Technician") {
			table = "technicians";
		}else if(user.getRole() == "Manager") {
			table = "managers";
		}else if(user.getRole() == "Receptionist") {
			table = "receptionists";
		}else if(user.getRole() == "Patient") {
			table = "patients";
		}
		
		String query = "SELECT * FROM "+ table +" WHERE email = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, user.getEmail());
		
		ResultSet rs = ps.executeQuery();
		
		boolean userExists = rs.next();
		
	    rs.close();
	    ps.close();
	    connection.close();

	    return userExists;
		
	}
	
	public HttpServletRequest login(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        System.out.println(user.getRole());
        if(user.getRole() == "Technician") {
        	session.setAttribute("auth_technician_id", user.getId());
		}else if(user.getRole() == "Manager") {
			
		}else if(user.getRole() == "Receptionist") {
			
		}else if(user.getRole() == "Patient") {
	        session.setAttribute("auth_patient_id", user.getId());
		}
        return request;
	}
	
	public void logout() {
		
	}
	
}
