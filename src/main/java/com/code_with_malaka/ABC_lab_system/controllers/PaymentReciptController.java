package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code_with_malaka.ABC_lab_system.models.PaymentRecipt;
import com.code_with_malaka.ABC_lab_system.services.PaymentReciptServiceImpl;


public class PaymentReciptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PaymentReciptController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null && type.equals("download")) {
			downloadPaymentRecipt(request, response, "");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void downloadPaymentRecipt(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		PaymentReciptServiceImpl paymentReciptService = PaymentReciptServiceImpl.getPaymentReciptServiceInstance();
		PaymentRecipt paymentRecipt;
		int id = Integer.parseInt(request.getParameter("appointment_id"));
		
		try {
			paymentRecipt = paymentReciptService.getPaymentRecipt(id);
			message = "Payment receipt id " + paymentRecipt.getId() + " with total price (Rs.)" + paymentRecipt.getTotalPrice() + " was created as PDF and download.";
			System.out.println(message);
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("message", message);
		
    	RequestDispatcher rd = request.getRequestDispatcher("patients?appointment_id="+ id +"&type=get-specific-appointment");
    	rd.forward(request, response);
		
	}

}
