package com.youyisi.app.soa.remote.relay;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.relay.RelayTeamWithMore;
import com.youyisi.sports.domain.user.User;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface RelayTeamServiceRemote extends BaseServiceInterface<Long, RelayTeam> {

	RelayTeam getByActivityIdAndUserId(Long activityId, Long userId)throws SoaException;

	List<RelayTeamWithMore> getList(Long activityId, User user, Integer end)throws SoaException;

	Integer getCountByActivityId(Long activityId)throws SoaException;

}

