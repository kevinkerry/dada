package com.youyisi.app.soa.application.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.feedback.FeedbackServiceRemote;
import com.youyisi.sports.domain.feedback.Feedback;
import com.youyisi.app.soa.infrastructure.persist.feedback.FeedbackRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-13
 */
@Service
public class FeedbackService implements FeedbackServiceRemote {

	@Autowired
	private FeedbackRepository repository;

	@Override
	public Feedback get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Feedback save(Feedback entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Feedback entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Feedback entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Feedback> queryPage(Page<Feedback> page) throws SoaException{
		return repository.queryPage(page);
	}
}

