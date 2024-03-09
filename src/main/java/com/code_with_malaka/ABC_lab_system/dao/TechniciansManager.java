package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;

import com.code_with_malaka.ABC_lab_system.models.Technician;

public class TechniciansManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public List<Technician> getAllTechnicians() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		List<Technician> techniciansList = new ArrayList<Technician>();
		
		String query = "SELECT * FROM technicians";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			
			Technician technician = new Technician();
			technician.setId(resultset.getInt("id"));
			technician.setEmail(resultset.getString("email"));
			technician.setName(resultset.getString("name"));
			techniciansList.add(technician);
		}
		statement.close();
		connection.close();
		return techniciansList;
		
	} 
	
}
