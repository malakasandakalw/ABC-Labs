package com.code_with_malaka.ABC_lab_system.models;

public class TestResult {
	int id;
	AppointmentTest appointmentTest;
	String fileUrl;
	
	public TestResult() {
		super();
	}
	
	public TestResult(AppointmentTest appointmentTest, String fileUrl) {
		super();
		this.appointmentTest = appointmentTest;
		this.fileUrl = fileUrl;
	}

	public TestResult(int id,AppointmentTest appointmentTest, String fileUrl) {
		super();
		this.id = id;
		this.appointmentTest = appointmentTest;
		this.fileUrl = fileUrl;
	}
	

	public AppointmentTest getAppointmentTest() {
		return appointmentTest;
	}


	public void setAppointmentTest(AppointmentTest appointmentTest) {
		this.appointmentTest = appointmentTest;
	}


	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public int getid() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
