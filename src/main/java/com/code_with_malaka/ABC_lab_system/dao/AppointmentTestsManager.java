package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;

public class AppointmentTestsManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public boolean createAppointmentTest(AppointmentTest appointmentTest) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		
		String query = "INSERT INTO appointment_tests (appointment_id, test_type_id) VALUES (?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, appointmentTest.getAppointment().getId());
		ps.setInt(2, appointmentTest.getTestType().getId());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public List<AppointmentTest> getAppointmentTestsByAppointmentId(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		TechniciansManager techniciansManager = new TechniciansManager();
		TestResultManager testResultManager = new TestResultManager();
		TestTypeManager testTypeManager = new TestTypeManager();
		
		List<AppointmentTest> appointmentTestsList = new ArrayList<AppointmentTest>();
		
		String query = "SELECT * FROM appointment_tests WHERE appointment_id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			AppointmentTest appointmentTest = new AppointmentTest();
			appointmentTest.setId(rs.getInt("id"));
			appointmentTest.setStatus(rs.getString("status"));
			
			int technicianId = rs.getInt("technician_id");
			
			if (rs.wasNull()) {
				appointmentTest.setTechnician(null); 
			} else {
				appointmentTest.setTechnician(techniciansManager.getSpecificTechnician(rs.getInt("technician_id")));
			}
			
			int testResultId = rs.getInt("result_id");
			
			if (rs.wasNull()) {
				appointmentTest.setTestResult(null);
			} else {
//				appointmentTest.setTestResult(testResultManager.getSpecificTechnician(rs.getInt("result_id")));
			}
			
			appointmentTest.setTestType(testTypeManager.getSpecificTestType(rs.getInt("test_type_id")));
			appointmentTestsList.add(appointmentTest);
		}
		
		ps.close();
		connection.close();
		return appointmentTestsList;
		
	}
	
}
