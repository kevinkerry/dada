package com.youyisi.admin.domain.relay;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-06
 */
public interface RelayRaceActivityRepository extends MybatisRepository<Long, RelayRaceActivity> {

	RelayRaceActivity getByActivityId(Long activityId);

	Integer delByActivityId(Long activityId);
}
