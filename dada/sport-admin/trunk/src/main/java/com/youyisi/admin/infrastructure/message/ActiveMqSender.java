package com.youyisi.admin.infrastructure.message;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSender {

	@Resource
	private JmsTemplate jmsTemplate;

	public void send(final String id, String _queue) {
		try {
			if (StringUtils.isNotBlank(_queue)) {
				Queue queue = jmsTemplate.getConnectionFactory()
						.createConnection()
						.createSession(false, Session.AUTO_ACKNOWLEDGE)
						.createQueue(_queue);
				jmsTemplate.send(queue, new MessageCreator() {
					public Message createMessage(Session session)throws JMSException {
						TextMessage msg = session.createTextMessage();
						msg.setText(id);
						return msg;
					}
				});
			} else {
				jmsTemplate.convertAndSend(id);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
