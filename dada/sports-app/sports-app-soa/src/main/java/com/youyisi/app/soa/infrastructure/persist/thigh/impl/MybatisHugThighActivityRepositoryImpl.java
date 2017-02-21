package com.youyisi.app.soa.infrastructure.persist.thigh.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.thigh.HugThighActivityRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.thigh.HugThighActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
@Repository
public class MybatisHugThighActivityRepositoryImpl extends MybatisOperations<Long, HugThighActivity> implements HugThighActivityRepository {

	@Override
	public HugThighActivity getByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityId"), activityId);
	}
}

