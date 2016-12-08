package org.heyframework.test.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService {

	@Autowired
	private MessageChannel helloWorldChannel;

	@Autowired
	private HelloService helloWorldGateway;

	@Override
	public void greet(String name) {
		helloWorldChannel.send(MessageBuilder.withPayload(name).build());
	}

	@Override
	public void greet2(String name) {
		System.out.println(helloWorldGateway.getHelloMessage(name));
	}
}
