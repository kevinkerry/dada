package com.youyisi.admin.infrastructure.persist.relay;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.relay.RelayMember;
import com.youyisi.admin.domain.relay.RelayMemberRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Repository
public class MybatisRelayMemberRepositoryImpl extends MybatisOperations<Long, RelayMember>
		implements RelayMemberRepository {

	@Override
	public Integer countRelayMemberNumber(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".countRelayMemberNumber"), activityId);
	}

	@Override
	public List<RelayMember> getRelayMemberByTeamId(Long teamId) {

		return getSqlSession().selectList(getNamespace().concat(".getRelayMemberByTeamId"), teamId);
	}

	@Override
	public Integer countRelayMember(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countRelayMember"), map);
	}

	@Override
	public List<RelayMember> getRelayMemberList(Map<String, Object> map) {
		return getSqlSession().selectList(getNamespace().concat(".getRelayMemberList"), map);
	}

	@Override
	public List<RelayMember> getRelayMemberByActivityId(Long activityId) {
		return getSqlSession().selectList(getNamespace().concat(".getRelayMemberByActivityId"), activityId);
	}
}
