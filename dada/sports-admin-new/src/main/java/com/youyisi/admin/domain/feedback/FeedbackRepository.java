package com.youyisi.admin.domain.feedback;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-23
 */
public interface FeedbackRepository extends MybatisRepository<Long, Feedback> {

	Page<Feedback> queryPageFeedback(Page<Feedback> page);
}
