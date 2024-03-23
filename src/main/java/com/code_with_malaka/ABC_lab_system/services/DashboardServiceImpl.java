package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.dao.DashboardManager;
import com.code_with_malaka.ABC_lab_system.models.Dashboard;
import com.code_with_malaka.ABC_lab_system.models.DurationFilter;

public class DashboardServiceImpl implements DashboardService {

	private static DashboardServiceImpl dashboardServiceObj;
	
	public DashboardServiceImpl() {
		
	}

	public static synchronized DashboardServiceImpl getDashboardServiceInstance() {
		if (dashboardServiceObj == null) {
			dashboardServiceObj = new DashboardServiceImpl();
		}
		
		return dashboardServiceObj;
	}
	
	private DashboardManager getDashboardManager() {
		return new DashboardManager();
	}
	

	@Override
	public Dashboard getDashboard() throws ClassNotFoundException, SQLException {
		return getDashboardManager().getDashboard();
	}
	
	public Dashboard getDashboardByDates(DurationFilter duration) throws ClassNotFoundException, SQLException {
		return getDashboardManager().getDashboardByDates(duration);
	}

	

}
