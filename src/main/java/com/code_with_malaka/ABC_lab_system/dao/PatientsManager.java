package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import com.code_with_malaka.ABC_lab_system.models.Patient;

public class PatientsManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
    public static int calculateAge(Date dateOfBirth) {
        LocalDate birthDate = dateOfBirth.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears();
    }
	
	public boolean createPatient(Patient patient) throws ClassNotFoundException, SQLException {
		PasswordManager passwordManager = new PasswordManager();
		Connection connection = getConnection(); 
		String query = "INSERT INTO patients (name, email, password, contact_number, dob) VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, patient.getName());
		ps.setString(2, patient.getEmail());
		ps.setString(3, passwordManager.passwordHash(patient.getPassword()));
		ps.setString(4, patient.getContactNumber());
		ps.setDate(5, (Date) patient.getDob());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public Patient getSpecificPatient(int id) throws ClassNotFoundException, SQLException {
		PasswordManager passwordManager = new PasswordManager();
		Connection connection = getConnection(); 
		String query = "SELECT * FROM patients WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Patient patient = new Patient();
		
		while (rs.next()) {
			patient.setId(rs.getInt("id"));
			patient.setName(rs.getString("name"));
			patient.setEmail(rs.getString("email"));
			patient.setPassword(passwordManager.passwordVerify(rs.getString("password")));
			patient.setContactNumber(rs.getString("contact_number"));
			patient.setDob(rs.getDate("dob"));
			patient.setAge(calculateAge(rs.getDate("dob")));
		}
		
		ps.close();
		connection.close();	
		
		return patient;
	}
	
}
