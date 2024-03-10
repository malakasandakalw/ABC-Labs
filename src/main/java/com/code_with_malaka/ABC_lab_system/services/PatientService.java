package com.code_with_malaka.ABC_lab_system.services;

import com.code_with_malaka.ABC_lab_system.models.Patient;

public interface PatientService {

	public boolean createPatient(Patient patient);
	public Patient getSpecificManager(int id);
	public boolean updateManager(Patient patient);
}
