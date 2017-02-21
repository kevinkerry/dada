package com.youyisi.admin.infrastructure.persist.snatch;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.admin.domain.snatch.SnatchActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Repository
public class MybatisSnatchActivityRepositoryImpl extends MybatisOperations<Long, SnatchActivity>
		implements SnatchActivityRepository {

	@Override
	public Integer delByActivityId(Long activityId) {

		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), activityId);
	}

	@Override
	public SnatchActivity getByActivityId(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityId"), activityId);
	}
}
