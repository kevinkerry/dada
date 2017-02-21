package com.youyisi.app.soa.infrastructure.persist.activity.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.activity.RelayRaceActivityRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.activity.RelayRaceActivity;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Repository
public class MybatisRelayRaceActivityRepositoryImpl extends MybatisOperations<Long, RelayRaceActivity> implements RelayRaceActivityRepository {

	@Override
	public RelayRaceActivity getByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityId"), activityId);
	}
}

