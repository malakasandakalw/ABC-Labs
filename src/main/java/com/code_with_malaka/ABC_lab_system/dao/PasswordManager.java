package com.code_with_malaka.ABC_lab_system.dao;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
	
	private static PasswordManager passwordManagerObj;
	
	public PasswordManager() {
		
	}

	public static synchronized PasswordManager getPasswordManagerInstance() {
		if (passwordManagerObj == null) {
			passwordManagerObj = new PasswordManager();
		}
		
		return passwordManagerObj;
	}
	
	
	public String passwordHash(String password) {
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		return passwordHash;
	}
	
	public boolean verify(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}
}
