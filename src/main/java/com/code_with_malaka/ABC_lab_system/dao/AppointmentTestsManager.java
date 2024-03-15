package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Appointment;
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
//			
			}
			
		}
		
		ps.close();
		connection.close();
		return appointmentTest;
		
	}
	
	public AppointmentTest getSpecificAppointmentTestByAppointmentId(int testTypeId, int appointmentId) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		TechniciansManager techniciansManager = new TechniciansManager();
		TestResultManager testResultManager = new TestResultManager();
		TestTypeManager testTypeManager = new TestTypeManager();
		
		AppointmentTest appointmentTest = new AppointmentTest();
		
		String query = "SELECT * FROM appointment_tests WHERE appointment_id = ? AND test_type_id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, appointmentId);
		ps.setInt(2, testTypeId);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			appointmentTest.setId(rs.getInt("id"));
			appointmentTest.setAppointment(new Appointment(rs.getInt("appointment_id")));
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
//			
			}
			
			appointmentTest.setTestType(testTypeManager.getSpecificTestType(rs.getInt("test_type_id")));
			
		}
		
		ps.close();
		connection.close();
		return appointmentTest;
		
	}
	
	public AppointmentTest getSpecificAppointmentTest(int id) throws ClassNotFoundException, SQLException {
		return null;
	}
	
	public List<AppointmentTest> getAppointmentTestsByTechnicianId(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		TechniciansManager techniciansManager = new TechniciansManager();
		TestTypeManager testTypeManager = new TestTypeManager();
		AppointmentsManager appointmentsManager = new AppointmentsManager();
		
		List<AppointmentTest> appointmentTestsList = new ArrayList<AppointmentTest>();
		
		String query = "SELECT * FROM appointment_tests WHERE technician_id = ?";
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
			appointmentTest.setAppointment(appointmentsManager.getSpecificAppointment(rs.getInt("appointment_id")));
			appointmentTestsList.add(appointmentTest);
		}
		
		ps.close();
		connection.close();
		return appointmentTestsList;
		
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
