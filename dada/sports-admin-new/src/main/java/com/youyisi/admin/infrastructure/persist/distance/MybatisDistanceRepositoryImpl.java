package com.youyisi.admin.infrastructure.persist.distance;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.distance.Distance;
import com.youyisi.admin.domain.distance.DistanceRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-06-02
 */
@Repository
public class MybatisDistanceRepositoryImpl extends MybatisOperations<Long, Distance> implements DistanceRepository {

	@Override
	public Double countDistance(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countDistance"), map);
	}

	@Override
	public Double countDistanceByUserId(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countDistanceByUserId"), map);
	}

	@Override
	public Integer countDistanceNum(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countDistanceNum"), map);
	}

	@Override
	public Double getTeamDistance(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getTeamDistance"), map);
	}

	@Override
	public Double getUserDistance(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getUserDistance"), map);
	}
}
