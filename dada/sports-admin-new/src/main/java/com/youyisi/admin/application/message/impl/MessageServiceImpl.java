package com.youyisi.admin.application.message.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.message.MessageService;
import com.youyisi.admin.application.user.impl.UserServiceImpl;
import com.youyisi.admin.domain.message.Message;
import com.youyisi.admin.domain.message.MessageRepository;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-24
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public Message get(Long id) {
		return repository.get(id);
	}

	@Override
	public Message save(Message entity) {
		entity.setCreateTime(System.currentTimeMillis());
		entity.setStatus(0);
		return repository.save(entity);
	}

	@Override
	public Integer delete(Message entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Message entity) {
		int row = 0;
		Message message = repository.get(entity.getId());
		if (message != null) {
			message.setTitle(entity.getTitle());
			message.setContent(entity.getContent());
			message.setType(entity.getType());
			message.setStatus(0);
			row = repository.update(message);
		}
		return row;
	}

	@Override
	public Page<Message> queryPage(Page<Message> page) {
		return repository.queryPage(page);
	}

	private void pushMessage(Message message, User user) {
		TransmissionContent tc = new TransmissionContent();
		Map<String, Object> param = new HashMap<String, Object>();
		tc.setTitle(message.getTitle());
		param.put("content", message.getContent());
		param.put("sendTime", message.getSendTime());
		tc.setType(Constant.PUSH_SYS_MESSAGE);
		tc.setEntity(param);
		tc.setToUserId(user.getId());
		PushToSingleHelper.push(user, tc);
	}

	@Override
	public Integer send(Long id) {
		int row = 0;
		final Message message = repository.get(id);
		if (message != null) {
			new Thread() {
				@Override
				public void run() {
					Page<User> queryPagePushUser = userServiceImpl.queryPagePushUser(1, 1000, message.getType());
					for (int currentPage = 1; currentPage <= queryPagePushUser.getTotalPages(); currentPage++) {
						queryPagePushUser = userServiceImpl.queryPagePushUser(currentPage, 1000, message.getType());
						for (User u : queryPagePushUser.getResult()) {
							pushMessage(message, u);
						}
					}
					super.run();
				}
			}.start();
			message.setStatus(1);
			message.setSendTime(System.currentTimeMillis());
			row = repository.update(message);
		}
		return row;
	}

	@Override
	public Integer sendOne(Long mid, Long userId) {
		Message message = repository.get(mid);
		User user = userServiceImpl.get(userId);
		pushMessage(message, user);
		return 1;
	}
}
