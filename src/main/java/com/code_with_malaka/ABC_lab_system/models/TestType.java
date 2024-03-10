package com.code_with_malaka.ABC_lab_system.models;

public class TestType {

	private int id;
	private String name;
	private Double price;
	private int isActive;
	
	public TestType() {
		
	}
	
	public TestType(int id) {
		this.id = id;
	}

	public TestType(String name, Double price) {
		this.name = name;
		this.price = price;
	}
	
	public TestType(int id, String name, Double price, int isActive) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.isActive = isActive;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getisActive() {
		return isActive;
	}

	public void setisActive(int isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
