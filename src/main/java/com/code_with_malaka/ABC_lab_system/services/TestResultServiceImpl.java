package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.TestResultManager;
import com.code_with_malaka.ABC_lab_system.models.TestResult;

public class TestResultServiceImpl implements TestResultService {
	
	private static TestResultServiceImpl tesResultServiceObj;
	
	public TestResultServiceImpl() {
		
	}

	public static synchronized TestResultServiceImpl getTestResultServiceImpl() {
		if (tesResultServiceObj == null) {
			tesResultServiceObj = new TestResultServiceImpl();
		}
		
		return tesResultServiceObj;
	}
	
	private TestResultManager getTestResultManager() {
		return new TestResultManager();
	}
	

	@Override
	public boolean creatTestResult(TestResult testResult) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TestResult> getAllTestResult() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestResult getSpecificTestResultById(int id) throws ClassNotFoundException, SQLException {
		return getTestResultManager().getSpecifcTestResultById(id);
	}

}
