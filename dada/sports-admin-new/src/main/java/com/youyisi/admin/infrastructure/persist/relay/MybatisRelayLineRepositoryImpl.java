package com.youyisi.admin.infrastructure.persist.relay;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.relay.RelayLine;
import com.youyisi.admin.domain.relay.RelayLineRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Repository
public class MybatisRelayLineRepositoryImpl extends MybatisOperations<Long, RelayLine> implements RelayLineRepository {

	@Override
	public Integer delByActivityId(Long activityId) {
		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), activityId);
	}

	@Override
	public List<RelayLine> getRelayLineList(Long activityId) {

		return getSqlSession().selectList(getNamespace().concat(".getRelayLineList"), activityId);
	}
}
