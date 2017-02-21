package com.youyisi.admin.application.topic.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.topic.TopicService;
import com.youyisi.admin.domain.topic.Topic;
import com.youyisi.admin.domain.topic.TopicRepository;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository repository;
	
	@Resource
	private ActiveMqSender activeMqSender;

	@Override
	public Topic get(Long id) {
		return repository.get(id);
	}

	@Override
	public Topic save(Topic topic) {
	    Topic savedTopic = repository.save(topic);
	    activeMqSender.send(savedTopic.getTopicId().toString(),Constant.JMS_QUEUE_TOPIC);
		return savedTopic;
	}

	@Override
	public Integer delete(Topic topic) {
		return repository.delete(topic);
	}

	@Override
	public Integer update(Topic topic) {
	    int result = repository.update(topic);
	    activeMqSender.send(topic.getTopicId().toString(),Constant.JMS_QUEUE_TOPIC);
		return result;
	}
	
	@Override
	public Page<Topic> queryPage(Page<Topic> page) {
		return repository.queryPage(page);
	}
}

