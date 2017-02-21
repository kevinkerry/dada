package com.youyisi.admin.application.relay;

import java.util.List;

import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.relay.RelayMember;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
public interface RelayMemberService {

	RelayMember save(RelayMember entity);

	RelayMember get(Long id);

	Integer delete(RelayMember entity);

	Integer update(RelayMember entity);

	Page<RelayMember> queryPage(Page<RelayMember> page);

	Page<RelayMember> queryPageMemberlist(Page<RelayMember> page);

	/** 统计活动所有有效人数 **/
	Integer countRelayMemberNumber(Long activityId);

	List<RelayMember> getRelayMemberByTeamId(Long teamId);

	Integer countRelayMember(Long activityId, Long parentId);

	/** 根据活动ID获取用户 **/
	List<RelayMember> getRelayMemberByActivityId(Long activityId);

	Double getSumDistance(Long userId, Activity activity);

	List<RelayMember> getRelayMemberList(Long activityId, Long id, Long parentId);

	Page<RelayMember> queryPageSportslist(Page<RelayMember> page);
}
