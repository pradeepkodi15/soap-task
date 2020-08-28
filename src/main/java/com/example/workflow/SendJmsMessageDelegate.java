package com.example.workflow;

import java.util.logging.Logger;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * This is an empty service implementation illustrating how to use a plain Java
 * class as a BPMN 2.0 Service Task delegate.
 */
@Named("jmsSender")
public class SendJmsMessageDelegate implements JavaDelegate {

	private final Logger LOGGER = Logger.getLogger(SendJmsMessageDelegate.class.getName());

	@Autowired
	private JmsTemplate jmsTemplate;

	public void execute(DelegateExecution execution) throws Exception {

		// Business Key
		String asynchronousCorrelationKey = execution.getProcessBusinessKey();

		LOGGER.info("------asynchronousCorrelationKey-----" + asynchronousCorrelationKey);

		// Post message to the message queue named "OrderTransactionQueue"
		jmsTemplate.convertAndSend("OrderTransactionQueue", asynchronousCorrelationKey);

	}

}
