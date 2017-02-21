package com.youyisi.app.soa.infrastructure.persist.coupon;

import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCouponMore;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface UserCouponRepository extends MybatisRepository<Long, UserCoupon> {

	UserCoupon getUsing(Map<String, Object> map);

	Page<UserCouponWithCoupon> queryPageUserCouponWithCoupon(
			Page<UserCouponWithCoupon> page);

	Page<UserCouponWithCoupon> queryPageForMyList(
			Page<UserCouponWithCoupon> page);
	
	UserCouponWithCoupon getUsingWithCoupon(Map<String, Object> map);

	Integer getCount(Map<String, Object> map);

	Page<UserCouponWithCouponMore> queryPageForInviteFriend(
			Page<UserCouponWithCouponMore> page);

	UserCoupon getByCategoryAndUser(Map<String, Object> cmap);

}

