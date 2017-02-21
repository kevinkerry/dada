package com.youyisi.admin.application.feedback.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.feedback.FeedbackService;
import com.youyisi.admin.domain.feedback.Feedback;
import com.youyisi.admin.domain.feedback.FeedbackRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-23
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository repository;

	@Override
	public Feedback get(Long id) {
		return repository.get(id);
	}

	@Override
	public Feedback save(Feedback entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Feedback entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Feedback entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Feedback> queryPage(Page<Feedback> page) {
		return repository.queryPage(page);
	}

	@Override
	public Page<Feedback> queryPageFeedback(Page<Feedback> page) {

		return repository.queryPageFeedback(page);
	}

	@Override
	public Integer dispose(Long id) {
		Integer row = 0;
		Feedback feedback = repository.get(id);
		if (feedback != null) {
			if (feedback.getStatus() == 0) {
				feedback.setStatus(1);
				row = repository.update(feedback);
			}
		}
		return row;
	}
}
