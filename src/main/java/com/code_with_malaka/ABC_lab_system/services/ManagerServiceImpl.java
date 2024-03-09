package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.ManagersManager;
import com.code_with_malaka.ABC_lab_system.models.Manager;

public class ManagerServiceImpl implements ManagerService {
	
	private static ManagerService managerServiceObj;
	
	private ManagerServiceImpl() {
		
	}
	
	public static synchronized ManagerService getManagerServiceInstance() {
		
		if(managerServiceObj == null) {
			managerServiceObj = new ManagerServiceImpl();
		}
		
		return managerServiceObj;
	}
	
	private ManagersManager getManagersManager() {
		return new ManagersManager();
	}
	
	
	@Override
	public boolean createManager(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Manager> getAllManagers() throws ClassNotFoundException, SQLException {
		return getManagersManager().getAllManagers();
	}

	@Override
	public Manager getSpecificManager(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateManager(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteManager(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
