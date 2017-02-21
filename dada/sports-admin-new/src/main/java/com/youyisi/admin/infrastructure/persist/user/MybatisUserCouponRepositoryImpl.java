package com.youyisi.admin.infrastructure.persist.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.admin.domain.user.UserCouponRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Repository
public class MybatisUserCouponRepositoryImpl extends MybatisOperations<Long, UserCoupon>
		implements UserCouponRepository {

	@Override
	public UserCoupon getUsing(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getUsing"), map);
	}

	@Override
	public Integer countUserCoupon(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countUserCoupon"), map);
	}

	@Override
	public Integer countUserCouponByCouponId(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countUserCouponByCouponId"), map);
	}

	@Override
	public Integer countUserCouponByActivityId(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countUserCouponByActivityId"), map);
	}

	@Override
	public List<UserCoupon> getUserCouponByUserIdAndCouponId(Map<String, Object> map) {

		return getSqlSession().selectList(getNamespace().concat(".getUserCouponByUserIdAndCouponId"), map);
	}

	@Override
	public UserCoupon getLotteryUserCoupon(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getLotteryUserCoupon"), map);
	}
}
