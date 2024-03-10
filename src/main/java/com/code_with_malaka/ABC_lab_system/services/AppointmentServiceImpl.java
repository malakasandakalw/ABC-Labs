package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.AppointmentsManager;
import com.code_with_malaka.ABC_lab_system.models.Appointment;

public class AppointmentServiceImpl implements AppointmentService {
	
	private static AppointmentService appointmentServiceObj;
	
	private AppointmentServiceImpl() {
		
	}

	public static synchronized AppointmentService getAppointmentServiceInstance() {
		if (appointmentServiceObj == null) {
			appointmentServiceObj = new AppointmentServiceImpl();
		}
		
		return appointmentServiceObj;
	}
	
	private AppointmentsManager getAppointmentManager() {
		return new AppointmentsManager();
	}

	@Override
	public boolean createAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		return getAppointmentManager().createAppointment(appointment);
	}

	@Override
	public List<Appointment> getAllAppointments() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByTechnician(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment getSpecificAppointments(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return false;
	}

}
