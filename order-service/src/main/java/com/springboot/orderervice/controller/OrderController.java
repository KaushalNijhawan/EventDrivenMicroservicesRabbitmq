package com.springboot.orderervice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.orderervice.model.Order;
import com.springboot.orderervice.model.OrderEvent;
import com.springboot.orderervice.producer.OrderProducer;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private OrderProducer orderProducer;

	
	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}


	@PostMapping("/publish")
	public ResponseEntity<String> publishOrder(@RequestBody Order order){
		OrderEvent event = new OrderEvent();
		event.setOrder(order);
		event.setMessage("Order is in Pending State");
		event.setStatus("PENDING");
		orderProducer.sendMessage(event);
		
		return ResponseEntity.ok("Message sent to the producer!");
	}
}
