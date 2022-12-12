package com.stackroute.authenticationservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfiguration {

    public final static String AUTH_QUEUE="AUTH_Q";
    public final static String ROUTING_KEY="AUTHENTICATION_KEY";

    @Bean
    public MessageConverter messageConverter()
    {  Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();

        return converter;
    }

    @Bean(name="MQ")
    AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


}
