package com.youyisi.app.soa.remote.relay;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.relay.RelayLine;

/**
 * @author shuye
 * @time 2016-10-08
 */
public interface RelayLineServiceRemote extends BaseServiceInterface<Long, RelayLine> {

	List<RelayLine> getByActivityId(Long activityId) throws SoaException;

}

