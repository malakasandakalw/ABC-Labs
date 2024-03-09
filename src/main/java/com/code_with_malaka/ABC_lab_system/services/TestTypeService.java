package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.TestType;

public interface TestTypeService {
	public boolean createTestType(TestType testType);
	public List<TestType> getAllTestTypes() throws ClassNotFoundException, SQLException;
	public TestType getSpecificTestType(int id);
	public boolean updateTestType(TestType testType);
	public boolean deleteTestType(int id);
}
