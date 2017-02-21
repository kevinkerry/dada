package com.youyisi.admin.domain.hugthigh;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-07-19
 */
public interface HugThighActivityRepository extends MybatisRepository<Long, HugThighActivity> {

	Integer delByActivityId(Long ActivityId);

	HugThighActivity getByActivityId(Long activityId);
}
