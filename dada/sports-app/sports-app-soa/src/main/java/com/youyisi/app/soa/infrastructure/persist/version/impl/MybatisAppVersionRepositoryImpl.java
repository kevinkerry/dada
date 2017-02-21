package com.youyisi.app.soa.infrastructure.persist.version.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.version.AppVersionRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.version.AppVersion;

/**
 * @author shuye
 * @time 2016-08-30
 */
@Repository
public class MybatisAppVersionRepositoryImpl extends MybatisOperations<Long, AppVersion> implements AppVersionRepository {

	@Override
	public AppVersion getByChannel(String channel) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByChannel"), channel);
	}
}

