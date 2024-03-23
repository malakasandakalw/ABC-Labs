package com.code_with_malaka.ABC_lab_system.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.dao.PatientsManager;
import com.code_with_malaka.ABC_lab_system.models.Patient;

public class PatientServiceImpl implements PatientService {

	private static PatientService patientsServiceObj;
	
	public PatientServiceImpl() {
		
	}

	public static synchronized PatientService getPatientsServiceInstance() {
		if (patientsServiceObj == null) {
			patientsServiceObj = new PatientServiceImpl();
		}
		
		return patientsServiceObj;
	}
	
	private PatientsManager getPatientsManager() {
		return new PatientsManager();
	}
	
	@Override
	public boolean createPatient(Patient patient) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		return getPatientsManager().createPatient(patient);
	}

	@Override
	public Patient getSpecificPatientById(int id) throws ClassNotFoundException, SQLException {
		return getPatientsManager().getSpecificPatient(id);
	}
	
	public Patient getSpecificPatientByEmail(String email) throws ClassNotFoundException, SQLException {
		return getPatientsManager().getSpecificPatientByEmail(email);
	}

	@Override
	public boolean updatePatient(Patient patient) throws ClassNotFoundException, SQLException {
		return getPatientsManager().updatePatient(patient);
	}

}
