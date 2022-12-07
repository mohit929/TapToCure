package com.stackroute.paymentsservice.patinetdetailsListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.stackroute.paymentsservice.dto.Appointment;
import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.rabbitmqconfig.RabbitMQConfig;

@Component
public class PaymentDetailsListener {
	
	
	@RabbitListener(queues=RabbitMQConfig.QUEUE)
	public void listener(Appointment appointment) {
	
		System.out.println(appointment);
		System.out.println("messsage received");
	}
	

}
