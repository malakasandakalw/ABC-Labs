package com.code_with_malaka.ABC_lab_system.dao;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class PasswordManager {
	
	public String passwordHash(String password) {
		Encoder encoder = Base64.getEncoder();
		String hashedPassword = encoder.encodeToString(password.getBytes());
		return hashedPassword;
	}
	
	public String passwordVerify(String password) {
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(password);
		String unhashedPassword = new String(bytes);
		return unhashedPassword;
	}
}
