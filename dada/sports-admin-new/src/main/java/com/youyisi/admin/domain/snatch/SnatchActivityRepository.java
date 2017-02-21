package com.youyisi.admin.domain.snatch;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface SnatchActivityRepository extends MybatisRepository<Long, SnatchActivity> {

	Integer delByActivityId(Long activityId);

	SnatchActivity getByActivityId(Long activityId);
}
