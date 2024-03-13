package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.models.Appointment;

public interface AppointmentService {
	public boolean createAppointment(Appointment appointment, int patientId) throws ClassNotFoundException, SQLException;
	public List<Appointment> getAllAppointments() throws ClassNotFoundException, SQLException;
	public List<Appointment> getAppointmentsByPatient(int id) throws ClassNotFoundException, SQLException;
	public List<Appointment> getAppointmentsByTechnician(int id) throws ClassNotFoundException, SQLException;
	public Appointment getSpecificAppointment(int id) throws ClassNotFoundException, SQLException;
	public boolean updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException;
}
