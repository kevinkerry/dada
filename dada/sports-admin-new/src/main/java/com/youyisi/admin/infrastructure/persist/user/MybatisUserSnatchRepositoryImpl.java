package com.youyisi.admin.infrastructure.persist.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.UserSnatch;
import com.youyisi.admin.domain.user.UserSnatchRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Repository
public class MybatisUserSnatchRepositoryImpl extends MybatisOperations<Long, UserSnatch>
		implements UserSnatchRepository {

	@Override
	public List<UserSnatch> getByActivityId(Long activityId) {
		return getSqlSession().selectList(getNamespace().concat(".getByActivityId"), activityId);
	}

	@Override
	public Integer getUserSnatchCount(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getUserSnatchCount"), map);
	}
}
