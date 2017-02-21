package com.youyisi.admin.infrastructure.persist.snatch;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.snatch.SnatchFee;
import com.youyisi.admin.domain.snatch.SnatchFeeRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Repository
public class MybatisSnatchFeeRepositoryImpl extends MybatisOperations<Long, SnatchFee> implements SnatchFeeRepository {

	@Override
	public Integer delByActivityId(Long activityId) {

		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), activityId);
	}

	@Override
	public List<SnatchFee> getSnatchFeeByActivityId(Long activityId) {
		return getSqlSession().selectList(getNamespace().concat(".getSnatchFeeByActivityId"), activityId);
	}
}
