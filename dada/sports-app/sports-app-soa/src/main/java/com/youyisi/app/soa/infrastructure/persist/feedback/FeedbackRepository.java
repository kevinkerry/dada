package com.youyisi.app.soa.infrastructure.persist.feedback;

import com.youyisi.sports.domain.feedback.Feedback;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-13
 */
public interface FeedbackRepository extends MybatisRepository<Long, Feedback> {
}

