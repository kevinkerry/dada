package com.youyisi.app.soa.infrastructure.persist.coupon.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.coupon.UserCouponRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCouponMore;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Repository
public class MybatisUserCouponRepositoryImpl extends MybatisOperations<Long, UserCoupon> implements UserCouponRepository {

	@Override
	public UserCoupon getUsing(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getUsing"), map);
	}

	@Override
	public Page<UserCouponWithCoupon> queryPageUserCouponWithCoupon(
			Page<UserCouponWithCoupon> page) {
		List<UserCouponWithCoupon> list = getSqlSession().selectList(getNamespace().concat(".queryPageUserCouponWithCoupon"), page);
		page.setResult(list);
		return page;
	}

	@Override
	public Page<UserCouponWithCoupon> queryPageForMyList(
			Page<UserCouponWithCoupon> page) {
		List<UserCouponWithCoupon> list = getSqlSession().selectList(getNamespace().concat(".queryPageForMyList"), page);
		page.setResult(list);
		return page;
	}

	@Override
	public UserCouponWithCoupon getUsingWithCoupon(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getUsingWithCoupon"), map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getCount"), map);
	}

	@Override
	public Page<UserCouponWithCouponMore> queryPageForInviteFriend(
			Page<UserCouponWithCouponMore> page) {
		// TODO Auto-generated method stub
		List<UserCouponWithCouponMore> list = getSqlSession().selectList(getNamespace().concat(".queryPageForInviteFriend"), page);
		page.setResult(list);
		return page;
	}

	@Override
	public UserCoupon getByCategoryAndUser(Map<String, Object> cmap) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByCategoryAndUser"), cmap);
	}
}

