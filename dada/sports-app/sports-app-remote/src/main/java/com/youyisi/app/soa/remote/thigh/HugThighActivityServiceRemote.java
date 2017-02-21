package com.youyisi.app.soa.remote.thigh;

import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.thigh.HugThighActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
public interface HugThighActivityServiceRemote extends BaseServiceInterface<Long, HugThighActivity> {

	HugThighActivity getByActivityId(Long activityId);

}

