package com.code_with_malaka.ABC_lab_system.models;

import java.util.Date;

public class Patient extends User {
	
	String contactNumber;
	Date dob;
	int age;
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
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
