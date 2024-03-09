package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.ReceptionistsManager;
import com.code_with_malaka.ABC_lab_system.models.Receptionist;

public class ReceptionistServiceImpl implements ReceptionistService {
	
	private static ReceptionistService receptionistServiceObj;
	
	private ReceptionistServiceImpl() {
		
	}

	public static synchronized ReceptionistService getReceptionistServiceInstance() {
		if (receptionistServiceObj == null) {
			receptionistServiceObj = new ReceptionistServiceImpl();
		}
		
		return receptionistServiceObj;
	}
	
	private ReceptionistsManager getReceptionistsManager() {
		return new ReceptionistsManager();
	}
	
	@Override
	public boolean createReceptionist(Receptionist receptionist) {
		return false;
	}

	@Override
	public List<Receptionist> getAllReceptionists() throws ClassNotFoundException, SQLException {
		return getReceptionistsManager().getAllReceptionists();
	}

	@Override
	public Receptionist getSpecificReceptionist(int id) {
		return null;
	}

	@Override
	public boolean updateReceptionist(Receptionist receptionist) {
		return false;
	}

	@Override
	public boolean deleteReceptionist(int id) {
		return false;
	}

}
