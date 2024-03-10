package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;

public interface AppointmentTestService {
	public boolean createAppointmentTest(AppointmentTest appointmentTest);
	public List<AppointmentTest> getAppointmentTestsByAppointmentId(int id) throws ClassNotFoundException, SQLException;
	public List<AppointmentTest> getAppointmentTestsByTechnicianId(int id) throws ClassNotFoundException, SQLException;
	public AppointmentTest getSpecificAppointmentTest(int id) throws ClassNotFoundException, SQLException;
}
