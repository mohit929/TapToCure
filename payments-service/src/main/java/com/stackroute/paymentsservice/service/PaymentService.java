package com.stackroute.paymentsservice.service;

import java.util.Map;

import com.razorpay.Order;
import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.rabbitmqdto.Appointment;

public interface PaymentService {
	
	public Order creatPaymen(int amount ,String receiptid);
	
	public String SavePaymentDetails(Order order);

	public void updateSucessPayment(Map<String, String> data);

	public void getAppointment(Appointment appointment);
	

	

}
