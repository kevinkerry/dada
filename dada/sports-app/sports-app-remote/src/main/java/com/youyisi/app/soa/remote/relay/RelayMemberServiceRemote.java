package com.youyisi.app.soa.remote.relay;

import java.util.List;
import java.util.Map;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMemberWithChildrenAndUser;
import com.youyisi.sports.domain.relay.RelayMemberWithMore;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface RelayMemberServiceRemote extends BaseServiceInterface<Long, RelayMember> {

	RelayMember getByActivityIdAndUserId(Long activityId, Long userId)throws SoaException;

	Integer getCountByActivityId(Long id)throws SoaException;

	Page<RelayMemberWithMore> queryPageForTeam(Page<RelayMemberWithMore> page,Long currentUserId)throws SoaException;

	RelayMemberWithMore getMyRelayMemberWithMore(Map<String,Object> map)throws SoaException;

	List<RelayMemberWithChildrenAndUser> getRelayMemberWithChildrenAndUserByParentId(Long id)throws SoaException;

	Double getSumDistance(Long userId, ActivityWithRelayRaceActivity activity)throws SoaException;

	Map<String,Object> mylist(Long id)throws SoaException;

	void otherpay(Orders order)throws SoaException;

	int pay(RelayMember rm)throws SoaException;

	RelayMember getByTeamIdAndUserId(Long teamId, Long id)throws SoaException;

}

