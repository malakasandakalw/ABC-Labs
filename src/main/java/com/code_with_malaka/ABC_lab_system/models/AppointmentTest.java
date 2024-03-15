package com.code_with_malaka.ABC_lab_system.models;

public class AppointmentTest {
	
	private int id;
	private TestType testType;
	private Technician technician;
	private String status;
	private TestResult testResult;
	private Appointment appointment;
	private Patient patient;
	
	public AppointmentTest(int id, Technician technician, String status) {
		this.id = id;
		this.technician = technician;
		this.status = status;
	}
	public AppointmentTest() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TestType getTestType() {
		return testType;
	}
	public void setTestType(TestType testType) {
		this.testType = testType;
	}
	public Technician getTechnician() {
		return technician;
	}
	public void setTechnician(Technician technician) {
		this.technician = technician;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TestResult getTestResult() {
		return testResult;
	}
	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
