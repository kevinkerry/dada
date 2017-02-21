package com.youyisi.app.soa.remote.snatch;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.snatch.SnatchFee;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface SnatchFeeServiceRemote extends BaseServiceInterface<Long, SnatchFee> {

	List<SnatchFee> getByActivityId(Long activityId)throws SoaException;

}

