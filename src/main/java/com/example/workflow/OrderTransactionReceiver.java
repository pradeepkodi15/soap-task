package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderTransactionReceiver {

	@Autowired
	RuntimeService runtimeService;

	@JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
	public void receiveMessage(String transaction) {
		System.out.println("Received <" + transaction + ">");
		// transactionRepository.save(transaction);
		runtimeService.correlateMessage("item-order", transaction);
	}
}