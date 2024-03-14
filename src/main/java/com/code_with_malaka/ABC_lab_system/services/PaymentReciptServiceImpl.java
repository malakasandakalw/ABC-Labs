package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.dao.PaymentReciptsManager;
import com.code_with_malaka.ABC_lab_system.models.PaymentRecipt;

public class PaymentReciptServiceImpl implements PaymentReciptService {
	
	private static PaymentReciptServiceImpl paymentReciptServiceObj;
	
	public PaymentReciptServiceImpl() {
		
	}

	public static synchronized PaymentReciptServiceImpl getPaymentReciptServiceInstance() {
		if (paymentReciptServiceObj == null) {
			paymentReciptServiceObj = new PaymentReciptServiceImpl();
		}
		
		return paymentReciptServiceObj;
	}
	
	private PaymentReciptsManager getPaymentReciptsManager() {
		return new PaymentReciptsManager();
	}
	
	@Override
	public boolean createPaymentRecipt(PaymentRecipt paymentRecipt, int appointmentId)
			throws ClassNotFoundException, SQLException {
		return  getPaymentReciptsManager().createPaymentRecipt(paymentRecipt, appointmentId);
	}

	@Override
	public PaymentRecipt getPaymentRecipt(int id) throws ClassNotFoundException, SQLException {
		return getPaymentReciptsManager().getSpecificPaymentRecipt(id);
	}

}
