package com.code_with_malaka.ABC_lab_system.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Appointment {
	private int id;
	private List<AppointmentTest> appointmentTests;
	private Double totalPrice;
	private Date date;
	private String status;
	private String details;
	private String contactNumber;
	private String email;
	private Timestamp createdAt;
	
	public Appointment(int id, Date date, String status, Double totalPrice) {
		this.id = id;
		this.date = date;
		this.status = status;
		this.totalPrice = totalPrice;
	}
	public Appointment() {
		// TODO Auto-generated constructor stub
	}
	public Appointment(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<AppointmentTest> getAppointmentTests() {
		return appointmentTests;
	}
	public void setAppointmentTests(List<AppointmentTest> appointmentTests) {
		this.appointmentTests = appointmentTests;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
