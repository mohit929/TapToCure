package com.stackroute.paymentsservice.service;

import com.razorpay.Order;

public interface PaymentService {
	
	public String SavePaymentDetails(Order order);

}
