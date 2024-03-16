package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Technician;

public interface TechnicianService {
	public boolean createTechnician(Technician technician) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;
	public List<Technician> getAllTechnicians() throws ClassNotFoundException, SQLException;
	public Technician getSpecificTechnician(int id) throws ClassNotFoundException, SQLException;
	public Technician getSpecificTechnicianByEmail(String email) throws ClassNotFoundException, SQLException;
	public List<Technician> getTechniciansByTestTypeId(int id) throws ClassNotFoundException, SQLException;
	public boolean updateTechnician(Technician manager) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;
	public boolean deleteTechnician(int id) throws ClassNotFoundException, SQLException;
}
