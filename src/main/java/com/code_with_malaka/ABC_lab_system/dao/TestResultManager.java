package com.code_with_malaka.ABC_lab_system.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;
import com.code_with_malaka.ABC_lab_system.models.CreateResponse;
import com.code_with_malaka.ABC_lab_system.models.FileUploadResponse;
import com.code_with_malaka.ABC_lab_system.models.TestResult;

@MultipartConfig(
		location = "C:\\Uploads",
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 11
		)

public class TestResultManager {
	public DbConnector getDbConnector() {
		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
		return factory.getDbConnector();
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConnector connector =  getDbConnector();
		return connector.getDbConnection();
	}
	
	public FileUploadResponse uploadFile(HttpServletRequest request) throws IOException, ServletException {
		FileUploadResponse result = new FileUploadResponse();
		
		try {
			Part part = request.getPart("file");
			String fileUrl = getFileName(part);
			part.write(fileUrl);
			
			TestResult testResult = new TestResult();
			testResult.setFileUrl(fileUrl);
			
			result.setTestResult(testResult);
			result.setUploaded(true);
		} catch (Exception e) {
			result.setTestResult(null);
			result.setUploaded(false);
		}
        return result;
	}
	
	public String getFileName(Part part) {
		String cd = part.getHeader("content-disposition");
		if (!cd.contains("filename=")) {
			return null;
		}
		
		int beginIndex = cd.indexOf("filename=") + 10;
		int endIndex = cd.length() - 1;
		
		return cd.substring(beginIndex, endIndex);
		
	}
	
	public CreateResponse createTestResult(TestResult testResult) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String query = "INSERT INTO test_results (appointment_test_id, test_result_file) VALUES (?, ?)";
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, testResult.getAppointmentTest().getId());
		ps.setString(2, testResult.getFileUrl());
		
		int result = ps.executeUpdate();		
		
		int testReultId = -1;
		ResultSet rs = ps.getGeneratedKeys(); 
		
		if(rs.next()) {
			testReultId = rs.getInt(1);
		}
		
		ps.close();
		connection.close();		
		
		CreateResponse response = new CreateResponse();
		response.setId(testReultId);
		response.setResult(result > 0);
		
		return response;
	}
	
	public CreateResponse updateTestResult(TestResult testResult) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String query = "UPDATE test_results SET test_result_file = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, testResult.getFileUrl());
		ps.setInt(2, testResult.getid());
		
		int result = ps.executeUpdate();
		ps.close();
		connection.close();		
		
		CreateResponse response = new CreateResponse();
		response.setId(testResult.getid());
		response.setResult(result > 0);
		
		return response;
	}
	
	public TestResult getSpecifcTestResultById(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		String query = "SELECT * FROM test_results WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		TestResult testResult = new TestResult();
		
		while(rs.next()) {
			testResult.setId(rs.getInt("id"));
			testResult.setFileUrl(rs.getString("test_result_file"));
			
			AppointmentTest appointmentTest = new AppointmentTest();
			appointmentTest.setId(rs.getInt("appointment_test_id"));
			
			testResult.setAppointmentTest(appointmentTest);
		}
		
		ps.close();
		connection.close();		
		return testResult;
		
	}
	
}
