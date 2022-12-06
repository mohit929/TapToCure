package com.stackroute.paymentsservice.patinetdetailsListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.rabbitmqconfig.RabbitMQConfig;

@Component
public class PaymentDetailsListener {
	
	
	@RabbitListener(queues=RabbitMQConfig.QUEUE)
	public void listener(PaymentDetailsPOJO paymentdetailspojo) {
		String currency = paymentdetailspojo.getCurrency();
		System.out.println(currency);
	}
	

}
