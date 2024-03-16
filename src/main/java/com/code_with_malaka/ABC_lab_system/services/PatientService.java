package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.models.Patient;

public interface PatientService {

	public boolean createPatient(Patient patient) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;
	public Patient getSpecificPatient(Patient patient) throws ClassNotFoundException, SQLException;
	public Patient getSpecificPatientById(int id);
	public Patient getSpecificPatientByEmail(String email) throws ClassNotFoundException, SQLException;
	public boolean updateManager(Patient patient);
}
