package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Manager;

public interface ManagerService {
	public boolean createManager(Manager manager) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;
	public List<Manager> getAllManagers() throws ClassNotFoundException, SQLException;
	public Manager getSpecificManager(int id);
	public Manager getSpecificManagerByEmail(String email) throws ClassNotFoundException, SQLException;
	public boolean updateManager(Manager manager);
	public boolean deleteManager(int id);
	
}