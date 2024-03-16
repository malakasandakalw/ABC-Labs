package com.code_with_malaka.ABC_lab_system.models;

public class Receptionist extends User{
	private int isActive;
	private int isChangedDefaultPassword;
	
	public Receptionist() {
		
	}

	public Receptionist( int id, String name, String email, String password, String role) {
		super(id, name, email, password, role);
		// TODO Auto-generated constructor stub
	}
	
	public Receptionist(String email) {
		super(email, "Receptionist");
		// TODO Auto-generated constructor stub
	}
	

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsChangedDefaultPassword() {
		return isChangedDefaultPassword;
	}

	public void setIsChangedDefaultPassword(int isChangedDefaultPassword) {
		this.isChangedDefaultPassword = isChangedDefaultPassword;
	}

}
