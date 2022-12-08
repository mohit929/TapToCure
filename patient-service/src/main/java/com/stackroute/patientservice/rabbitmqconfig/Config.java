package com.stackroute.patientservice.rabbitmqconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

@Configuration
public class Config {
    public static final String P_QUEUE="Patient_Q";
    public static final String EXCHANGE="Patient_Exchange";
    public static final String PKEY="Patient_key";

    @Bean
    public Queue pqueue(){
        return new Queue(P_QUEUE);
    }
    @Bean
    public TopicExchange topicExchange()
    {
        return new TopicExchange(EXCHANGE);
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
