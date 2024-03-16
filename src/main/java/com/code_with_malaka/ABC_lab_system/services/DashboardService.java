package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.models.Dashboard;

public interface DashboardService {
	public Dashboard getDashboard() throws ClassNotFoundException, SQLException;
}
