package com.youyisi.admin.domain.snatch;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface SnatchFeeRepository extends MybatisRepository<Long, SnatchFee> {

	Integer delByActivityId(Long activityId);

	List<SnatchFee> getSnatchFeeByActivityId(Long activityId);
}
