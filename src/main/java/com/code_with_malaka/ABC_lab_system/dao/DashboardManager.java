package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.code_with_malaka.ABC_lab_system.models.Dashboard;

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
		
		String query = "SELECT count(*) AS appointments_count, (SELECT count(*) FROM appointments WHERE status='Done') AS done_count, (SELECT count(*) FROM appointments where status='Cancel') AS cancel_count, (SELECT count(*) FROM appointments where status='Processing') AS processing_count, (SELECT sum(total_price) FROM appointments) AS total_earning , (SELECT COUNT(*) FROM patients) AS patients_count FROM appointments";
		
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
	
}
