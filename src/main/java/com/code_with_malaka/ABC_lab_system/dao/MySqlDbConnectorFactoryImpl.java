package com.code_with_malaka.ABC_lab_system.dao;

public class MySqlDbConnectorFactoryImpl implements DbConnectorFactory{

	@Override
	public DbConnector getDbConnector() {
		
		return new MySqlConnectorImpl();
	}
	
}
