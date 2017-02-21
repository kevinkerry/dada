package com.youyisi.app.soa.infrastructure.persist.activity;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.activity.RelayRaceActivity;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface RelayRaceActivityRepository extends MybatisRepository<Long, RelayRaceActivity> {

	RelayRaceActivity getByActivityId(Long activityId);
}

