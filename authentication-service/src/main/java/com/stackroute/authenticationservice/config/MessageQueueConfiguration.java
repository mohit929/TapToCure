package com.stackroute.authenticationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfiguration {
    public final String EXCHANGE_NAME="Registration_Exchange";
    public final String AUTH_QUEUE="AUTH_Q";
    public final String ROUTING_KEY="AUTHENTICATION_KEY";
//    public final String PATIENT_QUEUE="PATIENT_Q";

    @Bean
    public Queue queueA() {
        return new Queue(AUTH_QUEUE,false);
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingA(Queue queueA, DirectExchange directExchange)
    {
        return BindingBuilder.bind(queueA).to(directExchange).with(ROUTING_KEY);
    }

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
