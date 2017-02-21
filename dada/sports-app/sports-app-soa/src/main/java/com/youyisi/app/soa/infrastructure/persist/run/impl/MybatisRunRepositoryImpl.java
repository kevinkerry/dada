package com.youyisi.app.soa.infrastructure.persist.run.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.run.RunRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.run.RunWithTrack;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisRunRepositoryImpl extends MybatisOperations<Long, Run> implements RunRepository {

	@Override
	public RunWithTrack getDetail(Long id) {
		// TODO Auto-generated method stub
		 return getSqlSession().selectOne(getNamespace().concat(".getDetail"), id);
	}

	@Override
	public Run getMaxRun(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getMaxRun"), map);
	}
}

