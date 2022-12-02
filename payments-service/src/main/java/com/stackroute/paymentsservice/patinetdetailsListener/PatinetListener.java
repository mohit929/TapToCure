package com.stackroute.paymentsservice.patinetdetailsListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.stackroute.paymentsservice.rabbitmqconfig.RabbitMQConfig;

@Component
public class PatinetListener {
	
	
	@RabbitListener(queues=RabbitMQConfig.QUEUE)
	public void listener() {
		
	}
	

}
