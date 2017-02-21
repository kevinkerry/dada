package com.youyisi.app.soa.infrastructure.persist.snatch;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.snatch.SnatchFee;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface SnatchFeeRepository extends MybatisRepository<Long, SnatchFee> {

	List<SnatchFee> getByActivityId(Long activityId);
}

