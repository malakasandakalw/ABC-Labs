package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.models.PaymentRecipt;

public class PaymentReciptsManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public boolean createPaymentRecipt(PaymentRecipt paymentRecipt, int appointmentId) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection(); 
		String query = "INSERT INTO payment_recipts (appointment_id, total_price) VALUES (?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, appointmentId);
		ps.setDouble(2, paymentRecipt.getTotalPrice());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public PaymentRecipt getSpecificPaymentRecipt(int appointmentId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection(); 
		System.out.println("int appointment id" + appointmentId);
		String query = "SELECT * FROM payment_recipts WHERE appointment_id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, appointmentId);
		
		ResultSet rs = ps.executeQuery();
		PaymentRecipt paymentRecipt = new PaymentRecipt();
		
		while(rs.next()) {
			System.out.println(rs.getInt("id"));
			System.out.println(rs.getDouble("total_price"));
			System.out.println(rs.getTimestamp("created_at"));
			paymentRecipt.setId(rs.getInt("id"));
			paymentRecipt.setTotalPrice(rs.getDouble("total_price"));
			paymentRecipt.setCreatedAt(rs.getTimestamp("created_at"));
		}
		
		ps.close();
		connection.close();		
		return paymentRecipt;
		
		
	}
	
}
