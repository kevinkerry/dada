package com.youyisi.app.soa.remote.coupon;

import java.util.Map;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCouponMore;
import com.youyisi.sports.domain.user.User;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface UserCouponServiceRemote extends BaseServiceInterface<Long, UserCoupon> {

	AnnualYield use(UserCoupon userCoupon)throws SoaException;

	UserCoupon getUsing(Long userId, Long date)throws SoaException;

	Page<UserCouponWithCoupon> queryPageUserCouponWithCoupon(Page<UserCouponWithCoupon> page)throws SoaException;

	Page<UserCouponWithCoupon> queryPageForMyList(Page<UserCouponWithCoupon> page)throws SoaException;

	Integer getCount(Map<String, Object> map)throws SoaException;

	void issue(Activity a, User user)throws SoaException;

	Page<UserCouponWithCouponMore> queryPageForInviteFriend(Page<UserCouponWithCouponMore> page)throws SoaException;

}

