package com.youyisi.app.soa.infrastructure.persist.integralwall.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.integralwall.IntegralWallRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.integralwall.IntegralWall;

/**
 * @author shuye
 * @time 2016-08-09
 */
@Repository
public class MybatisIntegralWallRepositoryImpl extends MybatisOperations<Long, IntegralWall> implements IntegralWallRepository {

	@Override
	public IntegralWall getByUserIdAndPK(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndPK"), map);
	}
}

