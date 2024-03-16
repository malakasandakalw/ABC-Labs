package com.code_with_malaka.ABC_lab_system.models;

public class Dashboard {

	int totalAppointmentsCount;
	int cancelledAppointmentsCount;
	int doneAppointmentsCount;
	int processingAppointmentsCount;
	int totalPatients;
	double totalEarning;
	
	public double getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(double totalEarning) {
		this.totalEarning = totalEarning;
	}
	public int getTotalAppointmentsCount() {
		return totalAppointmentsCount;
	}
	public void setTotalAppointmentsCount(int totalAppointmentsCount) {
		this.totalAppointmentsCount = totalAppointmentsCount;
	}
	public int getCancelledAppointmentsCount() {
		return cancelledAppointmentsCount;
	}
	public void setCancelledAppointmentsCount(int cancelledAppointmentsCount) {
		this.cancelledAppointmentsCount = cancelledAppointmentsCount;
	}
	public int getDoneAppointmentsCount() {
		return doneAppointmentsCount;
	}
	public void setDoneAppointmentsCount(int doneAppointmentsCount) {
		this.doneAppointmentsCount = doneAppointmentsCount;
	}
	public int getProcessingAppointmentsCount() {
		return processingAppointmentsCount;
	}
	public void setProcessingAppointmentsCount(int processingAppointmentsCount) {
		this.processingAppointmentsCount = processingAppointmentsCount;
	}
	public int getTotalPatients() {
		return totalPatients;
	}
	public void setTotalPatients(int totalPatients) {
		this.totalPatients = totalPatients;
	}
	
	public Dashboard(int totalAppointmentsCount, int cancelledAppointmentsCount, int doneAppointmentsCount,
			int processingAppointmentsCount, int totalPatients) {
		super();
		this.totalAppointmentsCount = totalAppointmentsCount;
		this.cancelledAppointmentsCount = cancelledAppointmentsCount;
		this.doneAppointmentsCount = doneAppointmentsCount;
		this.processingAppointmentsCount = processingAppointmentsCount;
		this.totalPatients = totalPatients;
	}
	public Dashboard() {
		// TODO Auto-generated constructor stub
	}
}
