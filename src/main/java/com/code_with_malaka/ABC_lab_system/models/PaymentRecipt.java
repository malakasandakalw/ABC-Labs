package com.code_with_malaka.ABC_lab_system.models;

import java.sql.Timestamp;

public class PaymentRecipt {
	
	int id;
	Double totalPrice;
	Timestamp createdAt;
	
	public PaymentRecipt(int id, Double totalPrice, Timestamp createdAt) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
	}
	
	public PaymentRecipt() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp timestamp) {
		this.createdAt = timestamp;
	}
	
}
