package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Receptionist;


public interface ReceptionistService {
	public boolean createReceptionist(Receptionist receptionist);
	public List<Receptionist> getAllReceptionists() throws ClassNotFoundException, SQLException;
	public Receptionist getSpecificReceptionist(int id);
	public boolean updateReceptionist(Receptionist receptionist);
	public boolean deleteReceptionist(int id);
}
