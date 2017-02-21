package com.youyisi.admin.application.feedback;

import com.youyisi.admin.domain.feedback.Feedback;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-23
 */
public interface FeedbackService {

	Feedback save(Feedback entity);

	Feedback get(Long id);

	Integer delete(Feedback entity);

	Integer update(Feedback entity);

	Page<Feedback> queryPage(Page<Feedback> page);

	Page<Feedback> queryPageFeedback(Page<Feedback> page);

	Integer dispose(Long id);

}
