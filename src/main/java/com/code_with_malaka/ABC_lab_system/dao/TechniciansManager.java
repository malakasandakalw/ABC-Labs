package com.code_with_malaka.ABC_lab_system.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;

import com.code_with_malaka.ABC_lab_system.models.Technician;
import com.code_with_malaka.ABC_lab_system.models.TestType;

public class TechniciansManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public boolean createTechnician(Technician technician) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = getConnection(); 
		String query = "INSERT INTO technicians (name, email, password) VALUES (?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, technician.getName());
		ps.setString(2, technician.getEmail());
		ps.setString(3, technician.getPassword());
		
		int result = ps.executeUpdate();
		
		int technicianId = -1;
		ResultSet rs = ps.getGeneratedKeys(); 
		
		if(rs.next()) {
			technicianId = rs.getInt(1);
		}
	
		boolean isTestsAssigned = createTechniciansTestTypes(technicianId, technician, connection);
		
		boolean finalResult = false;
		
		if(isTestsAssigned && result > 0) {
			finalResult = true;
		} else if(!isTestsAssigned && result > 0) {
			finalResult = false;
			deleteTechnician(technicianId);
		}
		
		rs.close();
		ps.close();
		connection.close();		
		return finalResult;
		
	}
	
	public boolean updateTechnician(Technician technician) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		
		Connection connection = getConnection();
		
		String query = "UPDATE technicians SET is_active=? WHERE id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, technician.getIsActive());
		ps.setInt(2, technician.getId());
		
		int result = ps.executeUpdate();
		
		deleteTechnicianTestTypes(technician.getId(), technician, connection);
		
		boolean isTestsAssigned = createTechniciansTestTypes(technician.getId(), technician, connection);
		
		boolean finalResult = false;
		
		if(isTestsAssigned && result > 0) {
			finalResult = true;
		} else if(!isTestsAssigned && result > 0) {
			finalResult = false;
		}
		
		ps.close();
		connection.close();		
		return finalResult;
		
	}
	
	public boolean updatePassword(int id, String password) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		
		String query = "UPDATE technicians SET password=? WHERE id=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, password);
		ps.setInt(2, id);
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	private boolean createTechniciansTestTypes(int technicianId, Technician technician, Connection connection) throws SQLException {
		String query = "INSERT INTO technicians_specific_tests (test_type_id, technician_id) VALUES (?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);
		
		for (TestType testType : technician.getSpecificTestField()) {
            ps.setInt(1, testType.getId()); 
            ps.setInt(2, technicianId);
            ps.addBatch();
        }
		
		int[] results = ps.executeBatch();
		
        for (int result : results) {
            if (result <= 0) {
                return false;
            }
        }
		
        ps.close();
        
		return true;
		
	}
	
	private boolean deleteTechnicianTestTypes(int technicianId, Technician technician, Connection connection) throws SQLException {
		
		String query = "DELETE FROM technicians_specific_tests WHERE technician_id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, technicianId);
		
		int result = ps.executeUpdate();
		
		ps.close();	
		return result > 0;
	}
	
	public boolean deleteTechnician(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		String query = "DELETE FROM technicians WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
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
			technician.setIsActive(resultset.getInt("is_active"));
			techniciansList.add(technician);
		}
		statement.close();
		connection.close();
		return techniciansList;
		
	} 
	
	public Technician getSpecificTechnician(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		
		String query = "SELECT * FROM technicians WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		Technician technician = new Technician();
		
		while(rs.next()) {
			technician.setId(rs.getInt("id"));
			technician.setName(rs.getString("name"));
			technician.setEmail(rs.getString("email"));
			technician.setPassword(rs.getString("password"));
			technician.setIsActive(rs.getInt("is_active"));
			technician.setIsChangedDefaultPassword(rs.getInt("is_def_pw_changed"));
		}
		
		ps.close();
		connection.close();		
		return technician;
	}
	
	public Technician getSpecificTechnicianByEmail(String email) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		
		String query = "SELECT * FROM technicians WHERE email = ? AND is_active IS TRUE";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		
		Technician technician = new Technician();
		
	    if (rs.next()) {
	        technician.setId(rs.getInt("id"));
	        technician.setName(rs.getString("name"));
	        technician.setEmail(rs.getString("email"));
	        technician.setPassword(rs.getString("password"));
	        technician.setIsActive(rs.getInt("is_active"));
	        technician.setIsChangedDefaultPassword(rs.getInt("is_def_pw_changed"));
	        technician.setRole("Technician");
	    } else {
	        technician.setId(-999);
	    }
		
		ps.close();
		connection.close();		
		return technician;
	}
	
	public List<Technician> getTechniciansByTestType(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		List<Technician> techniciansList = new ArrayList<Technician>();

		String query = "SELECT * FROM technicians WHERE id IN (SELECT technician_id FROM technicians_specific_tests WHERE test_type_id = ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);

		ResultSet resultset = ps.executeQuery();
		
		while (resultset.next()) {
			Technician technician = new Technician();
			technician.setId(resultset.getInt("id"));
			technician.setEmail(resultset.getString("email"));
			technician.setName(resultset.getString("name"));
			techniciansList.add(technician);
		}
		
		ps.close();
		connection.close();		
		return techniciansList;
		
	}
	
}
