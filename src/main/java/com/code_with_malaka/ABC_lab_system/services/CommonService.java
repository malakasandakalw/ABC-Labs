package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.models.User;

public interface CommonService {
	public boolean checkUserExists(User user) throws ClassNotFoundException, SQLException;
}
