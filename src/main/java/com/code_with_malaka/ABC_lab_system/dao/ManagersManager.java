package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Manager;

public class ManagersManager {
	
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
	
    // Method to hash a password
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Generate a salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        
        // Hash the password with salt
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashedPassword = digest.digest(password.getBytes());
        
        // Combine the salt and hashed password
        byte[] combined = new byte[salt.length + hashedPassword.length];
        System.arraycopy(salt, 0, combined, 0, salt.length);
        System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);
        
        // Convert to Base64 and return
        return Base64.getEncoder().encodeToString(combined);
    }
    
    // Method to verify a password
    public static boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // Decode the hashed password
        byte[] combined = Base64.getDecoder().decode(hashedPassword);
        
        // Extract the salt and hashed password
        byte[] salt = new byte[16];
        byte[] savedHashedPassword = new byte[combined.length - 16];
        System.arraycopy(combined, 0, salt, 0, 16);
        System.arraycopy(combined, 16, savedHashedPassword, 0, combined.length - 16);
        
        // Hash the provided password with the same salt
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashedPasswordToCheck = digest.digest(password.getBytes());
        
        // Compare the hashed passwords
        return MessageDigest.isEqual(savedHashedPassword, hashedPasswordToCheck);
    }
	
	public boolean addManager(Manager manager) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = getConnection(); 
		String query = "INSERT INTO managers (name, email, password) VALUES (?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, manager.getName());
		ps.setString(2, manager.getEmail());
		ps.setString(3, hashPassword(manager.getPassword()));
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}
	
	public List<Manager> getAllManagers() throws ClassNotFoundException, SQLException{
		
		Connection connection = getConnection(); 
		System.out.println("connection");
		List<Manager> managersList = new ArrayList<Manager>();
		
		String query = "SELECT * FROM managers";
		
		Statement statement = connection.createStatement();
		System.out.println("statement");
		ResultSet resultset = statement.executeQuery(query);
		
		while (resultset.next()) {
			
			Manager manager = new Manager();
			manager.setId(resultset.getInt("id"));
			manager.setEmail(resultset.getString("email"));
			manager.setName(resultset.getString("name"));
			manager.setPassword(resultset.getString("password"));
			managersList.add(manager);
		}
		statement.close();
		connection.close();
		return managersList;
		
	}

}
