package com.youyisi.admin.infrastructure.persist.relay;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.admin.domain.relay.RelayRaceActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Repository
public class MybatisRelayRaceActivityRepositoryImpl extends MybatisOperations<Long, RelayRaceActivity>
		implements RelayRaceActivityRepository {

	@Override
	public RelayRaceActivity getByActivityId(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityId"), activityId);
	}

	@Override
	public Integer delByActivityId(Long activityId) {
		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), activityId);
	}
}
