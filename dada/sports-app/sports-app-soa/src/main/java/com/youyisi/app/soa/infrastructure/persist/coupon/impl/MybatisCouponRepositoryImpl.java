package com.youyisi.app.soa.infrastructure.persist.coupon.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.coupon.Coupon;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Repository
public class MybatisCouponRepositoryImpl extends MybatisOperations<Long, Coupon> implements CouponRepository {

	@Override
	public List<Coupon> getListByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getListByActivityId"), activityId);
	}

	@Override
	public Coupon getByActivityIdAndType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityIdAndType"), map);
	}
}

