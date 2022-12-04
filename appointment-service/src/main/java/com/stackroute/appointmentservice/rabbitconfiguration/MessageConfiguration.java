package com.stackroute.appointmentservice.rabbitconfiguration;

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
    public static final String C_QUEUE = "CLINIC_QUEUE";
    public static final String A_QUEUE = "APPOINTMENT_QUEUE";
    public static final String C_KEY = "CLINIC_KEY";
    public static final String A_KEY = "APPOINTMENT_KEY";
    public static final String EXCHANGE = "EXCHANGE";


    @Bean
    public Queue cQueue() {
        logger.info("queue created: "+C_QUEUE);
        return new Queue(C_QUEUE);

    }
    @Bean
    public Queue aQueue() {
        logger.info("queue created: "+A_QUEUE);
        return new Queue(A_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange()
    {
        logger.info("exchange created: "+EXCHANGE);
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingCQ() {
        logger.info("binding created: "+C_KEY);
        return BindingBuilder
                .bind(cQueue())
                .to(topicExchange())
                .with(C_KEY);
    }

    @Bean
    public Binding bindingAQ() {
        logger.info("binding created: "+A_KEY);
        return BindingBuilder
                .bind(aQueue())
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
