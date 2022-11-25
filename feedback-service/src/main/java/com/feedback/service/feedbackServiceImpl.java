package com.feedback.service;

import com.feedback.entity.feedback;
import com.feedback.repositry.feedbackRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class feedbackServiceImpl implements feedbackService{
@Autowired
feedbackRepositry fr;
    public List<feedback> getdata() {
   return fr.findAll();




    }
}
