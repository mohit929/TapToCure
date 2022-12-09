package com.stackroute.appointmentservice.rabbitconfiguration;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    Logger logger = Logger.getLogger(MessageConfiguration.class.getSimpleName());

    // QUEUE: Are required to publish and consume the data
    public static final String CLINIC_QUEUE_OF_CLINIC_SERVICE = "Clinic_Q";
    public static final String PATIENT_QUEUE_OF_PATIENT_SERVICE = "PATIENT_QUEUE_OF_PATIENT_SERVICE";
    public static final String APPOINTMENT_QUEUE= "APPOINTMENT_QUEUE";

    // KEYS: are required only to publish the data
    public static final String APPOINTMENT_KEY = "APPOINTMENT_KEY";


    // EXCHANGE: are required only to publish the data
    public static final String APPOINTMENT_EXCHANGE = "APPOINTMENT_EXCHANGE";

    // MESSAGES
    private static final String QUEUE_CREATED = "queue created: ";
    private static final String BINDING_CREATED = "binding created: ";
    private static final String EXCHANGE_CREATED = "exchange created: ";

    @Bean
    public Queue appointmentQueue() {
        logger.info(QUEUE_CREATED + APPOINTMENT_QUEUE);
        return new Queue(APPOINTMENT_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange() {
        logger.info(EXCHANGE_CREATED + APPOINTMENT_EXCHANGE);
        return new TopicExchange(APPOINTMENT_EXCHANGE);
    }

    @Bean
    public Binding bindingAppointmentQueue() {
        logger.info(BINDING_CREATED + APPOINTMENT_KEY);
        return BindingBuilder
                .bind(appointmentQueue())
                .to(topicExchange())
                .with(APPOINTMENT_KEY);
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