package com.code_with_malaka.ABC_lab_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectorImpl implements DbConnector {

	
	public Connection getDbConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/abc_labs";
		String userName = "root";
		String password = "bandarawela";
		
		Connection connection = DriverManager.getConnection(url, userName, password);
		return connection;
	}

	
}