package com.stackroute.feedback.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.feedback.entity.FeedbackPOJO;
import com.stackroute.feedback.repositry.FeedbackRepositry;
import com.stackroute.feedback.service.FeedbackService;


@RestController
public class FeedbackController {
	

	@Autowired
	private FeedbackService feedbackservice;
	
	
	@PostMapping("/save")
	public String saveFeedback(@RequestBody FeedbackPOJO feedbackpojo) {
		
		String savefeedbackDetails = feedbackservice.savefeedbackDetails(feedbackpojo);
		return savefeedbackDetails ;
	}
	
	
	@GetMapping("/get")
	public  List<FeedbackPOJO> getFeedback() {
		List<FeedbackPOJO> getfeedbackDetails = feedbackservice.getfeedbackDetails();
		return getfeedbackDetails;
	}
	
	@DeleteMapping("/delete/{commentId}")
	public  String  deleteFeedbackbyId(@PathVariable int commentId) {
		String deletefeedbackDetailsbyId = feedbackservice.deletefeedbackDetailsbyId(commentId);
	
		return deletefeedbackDetailsbyId;
	}
	
	@PutMapping("/update")
	public String updateFeedback(@RequestBody FeedbackPOJO feedbackpojo) {
		String updatefeedbackDetails = feedbackservice.updatefeedbackDetails(feedbackpojo);
		return updatefeedbackDetails;
	}
	
	
	
	

}
