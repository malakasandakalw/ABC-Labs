package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public boolean createTestType(TestType testType) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		String query = "INSERT INTO test_types (name, price) VALUES (?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, testType.getName());
		ps.setDouble(2, testType.getPrice());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public boolean updateTestType(TestType testType) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		String query = "UPDATE test_types SET name = ?, price = ? WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, testType.getName());
		ps.setDouble(2, testType.getPrice());
		ps.setInt(3, testType.getId());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public boolean deleteTestType(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		String query = "DELETE FROM test_types WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
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
	
	public TestType getSpecificTestType(int id) throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection(); 
		
		String query = "SELECT * FROM test_types WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		TestType testType = new TestType();
		
		while(rs.next()) {
			testType.setId(rs.getInt("id"));
			testType.setName(rs.getString("name"));
			testType.setPrice(rs.getDouble("price"));
		}
		
		ps.close();
		connection.close();		
		return testType;
		
	}
	
}
