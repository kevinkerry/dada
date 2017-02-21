package com.youyisi.admin.infrastructure.persist.hugthigh;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.admin.domain.hugthigh.HugThighActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Repository
public class MybatisHugThighActivityRepositoryImpl extends MybatisOperations<Long, HugThighActivity>
		implements HugThighActivityRepository {

	@Override
	public Integer delByActivityId(Long ActivityId) {

		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), ActivityId);
	}

	@Override
	public HugThighActivity getByActivityId(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityId"), activityId);
	}
}
