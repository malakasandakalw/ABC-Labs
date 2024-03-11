package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;
import java.util.List;

import com.code_with_malaka.ABC_lab_system.dao.AppointmentTestsManager;
import com.code_with_malaka.ABC_lab_system.dao.AppointmentsManager;
import com.code_with_malaka.ABC_lab_system.models.AppointmentTest;

public class AppointmentTestServiceImpl implements AppointmentTestService {
	
	private static AppointmentTestService appointmentTestServiceObj;

	private AppointmentTestServiceImpl() {
		
	}

	public static synchronized AppointmentTestService getAppointmentTestServiceInstance() {
		if (appointmentTestServiceObj == null) {
			appointmentTestServiceObj = new AppointmentTestServiceImpl();
		}
		
		return appointmentTestServiceObj;
	}
	
	private AppointmentTestsManager getAppointmentTestsManager() {
		return new AppointmentTestsManager();
	}

	@Override
	public boolean createAppointmentTest(AppointmentTest appointmentTest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AppointmentTest> getAppointmentTestsByAppointmentId(int id) throws ClassNotFoundException, SQLException {
		return getAppointmentTestsManager().getAppointmentTestsByAppointmentId(id);
	}

	@Override
	public List<AppointmentTest> getAppointmentTestsByTechnicianId(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppointmentTest getSpecificAppointmentTest(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
