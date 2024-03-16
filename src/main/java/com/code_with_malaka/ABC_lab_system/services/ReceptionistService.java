package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Receptionist;


public interface ReceptionistService {
	public boolean createReceptionist(Receptionist receptionist) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;
	public List<Receptionist> getAllReceptionists() throws ClassNotFoundException, SQLException;
	public Receptionist getSpecificReceptionist(int id);
	public Receptionist getSpecifcReceptionistByEmail(String email) throws ClassNotFoundException, SQLException;
	public boolean updateReceptionist(Receptionist receptionist);
	public boolean deleteReceptionist(int id);
}
