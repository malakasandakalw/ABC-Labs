package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.TestTypeManager;
import com.code_with_malaka.ABC_lab_system.models.TestType;

public class TestTypeServiceImpl implements TestTypeService {
	
	private static TestTypeService testTypeServiceObj;
	
	private TestTypeServiceImpl() {
		
	}

	public static synchronized TestTypeService getTestTypeServiceInstance() {
		if (testTypeServiceObj == null) {
			testTypeServiceObj = new TestTypeServiceImpl();
		}
		
		return testTypeServiceObj;
	}
	
	private TestTypeManager getTestTypeManager() {
		return new TestTypeManager();
	}

	@Override
	public boolean createTestType(TestType testType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TestType> getAllTestTypes() throws ClassNotFoundException, SQLException {
		return getTestTypeManager().getAllTestTypes();
	}

	@Override
	public TestType getSpecificTestType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTestType(TestType testType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTestType(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
