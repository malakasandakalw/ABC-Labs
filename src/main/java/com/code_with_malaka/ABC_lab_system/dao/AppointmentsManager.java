package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	
	public List<Appointment> getAllAppointments() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		List<Appointment> appointmentsList = new ArrayList<Appointment>();
		
		String query = "SELECT * FROM appointments ORDER BY created_at DESC";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		while (resultset.next()) {
			Appointment appointment = new Appointment();
			appointment.setId(resultset.getInt("id"));
			appointment.setDate(resultset.getDate("date"));
			appointment.setStatus(resultset.getString("status"));
			appointment.setTotalPrice(resultset.getDouble("total_price"));
			appointment.setCreatedAt(resultset.getTimestamp("created_at"));
			appointmentsList.add(appointment);
		}
		statement.close();
		connection.close();
		return appointmentsList;
	}
	
	public List<Appointment> getAllAppointmentsByPatient(int id) throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection(); 
		List<Appointment> appointmentsList = new ArrayList<Appointment>();
		System.out.println(id);
		String query = "SELECT * FROM appointments WHERE patient_id = ? ORDER BY created_at DESC";
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setInt(1, id);
		
		ResultSet resultset = ps.executeQuery();
		while (resultset.next()) {
			Appointment appointment = new Appointment();
			appointment.setId(resultset.getInt("id"));
			appointment.setTotalPrice(resultset.getDouble("total_price"));
			appointment.setDate(resultset.getDate("date"));
			appointment.setStatus(resultset.getString("status"));
			appointment.setCreatedAt(resultset.getTimestamp("created_at"));
			appointmentsList.add(appointment);
		}
		
		ps.close();
		connection.close();
		return appointmentsList;
	}
	
	public Appointment getSpecificAppointment(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		AppointmentTestsManager appointmentTestsManager = new AppointmentTestsManager();
		PaymentReciptsManager paymentReciptsManager = new PaymentReciptsManager();
		
		String query = "SELECT id, contact_number, email, created_at, date, doctor_details, status, total_price, (SELECT id FROM payment_recipts WHERE payment_recipts.appointment_id = appointments.id) AS payment_id FROM appointments WHERE id = ?";
		
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Appointment appointment = new Appointment();
		
		while(rs.next()) {
			appointment.setId(rs.getInt("id"));
			appointment.setContactNumber(rs.getString("contact_number"));
			appointment.setEmail(rs.getString("email"));
			appointment.setCreatedAt(rs.getTimestamp("created_at"));
			appointment.setDate(rs.getDate("date"));
			appointment.setDetails(rs.getString("doctor_details"));
			appointment.setStatus(rs.getString("status"));
			appointment.setTotalPrice(rs.getDouble("total_price"));
			appointment.setAppointmentTests(appointmentTestsManager.getAppointmentTestsByAppointmentId(id));
			
			int paymentId = rs.getInt("payment_id");
			
			if (rs.wasNull()) {
				appointment.setPaymentRecipt(null);
			} else {
				appointment.setPaymentRecipt(paymentReciptsManager.getSpecificPaymentRecipt(rs.getInt("id")));
			}
			
		}
		
		ps.close();
		connection.close();		
		return appointment;
	}
	
	public boolean createAppointment(Appointment appointment, int patientId) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 	
		Double totalPriceDouble = calculateAppointmentPrice(appointment, connection);
		
		Date currentDate = new Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		
		String query = "INSERT INTO appointments (total_price, patient_id, doctor_details, email, contact_number, created_at) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setDouble(1, totalPriceDouble);
		ps.setInt(2, patientId);
		ps.setString(3, appointment.getDetails());
		ps.setString(4, appointment.getEmail());
		ps.setString(5, appointment.getContactNumber());
		ps.setTimestamp(6, timestamp);
		
		int result = ps.executeUpdate();
		
		int appointmentId = -1;
		ResultSet rs = ps.getGeneratedKeys(); 
		
		if(rs.next()) {
			appointmentId = rs.getInt(1);
		}
		
		appointment.setId(appointmentId);
		
		boolean isTestsCreated = createAppointmentTest(appointment);
		
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
	
	public boolean updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		String query = "UPDATE appointments SET date = ?, status = ?, total_price = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		Timestamp timestamp = new Timestamp(appointment.getDate().getTime());
		
		ps.setTimestamp(1, timestamp);
		ps.setString(2, appointment.getStatus());
		ps.setDouble(3, appointment.getTotalPrice());
		ps.setInt(4, appointment.getId());
		
		int result = ps.executeUpdate();
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public boolean updateAppointmentStatus(Appointment appointment) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		String query = "UPDATE appointments SET status = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setString(1, appointment.getStatus());
		ps.setInt(2, appointment.getId());
		
		int result = ps.executeUpdate();
		ps.close();
		connection.close();		
		return result > 0;
	}
	
}
