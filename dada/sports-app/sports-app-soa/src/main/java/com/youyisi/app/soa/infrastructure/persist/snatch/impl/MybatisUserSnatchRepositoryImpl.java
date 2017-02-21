package com.youyisi.app.soa.infrastructure.persist.snatch.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.snatch.UserSnatchRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.snatch.UserSnatch;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Repository
public class MybatisUserSnatchRepositoryImpl extends MybatisOperations<Long, UserSnatch> implements UserSnatchRepository {

	@Override
	public Integer getCountByActivityIdAndUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getCountByActivityIdAndUserId"), map);
	}
}

