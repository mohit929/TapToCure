package com.stackroute.feedback.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.feedback.entity.FeedbackPOJO;

public interface FeedbackRepositry extends MongoRepository<FeedbackPOJO, Integer> {

}
