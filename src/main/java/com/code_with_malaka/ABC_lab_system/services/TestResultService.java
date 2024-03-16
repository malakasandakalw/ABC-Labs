package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.TestResult;


public interface TestResultService {

	public boolean creatTestResult(TestResult testResult) throws ClassNotFoundException, SQLException;
	public List<TestResult> getAllTestResult() throws ClassNotFoundException, SQLException;
	public TestResult getSpecificTestResultById(int id) throws ClassNotFoundException, SQLException;
}


