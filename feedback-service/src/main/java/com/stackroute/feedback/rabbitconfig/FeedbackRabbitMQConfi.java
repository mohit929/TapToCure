package com.stackroute.feedback.rabbitconfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackRabbitMQConfi {
	
	public static final String ROUNTING_KEY = "feedback_routing_key";
	public static final String EXCHANGE = "feedback_exchange";
	public static final String Clinic_Details_QUEUE = "Clinic_Q";
	
	public static final String Registeration_QUEUE = "EMAIL_Q";

	@Bean
	public Queue  cqueue() {
		return new Queue (Clinic_Details_QUEUE);
	}

	@Bean
	public Queue  rqueue() {
		return new Queue (Registeration_QUEUE);
	}
	
	
	@Bean
    public TopicExchange  exchange() {	
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Binding cbinding ()
	{
		
		return BindingBuilder.bind(cqueue()).to(exchange()).with(ROUNTING_KEY);
	}
	
	
	@Bean
	public Binding rbinding ()
	{
		
		return BindingBuilder.bind(rqueue()).to(exchange()).with(ROUNTING_KEY);
	}
	
	
	
	@Bean
	public MessageConverter messageConverter() {
		
		return new Jackson2JsonMessageConverter();
	
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}

	
	
	
	
	
	

}
