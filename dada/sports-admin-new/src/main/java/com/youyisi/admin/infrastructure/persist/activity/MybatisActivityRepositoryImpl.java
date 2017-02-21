package com.youyisi.admin.infrastructure.persist.activity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.ActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Repository
public class MybatisActivityRepositoryImpl extends MybatisOperations<Long, Activity> implements ActivityRepository {

	@Override
	public Integer validateDate(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".validateDate"), map);
	}

	@Override
	public Activity validateActivity(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".validateActivity"), map);
	}

	@Override
	public List<Activity> getCorrelationActivityName(Long medalId) {
		return getSqlSession().selectList(getNamespace().concat(".getCorrelationActivityName"), medalId);
	}
}
