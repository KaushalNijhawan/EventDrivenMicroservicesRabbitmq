package com.springboot.emailService.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springboot.emailService.model.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailConsumer {

	@RabbitListener(queues = "orderQueueEmail")
	public void receiveMessage(OrderEvent event) {
		log.info("Inside the receive message for the consumer in email to get data from producer " + event.toString());
	}
}
