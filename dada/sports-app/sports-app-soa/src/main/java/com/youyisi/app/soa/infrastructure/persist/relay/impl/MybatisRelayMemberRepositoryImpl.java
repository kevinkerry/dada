package com.youyisi.app.soa.infrastructure.persist.relay.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.relay.RelayMemberRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMemberWithChildren;
import com.youyisi.sports.domain.relay.RelayMemberWithChildrenAndUser;
import com.youyisi.sports.domain.relay.RelayMemberWithMore;
import com.youyisi.sports.domain.relay.RelayMemberWithParent;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Repository
public class MybatisRelayMemberRepositoryImpl extends MybatisOperations<Long, RelayMember> implements RelayMemberRepository {

	@Override
	public RelayMember getByActivityIdAndUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityIdAndUserId"), map);
	}

	@Override
	public Integer getCountByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getCountByActivityId"), activityId);
	}

	@Override
	public Page<RelayMemberWithMore> queryPageForTeam(
			Page<RelayMemberWithMore> page) {
		List<RelayMemberWithMore> result = getSqlSession().selectList(getNamespace().concat(".queryPageForTeam"), page);
		page.setResult(result);
		return page;
	}

	@Override
	public RelayMemberWithMore getMyRelayMemberWithMore(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getMyRelayMemberWithMore"), map);
	}

	@Override
	public List<RelayMemberWithChildren> getByParentId(Long id) {
		return getSqlSession().selectList(getNamespace().concat(".getByParentId"), id);
	}

	@Override
	public List<RelayMemberWithChildrenAndUser> getRelayMemberWithChildrenAndUserByParentId(Long id) {
		return getSqlSession().selectList(getNamespace().concat(".getRelayMemberWithChildrenAndUserByParentId"), id);
	}

	@Override
	public RelayMemberWithParent getParents(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getParents"), id);
	}

	@Override
	public RelayMember getByTeamIdAndUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByTeamIdAndUserId"), map);
	}
}

