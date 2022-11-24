package com.stackroute.paymentsservice.service;

import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.repositry.PaymentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.razorpay.Order;

@Service
public class PaymentServiceImpl implements PaymentService {

	
	@Autowired
	private PaymentRepositry paymentrepo;
	
	@Override
	public void SavePaymentDetails(Order order) {
		PaymentDetailsPOJO p=new PaymentDetailsPOJO();
        p.setRazorOrderId(order.get("id"));
        p.setAmount(order.get("amount"));
        p.setCurrency(order.get("currency"));
        p.setReceiptNumber(order.get("receipt"));
        p.setPatientId(1);
        p.setPatientName("Need to Enter Patient name");
        p.setPatientEmail("Enter Patient Email");
        paymentrepo.save(p);
	}

}
