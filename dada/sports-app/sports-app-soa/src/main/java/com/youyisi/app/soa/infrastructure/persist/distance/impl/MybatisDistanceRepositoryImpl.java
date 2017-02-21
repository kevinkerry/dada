package com.youyisi.app.soa.infrastructure.persist.distance.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.distance.Distance;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisDistanceRepositoryImpl extends MybatisOperations<Long, Distance> implements DistanceRepository {

	@Override
	public Distance getByUserIdAndDate(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndDate"), userId);
	}

	@Override
	public Double getSumDistance(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getSumDistance"), userId);
	}

	@Override
	public Double getTeamDistance(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getTeamDistance"), map);
	}

	@Override
	public Double getUserDistance(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getUserDistance"), map);
	}

	@Override
	public Distance getByUserIdAndEnyDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndEnyDate"), map);
	}
}

