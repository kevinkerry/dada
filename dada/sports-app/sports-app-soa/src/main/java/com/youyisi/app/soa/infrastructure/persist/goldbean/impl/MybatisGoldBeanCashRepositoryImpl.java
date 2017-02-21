package com.youyisi.app.soa.infrastructure.persist.goldbean.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanCashRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.goldbean.GoldBeanCash;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanCashRepositoryImpl extends MybatisOperations<Long, GoldBeanCash> implements GoldBeanCashRepository {

	@Override
	public Double getSumCash(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getSumCash"), userId);
	}
}

