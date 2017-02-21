package com.youyisi.app.soa.remote.activity;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.activity.ActivityWithHugThighActivity;
import com.youyisi.sports.domain.activity.ActivityWithInviteFriendActivity;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
public interface ActivityServiceRemote extends BaseServiceInterface<Long, Activity> {

	ActivityWithHugThighActivity getActivityWithHugThighActivityById(Long id) throws SoaException;

	ActivityWithInviteFriendActivity getActivityWithInviteFriendActivityById(
			Long id)throws SoaException;

	ActivityWithRelayRaceActivity getActivityWithRelayRaceActivityById(Long id)throws SoaException;

	ActivityWithSnatchActivity getActivityWithSnatchActivityById(Long id)throws SoaException;

	ActivityWithSnatchActivity getLatest(long time, int type);

}

