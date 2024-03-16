package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.AppointmentsManager;
import com.code_with_malaka.ABC_lab_system.models.Appointment;

public class AppointmentServiceImpl implements AppointmentService {
	
	private static AppointmentServiceImpl appointmentServiceObj;
	
	public AppointmentServiceImpl() {
		
	}

	public static synchronized AppointmentServiceImpl getAppointmentServiceInstance() {
		if (appointmentServiceObj == null) {
			appointmentServiceObj = new AppointmentServiceImpl();
		}
		
		return appointmentServiceObj;
	}
	
	private AppointmentsManager getAppointmentManager() {
		return new AppointmentsManager();
	}

	@Override
	public boolean createAppointment(Appointment appointment, int patientId) throws ClassNotFoundException, SQLException {
		return getAppointmentManager().createAppointment(appointment, patientId);
	}

	@Override
	public List<Appointment> getAllAppointments() throws ClassNotFoundException, SQLException {
		return getAppointmentManager().getAllAppointments();
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(int id) throws ClassNotFoundException, SQLException {
		return getAppointmentManager().getAllAppointmentsByPatient(id);
	}

	@Override
	public List<Appointment> getAppointmentsByTechnician(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment getSpecificAppointment(int id) throws ClassNotFoundException, SQLException {
		return getAppointmentManager().getSpecificAppointment(id);
	}

	@Override
	public boolean updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		return getAppointmentManager().updateAppointment(appointment);
	}
	
	public boolean updateAppointmentStatus(Appointment appointment) throws ClassNotFoundException, SQLException {
		return getAppointmentManager().updateAppointmentStatus(appointment);
	}
	

}
