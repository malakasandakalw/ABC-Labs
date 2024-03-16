package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.TechniciansManager;
import com.code_with_malaka.ABC_lab_system.models.Technician;

public class TechnicianServiceImpl implements TechnicianService {

private static TechnicianService technicianServiceObj;
	
	public TechnicianServiceImpl() {
		
	}
	
	public static synchronized TechnicianService getTechnicianServiceInstance() {
		
		if(technicianServiceObj == null) {
			technicianServiceObj = new TechnicianServiceImpl();
		}
		
		return technicianServiceObj;
	}
	
	private TechniciansManager getTechniciansManager() {
		return new TechniciansManager();
	}
	
	@Override
	public boolean createTechnician(Technician technician) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		return getTechniciansManager().createTechnician(technician);
	}

	@Override
	public List<Technician> getAllTechnicians() throws ClassNotFoundException, SQLException{
		return getTechniciansManager().getAllTechnicians();
	}

	@Override
	public Technician getSpecificTechnician(int id) throws ClassNotFoundException, SQLException {
		return getTechniciansManager().getSpecificTechnician(id);
	}
	
	public Technician getSpecificTechnicianByEmail(String email) throws ClassNotFoundException, SQLException {
		return getTechniciansManager().getSpecificTechnicianByEmail(email);
	}

	@Override
	public List<Technician> getTechniciansByTestTypeId(int id) throws ClassNotFoundException, SQLException {
		return getTechniciansManager().getTechniciansByTestType(id);
	}

	@Override
	public boolean updateTechnician(Technician technician) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		return getTechniciansManager().updateTechnician(technician);
	}

	@Override
	public boolean deleteTechnician(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
