package com.youyisi.app.soa.infrastructure.persist.activity.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.activity.ActivityRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.activity.ActivityWithHugThighActivity;
import com.youyisi.sports.domain.activity.ActivityWithInviteFriendActivity;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
@Repository
public class MybatisActivityRepositoryImpl extends MybatisOperations<Long, Activity> implements ActivityRepository {
	
	@Override
	public ActivityWithHugThighActivity getActivityWithHugThighActivityById(Long id) {
		return getSqlSession().selectOne(getNamespace().concat(".getActivityWithHugThighActivityById"), id);
	}

	@Override
	public ActivityWithInviteFriendActivity getActivityWithInviteFriendActivityById(
			Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getActivityWithInviteFriendActivityById"), id);
	}

	@Override
	public ActivityWithRelayRaceActivity getActivityWithRelayRaceActivityById(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getActivityWithRelayRaceActivityById"), id);
	}

	@Override
	public ActivityWithSnatchActivity getActivityWithSnatchActivityById(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getActivityWithSnatchActivityById"), id);
	}

	@Override
	public ActivityWithSnatchActivity getLatest(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  getSqlSession().selectOne(getNamespace().concat(".getLatest"), map);
	}
}

