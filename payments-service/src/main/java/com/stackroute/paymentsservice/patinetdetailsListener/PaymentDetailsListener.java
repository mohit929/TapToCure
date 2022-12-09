package com.stackroute.paymentsservice.patinetdetailsListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.paymentsservice.rabbitmqconfig.RabbitMQConfig;
import com.stackroute.paymentsservice.rabbitmqdto.Appointment;
import com.stackroute.paymentsservice.service.PaymentService;


@Component
public class PaymentDetailsListener {
	
	@Autowired
	public PaymentService paymentservice;
	
	@RabbitListener(queues=RabbitMQConfig.QUEUE)
	public void listener(Appointment appointment) {
	  
		paymentservice.getAppointment(appointment);
		
		System.out.println("messsage received");
	}
	

}
