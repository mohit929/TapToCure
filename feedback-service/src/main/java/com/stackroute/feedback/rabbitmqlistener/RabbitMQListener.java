package com.stackroute.feedback.rabbitmqlistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.feedback.rabbitconfig.FeedbackRabbitMQConfi;
import com.stackroute.feedback.rabbitmqconsumeDTO.ClinicDetail;
import com.stackroute.feedback.rabbitmqconsumeDTO.User;
import com.stackroute.feedback.service.FeedbackService;

@Component
public class RabbitMQListener {
	
	@Autowired
	private FeedbackService feedbackservice;
	
    @RabbitListener(queues="Clinic_Q")
	public void listener(ClinicDetail clinicdetail) {
	
		feedbackservice.saveClinicDetails(clinicdetail);
		System.out.println("messsage received");
	}
	

	
//	@RabbitListener(queues="EMAIL_Q")
//	public void listener(User user) {
//
//		feedbackservice.saveEmailDetails(user);
//		System.out.println("messsage received");
//	}
	
}
