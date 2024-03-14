package com.code_with_malaka.ABC_lab_system.services;

import java.sql.SQLException;

import com.code_with_malaka.ABC_lab_system.models.PaymentRecipt;

public interface PaymentReciptService {
	public boolean createPaymentRecipt(PaymentRecipt paymentRecipt, int appointmentId) throws ClassNotFoundException, SQLException;
	public PaymentRecipt getPaymentRecipt(int id) throws ClassNotFoundException, SQLException;
}
