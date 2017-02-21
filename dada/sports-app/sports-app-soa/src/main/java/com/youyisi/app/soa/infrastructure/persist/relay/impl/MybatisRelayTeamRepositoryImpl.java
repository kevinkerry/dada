package com.youyisi.app.soa.infrastructure.persist.relay.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.relay.RelayTeamRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.relay.RelayTeamWithMore;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Repository
public class MybatisRelayTeamRepositoryImpl extends MybatisOperations<Long, RelayTeam> implements RelayTeamRepository {

	@Override
	public RelayTeam getByActivityIdAndUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityIdAndUserId"), map);
	}
	@Override
	public List<RelayTeamWithMore> getList(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getList"), activityId);
	}
	@Override
	public Integer getCountByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getCountByActivityId"), activityId);
	}
	
}

