package com.code_with_malaka.ABC_lab_system.dao;


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
		return password;
	}
	
	public boolean verify(String password, String hashedPassword) {
		return password.equals(hashedPassword);
	}
}
