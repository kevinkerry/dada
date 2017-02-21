package com.youyisi.admin.domain.relay;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-06
 */
public interface RelayMemberRepository extends MybatisRepository<Long, RelayMember> {

	/** 统计活动所有有效人数 **/
	Integer countRelayMemberNumber(Long activityId);

	List<RelayMember> getRelayMemberByTeamId(Long teamId);

	List<RelayMember> getRelayMemberList(Map<String, Object> map);

	Integer countRelayMember(Map<String, Object> map);

	List<RelayMember> getRelayMemberByActivityId(Long activityId);

}
