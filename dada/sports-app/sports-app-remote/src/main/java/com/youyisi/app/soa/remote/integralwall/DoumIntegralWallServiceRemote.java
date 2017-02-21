package com.youyisi.app.soa.remote.integralwall;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.integralwall.DoumIntegralWall;

/**
 * @author shuye
 * @time 2016-08-15
 */
public interface DoumIntegralWallServiceRemote extends BaseServiceInterface<Long, DoumIntegralWall> {

	void domobNote(DoumIntegralWall integralWall)throws SoaException;

}

