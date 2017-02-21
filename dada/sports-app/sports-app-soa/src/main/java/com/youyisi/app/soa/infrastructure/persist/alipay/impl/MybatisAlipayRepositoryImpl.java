package com.youyisi.app.soa.infrastructure.persist.alipay.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.alipay.AlipayRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.alipay.Alipay;

/**
 * @author shuye
 * @time 2016-05-19
 */
@Repository
public class MybatisAlipayRepositoryImpl extends MybatisOperations<Long, Alipay> implements AlipayRepository {

	@Override
	public Alipay getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}

	@Override
	public Alipay getByAlipay(String alipay) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByAlipay"), alipay);
	}
}

