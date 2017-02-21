package com.youyisi.app.soa.infrastructure.persist.relay.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.relay.RelayLineRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.relay.RelayLine;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Repository
public class MybatisRelayLineRepositoryImpl extends MybatisOperations<Long, RelayLine> implements RelayLineRepository {

	@Override
	public List<RelayLine> getByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getByActivityId"), activityId);
	}
}

