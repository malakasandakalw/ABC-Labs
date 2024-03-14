package com.code_with_malaka.ABC_lab_system.models;

public class PaymentRecipt {
	
	int id;
	Double totalPrice;
	
	public PaymentRecipt(int id, Double totalPrice) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
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
	
}
