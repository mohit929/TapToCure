package com.stackroute.feedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.feedback.entity.FeedbackPOJO;
import com.stackroute.feedback.rabbitmqconsumeDTO.ClinicDetail;
import com.stackroute.feedback.rabbitmqconsumeDTO.User;
import com.stackroute.feedback.repositry.FeedbackRepositry;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackRepositry feedbackrepo;
	

	@Override
	public String savefeedbackDetails(FeedbackPOJO feedback) {
		 feedbackrepo.save(feedback);
		return "Details Created Sucessfully";
	}

	@Override
	public List<FeedbackPOJO> getfeedbackDetails() {
		List<FeedbackPOJO> findAll = feedbackrepo.findAll();
		return findAll;
	}

	@Override
	public String deletefeedbackDetailsbyId(int commentId) {
	feedbackrepo.deleteById(commentId);
		return "deleted Sucessfully";
	}

	@Override
	public String updatefeedbackDetails(FeedbackPOJO feedback) {
		 feedbackrepo.save(feedback);
			return "Details updated Sucessfully";
	}

	@Override
	public void saveClinicDetails(ClinicDetail clinicdetail) {
		FeedbackPOJO f=new FeedbackPOJO();
		f.setClinicId(String.valueOf(clinicdetail.getClinicID()));
		f.setClinicName(clinicdetail.getClinicName());
		f.setDoctorId(clinicdetail.getDoctorId());
		f.setDoctorName(clinicdetail.getDoctorName());
		feedbackrepo.save(f);
		
	}

	@Override
	public void saveEmailDetails(User user) {
		FeedbackPOJO f=new FeedbackPOJO();
		f.setUserEmail(user.getEmailId());
		feedbackrepo.save(f);

	}

}
