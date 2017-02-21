package com.youyisi.app.soa.infrastructure.persist.thigh;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.thigh.HugThighActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
public interface HugThighActivityRepository extends MybatisRepository<Long, HugThighActivity> {

	HugThighActivity getByActivityId(Long activityId);
}

