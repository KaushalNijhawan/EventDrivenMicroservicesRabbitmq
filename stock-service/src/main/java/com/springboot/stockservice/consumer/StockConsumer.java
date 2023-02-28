package com.springboot.stockservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springboot.stockservice.model.OrderEvent;


@Service
public class StockConsumer {

	private Logger log = LoggerFactory.getLogger(StockConsumer.class);
	@RabbitListener(queues = "orderQueue")
	public void receiveMessage(OrderEvent event) {
		log.info("Inside the receive message method to get the data " + event.toString());
	}
}
