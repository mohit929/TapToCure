package com.feedback.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.feedback.entity.feedback;

public interface feedbackRepositry extends MongoRepository<feedback, Integer> {

}
