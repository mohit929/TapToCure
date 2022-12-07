package com.stackroute.registrationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config
{

    public static final String D_QUEUE="Doctor_Q";
    public static final String P_QUEUE="Patient_Q";
    public static final String E_QUEUE="EMAIL_Q";
    public static final String A_QUEUE="AUTH_Q";
    public static final String EXCHANGE="Registration_Exchange";
    public static final String DKEY="Doctor_key";
    public static final String PKEY="Patient_key";
    public static final String EKEY = "Email_key";
    public static final String AUTHKEY = "AUTHENTICATION_KEY";

    @Bean
    public Queue dqueue(){
        return new Queue(D_QUEUE);
    }
    @Bean
    public Queue pqueue(){
        return new Queue(P_QUEUE);
    }
    @Bean
    public Queue equeue(){
        return new Queue(E_QUEUE);
    }
    @Bean
    public Queue authQueue(){
        return new Queue(A_QUEUE);
    }
    @Bean
    public TopicExchange topicExchange()
    {
        return new TopicExchange(EXCHANGE);
    }


    @Bean
    public Binding bindingDq(){
        return BindingBuilder
                .bind(dqueue())
                .to(topicExchange())
                .with(DKEY);
    }

    // binding between json queue and exchange using routing key
    @Bean
    public Binding bindingPq(){
        return BindingBuilder
                .bind(pqueue())
                .to(topicExchange())
                .with(PKEY);
    }
    @Bean
    public Binding bindingEmailQ(){
        return BindingBuilder
                .bind(equeue())
                .to(topicExchange())
                .with(EKEY);
    }
    @Bean
    public Binding bindingAuthQ(){
        return BindingBuilder
                .bind(authQueue())
                .to(topicExchange())
                .with(AUTHKEY);
    }
    @Bean
    public Jackson2JsonMessageConverter converter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory conn)
    {
        final RabbitTemplate rabbitTemplate=new RabbitTemplate(conn);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

