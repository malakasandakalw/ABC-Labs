package com.code_with_malaka.ABC_lab_system.models;

public class Technician extends User{
	
	private int specificTestField;
	
	public Technician() {
		
	}

	public Technician( int id, String name, String email, String password, String role, int specificTestField) {
		super(id, name , email, password, role);
		this.specificTestField = specificTestField;
	}
	
	public int getSpecificTestField() {
		return specificTestField;
	}

	public void setSpecificTestField(int specificTestField) {
		this.specificTestField = specificTestField;
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
