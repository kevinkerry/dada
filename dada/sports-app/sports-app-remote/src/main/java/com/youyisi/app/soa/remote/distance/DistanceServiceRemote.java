package com.youyisi.app.soa.remote.distance;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.distance.Distance;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface DistanceServiceRemote extends BaseServiceInterface<Long, Distance> {

	Distance getByUserIdAndDate(Long userId) throws SoaException;

	Double getSumDistance(Long userId);

}

