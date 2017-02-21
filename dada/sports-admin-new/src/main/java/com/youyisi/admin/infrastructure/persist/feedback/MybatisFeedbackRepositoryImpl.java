package com.youyisi.admin.infrastructure.persist.feedback;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.feedback.Feedback;
import com.youyisi.admin.domain.feedback.FeedbackRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-23
 */
@Repository
public class MybatisFeedbackRepositoryImpl extends MybatisOperations<Long, Feedback> implements FeedbackRepository {

	@Override
	public Page<Feedback> queryPageFeedback(Page<Feedback> page) {
		List<Feedback> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageFeedback"), page);
		page.setResult(selectList);
		return page;
	}
}
