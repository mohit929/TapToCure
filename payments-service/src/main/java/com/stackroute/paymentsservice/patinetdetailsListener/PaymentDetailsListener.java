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

	public static final String APPOINTMENT_QUEUE_FOR_PAYMENT_SERVICE= "APPOINTMENT_QUEUE_FOR_PAYMENT_SERVICE";
	
	@RabbitListener(queues=APPOINTMENT_QUEUE_FOR_PAYMENT_SERVICE)
	public void listener(Appointment appointment) {
	  
		paymentservice.getAppointment(appointment);
		
		System.out.println("messsage received");
	}
	

}
