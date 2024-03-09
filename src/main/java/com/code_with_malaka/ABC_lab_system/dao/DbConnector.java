package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnector {
	
	Connection getDbConnection() throws ClassNotFoundException, SQLException;
}
