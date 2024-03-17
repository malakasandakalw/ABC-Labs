package com.code_with_malaka.ABC_lab_system.models;

public class Manager extends User {
	private int isActive;
	private int isChangedDefaultPassword;
	
	public int getIsChangedDefaultPassword() {
		return isChangedDefaultPassword;
	}

	public void setIsChangedDefaultPassword(int isChangedDefaultPassword) {
		this.isChangedDefaultPassword = isChangedDefaultPassword;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Manager() {
		
	}

	public Manager( int id, String name, String email, String password, String role) {
		super(id, name, email, password, role);
	}
	
	public Manager(String email) {
		super(email, "Manager");
	}

}
