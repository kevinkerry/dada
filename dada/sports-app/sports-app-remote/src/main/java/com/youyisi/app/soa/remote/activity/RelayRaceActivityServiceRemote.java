package com.youyisi.app.soa.remote.activity;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.activity.RelayRaceActivity;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface RelayRaceActivityServiceRemote extends BaseServiceInterface<Long, RelayRaceActivity> {

	RelayRaceActivity getByActivityId(Long activityId)throws SoaException;

}

