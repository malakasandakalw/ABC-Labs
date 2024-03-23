package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import com.code_with_malaka.ABC_lab_system.dao.PasswordManager;
import com.code_with_malaka.ABC_lab_system.dao.ReceptionistsManager;
import com.code_with_malaka.ABC_lab_system.models.Receptionist;

public class ReceptionistServiceImpl implements ReceptionistService {
	
	private static ReceptionistService receptionistServiceObj;
	
	public ReceptionistServiceImpl() {
		
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
	public boolean createReceptionist(Receptionist receptionist) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		return getReceptionistsManager().createReceptionist(receptionist);
	}

	@Override
	public List<Receptionist> getAllReceptionists() throws ClassNotFoundException, SQLException {
		return getReceptionistsManager().getAllReceptionists();
	}

	@Override
	public Receptionist getSpecificReceptionist(int id) throws ClassNotFoundException, SQLException {
		return getReceptionistsManager().getSpecifcReceptionistById(id);
	}
	
	public Receptionist getSpecifcReceptionistByEmail(String email) throws ClassNotFoundException, SQLException {
		return getReceptionistsManager().getSpecifcReceptionistByEmail(email);
	}

	@Override
	public boolean updateReceptionist(Receptionist receptionist) throws ClassNotFoundException, SQLException {
		return getReceptionistsManager().updateReceptionist(receptionist);
	}
	
	public boolean updatePassword(int id, String password ) throws ClassNotFoundException, SQLException {
		return getReceptionistsManager().updatePassword(id, password);
	}

	@Override
	public boolean deleteReceptionist(int id) {
		return false;
	}

}
