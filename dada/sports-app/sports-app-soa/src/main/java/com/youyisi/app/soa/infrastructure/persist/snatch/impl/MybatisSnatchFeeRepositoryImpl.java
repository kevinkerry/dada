package com.youyisi.app.soa.infrastructure.persist.snatch.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchFeeRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.snatch.SnatchFee;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Repository
public class MybatisSnatchFeeRepositoryImpl extends MybatisOperations<Long, SnatchFee> implements SnatchFeeRepository {

	@Override
	public List<SnatchFee> getByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getByActivityId"), activityId);
	}
}

