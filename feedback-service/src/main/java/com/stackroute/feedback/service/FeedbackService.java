package com.stackroute.feedback.service;

import java.util.List;

import com.stackroute.feedback.entity.FeedbackPOJO;
import com.stackroute.feedback.rabbitmqconsumeDTO.ClinicDetail;
import com.stackroute.feedback.rabbitmqconsumeDTO.User;

public interface FeedbackService {
	
	public String savefeedbackDetails(FeedbackPOJO feedback);
	public List<FeedbackPOJO> getfeedbackDetails();
	public String deletefeedbackDetailsbyId(int commentId);
	public String updatefeedbackDetails(FeedbackPOJO feedback);
	public void saveClinicDetails(ClinicDetail clinicdetail);
	public void saveEmailDetails(User user);
}

