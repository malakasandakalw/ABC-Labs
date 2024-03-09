package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.TestType;

public class TestTypeManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public List<TestType> getAllTestTypes() throws ClassNotFoundException, SQLException{
		
		Connection connection = getConnection(); 
		List<TestType> testTypesList = new ArrayList<TestType>();
		
		String query = "SELECT * FROM test_types";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			
			TestType testType = new TestType();
			testType.setId(resultset.getInt("id"));
			testType.setName(resultset.getString("name"));
			testType.setPrice(resultset.getDouble("price"));
			testTypesList.add(testType);
		}
		statement.close();
		connection.close();
		return testTypesList;
		
	}
}
