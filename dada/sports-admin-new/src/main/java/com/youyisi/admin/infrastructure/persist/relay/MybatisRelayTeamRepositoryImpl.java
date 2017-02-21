package com.youyisi.admin.infrastructure.persist.relay;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.relay.RelayTeam;
import com.youyisi.admin.domain.relay.RelayTeamRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Repository
public class MybatisRelayTeamRepositoryImpl extends MybatisOperations<Long, RelayTeam> implements RelayTeamRepository {

	@Override
	public Double getRelayTeamSumDistance(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getRelayTeamSumDistance"), map);
	}

	@Override
	public List<RelayTeam> getRelayTeamByActivityId(Long activityId) {
		return getSqlSession().selectList(getNamespace().concat(".getRelayTeamByActivityId"), activityId);
	}

	@Override
	public Integer updateSettleByActivityId(Map<String, Object> map) {
		return getSqlSession().update(getNamespace().concat(".updateSettleByActivityId"), map);
	}

	@Override
	public Integer countRelayTeamNumByActivityId(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".countRelayTeamNumByActivityId"), activityId);
	}

}
