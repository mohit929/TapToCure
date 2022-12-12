package com.stackroute.emailservice.rabbitmq.configuration;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;



@Configuration
public class MessageConfiguration {
    Logger logger = Logger.getLogger(MessageConfiguration.class.getSimpleName());
    // new  code

    public static final String A_QUEUE = "APPOINTMENT_QUEUE";
    public static final String E_QUEUE = "EMAIL_Q";
    public static final String O_QUEUE = "OTP_Q";

    public static final String A_KEY = "APPOINTMENT_KEY";

    public static final String EXCHANGE = "EXCHANGE";





    @Bean
    public Queue aQueue() {
        logger.info("queue created: "+A_QUEUE);

        return new Queue(A_QUEUE);
    }
    @Bean
    public Queue eQueue() {
        logger.info("queue created: "+E_QUEUE);

        return new Queue(E_QUEUE);
    }
    @Bean
    public Queue oQueue() {
        logger.info("queue created: "+O_QUEUE);

        return new Queue(O_QUEUE);
    }


    @Bean
    public TopicExchange topicExchange()
    {
        logger.info("exchange created: "+EXCHANGE);
        return new TopicExchange(EXCHANGE);
    }




    @Bean
    public Binding bindingAQ() {
        logger.info("binding created: "+A_KEY);
        return BindingBuilder
                .bind(aQueue())
                .to(topicExchange())
                .with(A_KEY);
    }
    @Bean
    public Binding bindingEQ() {
        logger.info("binding created: "+A_KEY);
        return BindingBuilder
                .bind(aQueue())
                .to(topicExchange())
                .with(A_KEY);
    }
    @Bean
    public Binding bindingOQ() {
        logger.info("binding created: "+A_KEY);
        return BindingBuilder
                .bind(oQueue())
                .to(topicExchange())
                .with(A_KEY);
    }



    // we use message converter class because we are playing with the object not with string
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // add rabbitmq template to publish the message to the queue and then consume it
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
