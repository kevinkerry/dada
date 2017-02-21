package com.youyisi.app.soa.infrastructure.persist.goldbean.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.goldbean.GoldBean;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanRepositoryImpl extends MybatisOperations<Long, GoldBean> implements GoldBeanRepository {

	@Override
	public GoldBean getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}
}

