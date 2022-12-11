package com.stackroute.feedback.service;

import java.util.List;

import com.stackroute.feedback.rabbitmqconsumeDTO.ClinicDTO;
import com.stackroute.feedback.rabbitmqconsumeDTO.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.feedback.entity.FeedbackPOJO;
import com.stackroute.feedback.repositry.FeedbackRepositry;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackRepositry feedbackrepo;


	FeedbackPOJO f=new FeedbackPOJO();
	@Override
	public String savefeedbackDetails(FeedbackPOJO feedback) {
		feedback.setClinicId(f.getClinicId());
		feedback.setClinicName(f.getClinicName());
		feedback.setDoctorId(f.getDoctorId());
		feedback.setDoctorName(f.getDoctorName());
		feedback.setUserEmail(f.getUserEmail());
		try {
			feedbackrepo.save(feedback);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
	public void saveClinicDetails(ClinicDTO clinicdetail) {
//		FeedbackPOJO f=new FeedbackPOJO();
		f.setClinicId(String.valueOf(clinicdetail.getClinicID()));
		f.setClinicName(clinicdetail.getClinicName());
		f.setDoctorId(clinicdetail.getDoctorId());
		f.setDoctorName(clinicdetail.getDoctorName());
		//feedbackrepo.save(f);
		
	}

	@Override
	public void saveEmailDetails(EmailDTO user) {
		//FeedbackPOJO f=new FeedbackPOJO();
		f.setUserEmail(user.getEmailId());
		feedbackrepo.save(f);

	}

}
