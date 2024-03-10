package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Manager;

public class ManagersManager {
	
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public boolean addManager(Manager manager) throws ClassNotFoundException, SQLException {
		PasswordManager passwordManager = new PasswordManager();
		Connection connection = getConnection(); 
		String query = "INSERT INTO managers (name, email, password) VALUES (?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, manager.getName());
		ps.setString(2, manager.getEmail());
		ps.setString(3, passwordManager.passwordHash(manager.getPassword()));
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public List<Manager> getAllManagers() throws ClassNotFoundException, SQLException{
		
		Connection connection = getConnection(); 
		List<Manager> managersList = new ArrayList<Manager>();
		
		String query = "SELECT * FROM managers";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			
			Manager manager = new Manager();
			manager.setId(resultset.getInt("id"));
			manager.setEmail(resultset.getString("email"));
			manager.setName(resultset.getString("name"));
			manager.setPassword(resultset.getString("password"));
			managersList.add(manager);
		}
		statement.close();
		connection.close();
		return managersList;
		
	}

}
