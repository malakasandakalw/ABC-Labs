package com.code_with_malaka.ABC_lab_system.models;

public class FileUploadResponse {
	boolean isUploaded;
	TestResult testResult;
	
	public FileUploadResponse(boolean isUploaded, TestResult testResult) {
		this.isUploaded = isUploaded;
		this.testResult = testResult;
	}
	
	public FileUploadResponse() {
		// TODO Auto-generated constructor stub
	}

	public boolean isUploaded() {
		return isUploaded;
	}
	public void setUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}
	public TestResult getTestResult() {
		return testResult;
	}
	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
	
}
