package com.youyisi.app.soa.remote.integralwall;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.integralwall.IntegralWall;

/**
 * @author shuye
 * @time 2016-08-09
 */
public interface IntegralWallServiceRemote extends BaseServiceInterface<Long, IntegralWall> {

	void note(IntegralWall integralWall) throws SoaException;


}

