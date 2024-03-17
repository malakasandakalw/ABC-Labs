package com.code_with_malaka.ABC_lab_system.models;

import java.util.Date;

public class Patient extends User {
	
	String contactNumber;
	Date dob;
	int age;
	String gender;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Patient(String email) {
		super(email, "Patient");
	}
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

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

}
