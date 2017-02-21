package com.youyisi.app.soa.infrastructure.persist.relay;

import java.util.List;
import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMemberWithChildren;
import com.youyisi.sports.domain.relay.RelayMemberWithChildrenAndUser;
import com.youyisi.sports.domain.relay.RelayMemberWithMore;
import com.youyisi.sports.domain.relay.RelayMemberWithParent;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface RelayMemberRepository extends MybatisRepository<Long, RelayMember> {

	RelayMember getByActivityIdAndUserId(Map<String, Object> map);

	Integer getCountByActivityId(Long activityId);

	Page<RelayMemberWithMore> queryPageForTeam(Page<RelayMemberWithMore> page);

	RelayMemberWithMore getMyRelayMemberWithMore(Map<String, Object> map);

	List<RelayMemberWithChildren> getByParentId(Long id);

	List<RelayMemberWithChildrenAndUser> getRelayMemberWithChildrenAndUserByParentId(
			Long id);

	RelayMemberWithParent getParents(Long id);

	RelayMember getByTeamIdAndUserId(Map<String, Object> map);

}

