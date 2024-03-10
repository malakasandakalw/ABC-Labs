package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.dao.CommonManager;
import com.code_with_malaka.ABC_lab_system.models.User;

public class CommonServiceImpl implements CommonService {
	private static CommonService commonServiceObj;
	
	private CommonServiceImpl() {
		
	}
	
	public static synchronized CommonService getCommonServiceInstance() {
		
		if(commonServiceObj == null) {
			commonServiceObj = new CommonServiceImpl();
		}
		
		return commonServiceObj;
	}
	
	private CommonManager getCommonManager() {
		return new CommonManager();
	}

	@Override
	public boolean checkUserExists(User user) throws ClassNotFoundException, SQLException {
		return getCommonManager().checkIfUserExists(user);
	}

}
