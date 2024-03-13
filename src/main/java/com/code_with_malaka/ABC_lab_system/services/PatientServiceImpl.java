package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.dao.AppointmentsManager;
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
	public boolean createPatient(Patient patient) throws ClassNotFoundException, SQLException {
		return getPatientsManager().createPatient(patient);
	}

	@Override
	public Patient getSpecificPatientById(int id) {
		return null;
	}
	
	public Patient getSpecificPatientByEmail(String email) {
		return null;
	}

	@Override
	public boolean updateManager(Patient patient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Patient getSpecificPatient(Patient patient) throws ClassNotFoundException, SQLException {
		return getPatientsManager().getSpecificPatient(patient);
	}

}
