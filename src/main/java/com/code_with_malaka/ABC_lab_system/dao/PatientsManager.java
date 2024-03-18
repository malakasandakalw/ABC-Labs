package com.code_with_malaka.ABC_lab_system.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
        LocalDate lDateOfBirth = LocalDate.parse(dateOfBirth.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(lDateOfBirth, currentDate);
        return period.getYears();
    }
	
	public boolean createPatient(Patient patient) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		Connection connection = getConnection(); 
		String query = "INSERT INTO patients (name, email, password, contact_number, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, patient.getName());
		ps.setString(2, patient.getEmail());
		ps.setString(3, patient.getPassword());
		ps.setString(4, patient.getContactNumber());
		
	    long millis = patient.getDob().getTime();
	    java.sql.Date dob = new java.sql.Date(millis);
	    
	    ps.setDate(5, dob);
		ps.setString(6, patient.getGender());
	    
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public Patient getSpecificPatient(int id) throws ClassNotFoundException, SQLException {
		
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
			patient.setPassword(rs.getString("password"));
			patient.setContactNumber(rs.getString("contact_number"));
			patient.setDob(rs.getDate("dob"));
			patient.setGender(rs.getString("gender"));
			patient.setAge(calculateAge(rs.getDate("dob")));
		}
		
		ps.close();
		connection.close();	
		
		return patient;
	}
	
	public Patient getSpecificPatient(Patient patient) throws ClassNotFoundException, SQLException {

		Connection connection = getConnection(); 
		String query = "SELECT * FROM patients WHERE email = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, patient.getEmail());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			patient.setId(rs.getInt("id"));
			patient.setName(rs.getString("name"));
			patient.setEmail(rs.getString("email"));
			patient.setPassword(rs.getString("password"));
			patient.setContactNumber(rs.getString("contact_number"));
			patient.setDob(rs.getDate("dob"));
			patient.setGender(rs.getString("gender"));
			patient.setAge(calculateAge(rs.getDate("dob")));
		}
		
		ps.close();
		connection.close();	
		
		return patient;
		
	}
	
	public Patient getSpecificPatientByEmail(String email) throws ClassNotFoundException, SQLException {

		Connection connection = getConnection(); 
		String query = "SELECT * FROM patients WHERE email = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, email);
		
		Patient patient = new Patient();
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			patient.setId(rs.getInt("id"));
			patient.setName(rs.getString("name"));
			patient.setEmail(rs.getString("email"));
			patient.setPassword(rs.getString("password"));
			patient.setContactNumber(rs.getString("contact_number"));
			patient.setDob(rs.getDate("dob"));
			patient.setGender(rs.getString("gender"));
			patient.setAge(calculateAge(rs.getDate("dob")));
			patient.setRole("Patient");
		} else {
			patient.setId(-999);
		}
		
		ps.close();
		connection.close();	
		
		return patient;
		
	}
	
}
