package com.youyisi.app.soa.infrastructure.persist.activity;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.activity.ActivityWithHugThighActivity;
import com.youyisi.sports.domain.activity.ActivityWithInviteFriendActivity;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
public interface ActivityRepository extends MybatisRepository<Long, Activity> {
	public ActivityWithHugThighActivity getActivityWithHugThighActivityById(Long id);

	public ActivityWithInviteFriendActivity getActivityWithInviteFriendActivityById(
			Long id);

	public ActivityWithRelayRaceActivity getActivityWithRelayRaceActivityById(
			Long id);

	public ActivityWithSnatchActivity getActivityWithSnatchActivityById(Long id);

	public ActivityWithSnatchActivity getLatest(Map<String, Object> map);
}

