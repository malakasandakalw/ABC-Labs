package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.TestType;

public interface TestTypeService {
	public boolean createTestType(TestType testType) throws ClassNotFoundException, SQLException;
	public List<TestType> getAllTestTypes() throws ClassNotFoundException, SQLException;
	public List<TestType> getTestTypesByTechnicianId(int id) throws ClassNotFoundException, SQLException;
	public TestType getSpecificTestType(int id) throws ClassNotFoundException, SQLException;
	public boolean updateTestType(TestType testType) throws ClassNotFoundException, SQLException;
	public boolean updateTestTypeStatus(int id, int status) throws ClassNotFoundException, SQLException;
	public boolean deleteTestType(int id) throws ClassNotFoundException, SQLException;
}
