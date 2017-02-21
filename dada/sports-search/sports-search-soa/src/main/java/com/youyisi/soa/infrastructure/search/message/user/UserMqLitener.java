package com.youyisi.soa.infrastructure.search.message.user;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;

import com.youyisi.soa.infrastructure.persist.user.UserRepository;
import com.youyisi.sports.domain.user.User;

public class UserMqLitener {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserSearchRepository userSearchRepository;
 
	public void receive(TextMessage message) throws JmsException, JMSException {
		System.out.println("---------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + message.getText());
		User user = userRepository.get(Long.parseLong(message.getText()));
		userSearchRepository.update(user);

	}
}
