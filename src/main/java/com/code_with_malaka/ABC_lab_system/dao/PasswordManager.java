package com.code_with_malaka.ABC_lab_system.dao;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordManager {
	
	public String passwordHash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public String passwordUnhash(String password) {
//		Decoder decoder = Base64.getDecoder();
//		byte[] bytes = decoder.decode(password);
//		String unhashedPassword = new String(bytes);
//		return unhashedPassword;
		return password;
	}
	
	public boolean verify(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}
}
