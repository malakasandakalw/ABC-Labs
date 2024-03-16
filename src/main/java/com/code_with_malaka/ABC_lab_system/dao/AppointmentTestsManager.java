package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Appointment;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.TestResult;

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
	
	public boolean updateSpecificAppointmentTest(AppointmentTest appointmentTest) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection(); 
		String query = "UPDATE appointment_tests SET status = ?, technician_id = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setString(1, appointmentTest.getStatus());
		ps.setInt(2, appointmentTest.getTechnician().getId());
		ps.setInt(3, appointmentTest.getId());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public boolean updateSpecificAppointmentTestStatus(AppointmentTest appointmentTest) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection(); 
		String query = "UPDATE appointment_tests SET status = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setString(1, appointmentTest.getStatus());
		ps.setInt(2, appointmentTest.getId());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public boolean updateSpecificAppointmentTestByTechnician(AppointmentTest appointmentTest) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection(); 
		String query = "UPDATE appointment_tests SET status = ?, result_id = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setString(1, appointmentTest.getStatus());
		System.out.println(appointmentTest.getTestResult().getid());
		ps.setInt(2, appointmentTest.getTestResult().getid());
		ps.setInt(3, appointmentTest.getId());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public AppointmentTest getSpecificAppointmentTestById(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		PatientsManager patientsManager = new PatientsManager();
		AppointmentsManager appointmentsManager = new AppointmentsManager();
		TestResultManager testResultManager = new TestResultManager();
		
		AppointmentTest appointmentTest = new AppointmentTest();
		TestTypeManager testTypeManager = new TestTypeManager();
		
		String query = "SELECT id, appointment_id, technician_id, status, result_id, test_type_id, (SELECT patient_id FROM appointments WHERE id = appointment_id) AS patient_id FROM appointment_tests WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			appointmentTest.setId(rs.getInt("id"));
			appointmentTest.setAppointment(appointmentsManager.getSpecificAppointment(rs.getInt("appointment_id")));
			appointmentTest.setStatus(rs.getString("status"));
			
			appointmentTest.setPatient(patientsManager.getSpecificPatient(rs.getInt("patient_id")));
			appointmentTest.setTestType(testTypeManager.getSpecificTestType(rs.getInt("test_type_id")));
			
			int testResultId = rs.getInt("result_id");
			
			if (rs.wasNull()) {
				appointmentTest.setTestResult(null);
			} else {
				TestResult testResult = new TestResult();
				testResult = testResultManager.getSpecifcTestResultById(testResultId);
				appointmentTest.setTestResult(testResult);
			}
			
		}
		
		ps.close();
		connection.close();
		return appointmentTest;
		
	}
	
	public List<AppointmentTest> getAppointmentTestsByAppointmentId(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		TechniciansManager techniciansManager = new TechniciansManager();
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
				appointmentTest.setTechnician(techniciansManager.getSpecificTechnician(technicianId));
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
