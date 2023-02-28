package com.springboot.orderervice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

	@Bean
	public Queue getQueue() {
		return new Queue("orderQueue");
	}
	
	@Bean
	public Queue getQueueEmail() {
		return new Queue("orderQueueEmail");
	}
	
	@Bean
	public TopicExchange getTopic() {
		return new TopicExchange("orderTopic");
	}
	
	@Bean
	public Binding getBind() {
		return BindingBuilder.bind(getQueue())
				.to(getTopic())
				.with("orderRouteKey");
	}
	
	@Bean
	public Binding getBindEmail() {
		return BindingBuilder.bind(getQueueEmail())
				.to(getTopic())
				.with("orderKeyEmail");
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate getTemplate(ConnectionFactory connectionfactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionfactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
