package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.code_with_malaka.ABC_lab_system.models.Dashboard;
import com.code_with_malaka.ABC_lab_system.models.DurationFilter;
import com.code_with_malaka.ABC_lab_system.models.PaymentRecipt;

public class DashboardManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public Dashboard getDashboard() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		Dashboard dashboard = new Dashboard();
		
		String query = "SELECT count(*) AS appointments_count, (SELECT count(*) FROM appointments WHERE status='Done') AS done_count, (SELECT count(*) FROM appointments where status='Cancel') AS cancel_count, (SELECT count(*) FROM appointments where status='Processing') AS processing_count, (SELECT sum(total_price) FROM payment_recipts) AS total_earning , (SELECT COUNT(*) FROM patients) AS patients_count FROM appointments";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			dashboard.setTotalAppointmentsCount(resultset.getInt("appointments_count"));
			dashboard.setCancelledAppointmentsCount(resultset.getInt("cancel_count"));
			dashboard.setDoneAppointmentsCount(resultset.getInt("done_count"));
			dashboard.setProcessingAppointmentsCount(resultset.getInt("processing_count"));
			dashboard.setTotalPatients(resultset.getInt("patients_count"));
			dashboard.setTotalEarning(resultset.getDouble("total_earning"));
		}
		
		statement.close();
		connection.close();
		return dashboard;
		
	}
	
	public Dashboard getDashboardByDates(DurationFilter duration) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		Dashboard dashboard = new Dashboard();
		
		Date startDate = duration.getStartDate();
		Date endDate = duration.getEndDate();
		
		String query = "SELECT count(*) AS appointments_count, (SELECT count(*) FROM appointments WHERE status='Done' AND appointments.created_at >= ? AND appointments.created_at <= ?) AS done_count,"
				+ "(SELECT count(*) FROM appointments where status='Cancel' AND appointments.created_at >= ? AND appointments.created_at <= ?) AS cancel_count,"
				+ "(SELECT count(*) FROM appointments where status='Processing' AND appointments.created_at >= ? AND appointments.created_at <= ?) AS processing_count,"
				+ "(SELECT sum(total_price) FROM payment_recipts WHERE payment_recipts.created_at >= ? AND payment_recipts.created_at <= ?) AS total_earning ,"
				+ "(SELECT COUNT(*) FROM patients) AS patients_count FROM appointments WHERE appointments.created_at >= ? AND appointments.created_at <= ?;";
		
		
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setDate(1, startDate);
		statement.setDate(2, endDate);
		statement.setDate(3, startDate);
		statement.setDate(4, endDate);
		statement.setDate(5, startDate);
		statement.setDate(6, endDate);
		statement.setDate(7, startDate);
		statement.setDate(8, endDate);
		statement.setDate(9, startDate);
		statement.setDate(10, endDate);
		
		ResultSet resultset = statement.executeQuery();		
		
		while (resultset.next()) {
			dashboard.setTotalAppointmentsCount(resultset.getInt("appointments_count"));
			dashboard.setCancelledAppointmentsCount(resultset.getInt("cancel_count"));
			dashboard.setDoneAppointmentsCount(resultset.getInt("done_count"));
			dashboard.setProcessingAppointmentsCount(resultset.getInt("processing_count"));
			dashboard.setTotalPatients(resultset.getInt("patients_count"));
			dashboard.setTotalEarning(resultset.getDouble("total_earning"));
		}
		
		statement.close();
		connection.close();
		return dashboard;
		
	}
	
}
