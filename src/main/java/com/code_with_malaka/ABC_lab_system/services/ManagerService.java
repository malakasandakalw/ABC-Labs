package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Manager;

public interface ManagerService {
	public boolean createManager(Manager manager);
	public List<Manager> getAllManagers() throws ClassNotFoundException, SQLException;
	public Manager getSpecificManager(int id);
	public boolean updateManager(Manager manager);
	public boolean deleteManager(int id);
	
}