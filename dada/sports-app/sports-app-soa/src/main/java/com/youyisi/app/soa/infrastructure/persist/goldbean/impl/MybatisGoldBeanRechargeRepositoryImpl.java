package com.youyisi.app.soa.infrastructure.persist.goldbean.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRechargeRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.goldbean.GoldBeanRecharge;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanRechargeRepositoryImpl extends MybatisOperations<Long, GoldBeanRecharge> implements GoldBeanRechargeRepository {

	@Override
	public List<GoldBeanRecharge> list() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".list"));
	}
}

