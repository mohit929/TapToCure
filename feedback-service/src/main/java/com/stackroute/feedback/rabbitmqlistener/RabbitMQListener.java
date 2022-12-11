package com.stackroute.feedback.rabbitmqlistener;

import com.stackroute.feedback.rabbitmqconsumeDTO.ClinicDTO;
import com.stackroute.feedback.rabbitmqconsumeDTO.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.feedback.service.FeedbackService;

@Component
public class RabbitMQListener {
	
	@Autowired
	private FeedbackService feedbackservice;

	public static final String CLINIC_QUEUE_FOR_FEEDBACK_SERVICE="CLINIC_QUEUE_FOR_FEEDBACK_SERVICE";
	public static final String EMAIL_QUEUE_FOR_FEEDBACK_SERVICE = "EMAIL_QUEUE_FOR_FEEDBACK_SERVICE";
    @RabbitListener(queues=CLINIC_QUEUE_FOR_FEEDBACK_SERVICE)
	public void listener(ClinicDTO clinicdetail) {
	
		feedbackservice.saveClinicDetails(clinicdetail);
		System.out.println("messsage received");
	}
	

	
	@RabbitListener(queues=EMAIL_QUEUE_FOR_FEEDBACK_SERVICE)
	public void listener(EmailDTO user) {

		feedbackservice.saveEmailDetails(user);
		System.out.println("messsage received");
	}
	
}
