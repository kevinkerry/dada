package com.youyisi.admin.application.topic.topicimage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.topic.topicimage.TopicImageService;
import com.youyisi.admin.domain.topic.topicimage.TopicImage;
import com.youyisi.admin.domain.topic.topicimage.TopicImageRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Service
public class TopicImageServiceImpl implements TopicImageService {

	@Autowired
	private TopicImageRepository repository;

	@Override
	public TopicImage get(Long id) {
		return repository.get(id);
	}

	@Override
	public TopicImage save(TopicImage topicImage) {
		return repository.save(topicImage);
}

	@Override
	public Integer delete(TopicImage topicImage) {
		return repository.delete(topicImage);
	}

	@Override
	public Integer update(TopicImage topicImage) {
		return repository.update(topicImage);
	}
	@Override
	public Page<TopicImage> queryPage(Page<TopicImage> page) {
		return repository.queryPage(page);
	}
}

