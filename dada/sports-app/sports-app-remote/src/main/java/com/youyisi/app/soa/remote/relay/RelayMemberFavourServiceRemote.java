package com.youyisi.app.soa.remote.relay;

import java.util.Map;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.relay.RelayMemberFavour;

/**
 * @author shuye
 * @time 2016-10-08
 */
public interface RelayMemberFavourServiceRemote extends BaseServiceInterface<Long, RelayMemberFavour> {

	RelayMemberFavour getByMap(Map<String, Object> map)throws SoaException;

}

