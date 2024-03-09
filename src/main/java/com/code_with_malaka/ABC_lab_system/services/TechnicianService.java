package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Technician;

public interface TechnicianService {
	public boolean createTechnician(Technician technician);
	public List<Technician> getAllTechnicians() throws ClassNotFoundException, SQLException;
	public Technician getSpecificTechnician(int id);
	public List<Technician> getTechniciansByTestTypeId(int id);
	public boolean updateTechnician(Technician manager);
	public boolean deleteTechnician(int id);
}
