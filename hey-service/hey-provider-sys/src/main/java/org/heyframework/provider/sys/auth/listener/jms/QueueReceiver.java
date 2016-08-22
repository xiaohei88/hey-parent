package org.heyframework.provider.sys.auth.listener.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class QueueReceiver {

	@JmsListener(destination = "adduser.queue")
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
