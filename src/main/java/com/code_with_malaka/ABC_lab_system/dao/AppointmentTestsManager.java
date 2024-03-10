package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
