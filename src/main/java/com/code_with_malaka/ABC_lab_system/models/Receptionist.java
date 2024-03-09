package com.code_with_malaka.ABC_lab_system.models;

public class Receptionist extends User{
	
	public Receptionist() {
		
	}

	public Receptionist( int id, String name, String email, String password, String role) {
		super(id, name, email, password, role);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateAccount(String name, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}
