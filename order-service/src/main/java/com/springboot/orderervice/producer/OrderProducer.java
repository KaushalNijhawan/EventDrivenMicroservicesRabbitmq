package com.springboot.orderervice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.springboot.orderervice.model.OrderEvent;

@Service
public class OrderProducer {

	private RabbitTemplate rabbitTemplate;
	private Logger log = LoggerFactory.getLogger(OrderProducer.class);
	public OrderProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(OrderEvent event) {
		log.info("Inside the method to send the data from producer to the queue " + event.toString());
		rabbitTemplate.convertAndSend("orderTopic", "orderRouteKey", event);
		log.info("Sending the data to the email consumer " + event.toString());
		rabbitTemplate.convertAndSend("orderTopic", "orderKeyEmail", event);
	}
}
