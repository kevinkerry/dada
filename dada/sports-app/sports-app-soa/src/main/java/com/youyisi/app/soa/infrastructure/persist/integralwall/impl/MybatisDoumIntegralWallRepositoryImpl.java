package com.youyisi.app.soa.infrastructure.persist.integralwall.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.integralwall.DoumIntegralWallRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.integralwall.DoumIntegralWall;

/**
 * @author shuye
 * @time 2016-08-15
 */
@Repository
public class MybatisDoumIntegralWallRepositoryImpl extends MybatisOperations<Long, DoumIntegralWall> implements DoumIntegralWallRepository {

	@Override
	public DoumIntegralWall getByUserIdAndOrderId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndOrderId"), map);
	}
}

