package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Appointment;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;

public class AppointmentsManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public Double calculateAppointmentPrice(Appointment appointment, Connection connection) throws SQLException {
		Double totalPriceDouble = (double) 0;
		List<AppointmentTest> testTypes = appointment.getAppointmentTests();
		StringBuilder idListBuilder = new StringBuilder();
		
		idListBuilder.append(testTypes.get(0).getTestType().getId());

	    for (int i = 1; i < testTypes.size(); i++) {
	        idListBuilder.append(",").append(testTypes.get(i).getTestType().getId());
	    }
	
	    String idList = idListBuilder.toString();
	    String query = "SELECT SUM(price) AS price FROM test_types WHERE id IN ("+ idList +")";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    while(rs.next()) {
	    	totalPriceDouble = rs.getDouble("price");
	    }
	    ps.close();
		return totalPriceDouble;
	}
	
	public boolean createAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 	
		Double totalPriceDouble = calculateAppointmentPrice(appointment, connection);
		
		String query = "INSERT INTO appointments (total_price, patient_id, doctor_details, email, contact_number) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setDouble(1, totalPriceDouble);
		ps.setInt(2, 3);
		ps.setString(3, appointment.getDetails());
		ps.setString(4, appointment.getEmail());
		ps.setString(5, appointment.getContactNumber());
		
		int result = ps.executeUpdate();
		
		int appointmentId = -1;
		ResultSet rs = ps.getGeneratedKeys(); 
		
		if(rs.next()) {
			appointmentId = rs.getInt(1);
		}
		
		appointment.setId(appointmentId);
		
		boolean isTestsCreated = createAppointmentTest(appointment);
		
		System.out.println(isTestsCreated);
		
		if(!isTestsCreated) {
//			delete added record and apppointment as well
			return false;
		} 
		
		rs.close();
		ps.close();
		connection.close();
		return result > 0;
	}
	
	private boolean createAppointmentTest(Appointment appointment) throws ClassNotFoundException, SQLException {
		boolean result = true;
		AppointmentTestsManager appointmentTestsManager = new AppointmentTestsManager();
		for (AppointmentTest appointmentTest : appointment.getAppointmentTests()) {
			try {
				appointmentTest.setAppointment(appointment);
	            boolean success = appointmentTestsManager.createAppointmentTest(appointmentTest);
	            if (!success) {
	                result = false;
	                break;
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            result = false;
	            break;
	        }
		}
		return result;
	}
	
}
