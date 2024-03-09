package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Receptionist;

public class ReceptionistsManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		System.out.println("here 1");
		DbConnector connector =  getDbConnector();
		System.out.println(connector);
		return connector.getDbConnection();
	}
	
	public List<Receptionist> getAllReceptionists() throws ClassNotFoundException, SQLException{
		
		Connection connection = getConnection(); 
		List<Receptionist> receptionistsList = new ArrayList<Receptionist>();
		
		String query = "SELECT * FROM receptionists";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			
			Receptionist receptionist = new Receptionist();
			receptionist.setId(resultset.getInt("id"));
			receptionist.setEmail(resultset.getString("email"));
			receptionist.setName(resultset.getString("name"));
			receptionist.setPassword(resultset.getString("password"));
			receptionistsList.add(receptionist);
		}
		statement.close();
		connection.close();
		return receptionistsList;
		
	}
}
