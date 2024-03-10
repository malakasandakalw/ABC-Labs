package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
