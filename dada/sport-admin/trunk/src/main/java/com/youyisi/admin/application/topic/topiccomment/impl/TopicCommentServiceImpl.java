package com.youyisi.admin.application.topic.topiccomment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.topic.topiccomment.TopicCommentService;
import com.youyisi.admin.domain.topic.topiccomment.TopicComment;
import com.youyisi.admin.domain.topic.topiccomment.TopicCommentRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Service
public class TopicCommentServiceImpl implements TopicCommentService {

	@Autowired
	private TopicCommentRepository repository;

	@Override
	public TopicComment get(Long id) {
		return repository.get(id);
	}

	@Override
	public TopicComment save(TopicComment topicComment) {
		return repository.save(topicComment);
}

	@Override
	public Integer delete(TopicComment topicComment) {
		return repository.delete(topicComment);
	}

	@Override
	public Integer update(TopicComment topicComment) {
		return repository.update(topicComment);
	}
	@Override
	public Page<TopicComment> queryPage(Page<TopicComment> page) {
		return repository.queryPage(page);
	}
}

