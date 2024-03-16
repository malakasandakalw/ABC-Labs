package com.code_with_malaka.ABC_lab_system.models;

public class Manager extends User {
	
	public Manager() {
		
	}

	public Manager( int id, String name, String email, String password, String role) {
		super(id, name, email, password, role);
	}
	
	public Manager(String email) {
		super(email, "Manager");
	}

}
