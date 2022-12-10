package com.stackroute.clinicservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class  Config {

    // for feedback service -START
    public static final String CLINIC_QUEUE_FOR_FEEDBACK_SERVICE="CLINIC_QUEUE_FOR_FEEDBACK_SERVICE";
    public static final String CLINIC_KEY_FOR_FEEDBACK_SERVICE="CLINIC_KEY_FOR_FEEDBACK_SERVICE";
    public static final String CLINIC_EXCHANGE_FOR_FEEDBACK_SERVICE="CLINIC_EXCHANGE_FOR_FEEDBACK_SERVICE";

    @Bean
    public TopicExchange topicExchangeForFeedbackService(){
        return new TopicExchange(CLINIC_EXCHANGE_FOR_FEEDBACK_SERVICE);
    }
    @Bean
    public Queue clinicQueueForFeedbackService(){
        System.out.println("clinicQueueForFeedbackService");
        return new Queue(CLINIC_QUEUE_FOR_FEEDBACK_SERVICE);
    }
    @Bean
    public Binding bindingClinicQueueForFeedbackService(){
        System.out.println("bindingClinicQueueForFeedbackService");
        return BindingBuilder.bind(clinicQueueForFeedbackService())
                .to(topicExchangeForFeedbackService()).with(CLINIC_KEY_FOR_FEEDBACK_SERVICE);
    }
    // for feedback service -END

    public static final String C_Queue="Clinic_Q";
    public static final String EXCHANGE="Clinic_Exchange";
    public static final String Clinic_Key="Clinic_Key";
    @Bean
    public Queue cqueue(){
        return new Queue(C_Queue);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding bindingCq(){
        return BindingBuilder.bind(cqueue()).to(topicExchange()).with(Clinic_Key);
    }
    @Bean
    public Jackson2JsonMessageConverter converter(){
        return  new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory conn){
        final RabbitTemplate rabbitTemplate=new RabbitTemplate(conn);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}