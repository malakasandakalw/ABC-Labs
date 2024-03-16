package com.code_with_malaka.ABC_lab_system.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Receptionist;

public class ReceptionistsManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		System.out.println("here 1");
		DbConnector connector =  getDbConnector();
		System.out.println(connector);
		return connector.getDbConnection();
	}
	
	public boolean createReceptionist(Receptionist receptionist) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		PasswordManager passwordManager = new PasswordManager();
		Connection connection = getConnection(); 
		String query = "INSERT INTO receptionists (name, email, password) VALUES (?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, receptionist.getName());
		ps.setString(2, receptionist.getEmail());
		ps.setString(3, passwordManager.passwordHash(receptionist.getPassword()));
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public List<Receptionist> getAllReceptionists() throws ClassNotFoundException, SQLException{
		
		Connection connection = getConnection(); 
		List<Receptionist> receptionistsList = new ArrayList<Receptionist>();
		
		String query = "SELECT * FROM receptionists";
		
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			
			Receptionist receptionist = new Receptionist();
			receptionist.setId(resultset.getInt("id"));
			receptionist.setEmail(resultset.getString("email"));
			receptionist.setName(resultset.getString("name"));
			receptionist.setPassword(resultset.getString("password"));
			receptionistsList.add(receptionist);
		}
		statement.close();
		connection.close();
		return receptionistsList;
		
	}
	
	public Receptionist getSpecifcReceptionistByEmail(String email) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection(); 
		
		String query = "SELECT * FROM receptionists WHERE email = ? AND is_active IS TRUE";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		
		Receptionist receptionist = new Receptionist();
		
	    if (rs.next()) {
	    	receptionist.setId(rs.getInt("id"));
	    	receptionist.setName(rs.getString("name"));
	    	receptionist.setEmail(rs.getString("email"));
	    	receptionist.setPassword(rs.getString("password"));
	    	receptionist.setIsActive(rs.getInt("is_active"));
	    	receptionist.setIsChangedDefaultPassword(rs.getInt("is_def_pw_changed"));
	    	receptionist.setRole("Receptionist");
	    } else {
	    	receptionist.setId(-999);
	    }
	    
	    System.out.println("select : " + receptionist.getId());
		
		ps.close();
		connection.close();		
		return receptionist;
		
	}
	
}
