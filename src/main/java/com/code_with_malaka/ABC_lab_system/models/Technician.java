package com.code_with_malaka.ABC_lab_system.models;

import java.util.List;

public class Technician extends User{
	
	private List<TestType> specificTestField;
	private int isActive;
	private int isChangedDefaultPassword;

	public Technician() {
		
	}
	
	public Technician(String name, String email, String password, String role, List<TestType> specificTestField) {
		this.specificTestField = specificTestField;
	}

	public Technician( int id, String name, String email, String password, String role, List<TestType> specificTestField) {
		super(id, name , email, password, role);
		this.specificTestField = specificTestField;
	}
	
	public List<TestType> getSpecificTestField() {
		return specificTestField;
	}

	public void setSpecificTestField(List<TestType> specificTestField) {
		this.specificTestField = specificTestField;
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
