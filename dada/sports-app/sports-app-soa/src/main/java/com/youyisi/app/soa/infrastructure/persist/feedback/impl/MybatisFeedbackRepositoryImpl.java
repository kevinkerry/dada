package com.youyisi.app.soa.infrastructure.persist.feedback.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.feedback.Feedback;
import com.youyisi.app.soa.infrastructure.persist.feedback.FeedbackRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-13
 */
@Repository
public class MybatisFeedbackRepositoryImpl extends MybatisOperations<Long, Feedback> implements FeedbackRepository {
}

