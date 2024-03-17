package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.ManagersManager;
import com.code_with_malaka.ABC_lab_system.models.Manager;

public class ManagerServiceImpl implements ManagerService {
	
	private static ManagerService managerServiceObj;
	
	public ManagerServiceImpl() {
		
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
	public boolean createManager(Manager manager) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		return getManagersManager().addManager(manager);
	}

	@Override
	public List<Manager> getAllManagers() throws ClassNotFoundException, SQLException {
		return getManagersManager().getAllManagers();
	}

	@Override
	public Manager getSpecificManager(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return getManagersManager().getSpecificManagerById(id);
	}
	
	public Manager getSpecificManagerByEmail(String email) throws ClassNotFoundException, SQLException {
		return getManagersManager().getSpecificManagerByEmail(email);
	}

	@Override
	public boolean updateManager(Manager manager) throws ClassNotFoundException, SQLException {
		return getManagersManager().updateManager(manager);
	}

	@Override
	public boolean deleteManager(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
