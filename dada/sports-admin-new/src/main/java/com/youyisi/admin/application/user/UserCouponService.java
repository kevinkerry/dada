package com.youyisi.admin.application.user;

import java.util.List;

import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
public interface UserCouponService {

	UserCoupon save(UserCoupon entity);

	UserCoupon get(Long id);

	Integer delete(UserCoupon entity);

	Integer update(UserCoupon entity);

	Page<UserCoupon> queryPage(Page<UserCoupon> page);

	UserCoupon getUsing(Long userId, Long date);

	/**
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param clientType
	 *            1安卓 2IOS
	 * @return Integer
	 */
	Integer countUserCoupon(Long beginTime, Long endTime, Integer clientType);

	/**
	 * 
	 * @param couponId
	 * @param status
	 * @return Integer
	 */
	Integer countUserCouponByCouponId(Long couponId, Integer status);

	/**
	 * 
	 * @param activityId
	 * @param status
	 * @return Integer
	 */
	Integer countUserCouponByActivityId(Long activityId, Integer status);

	List<UserCoupon> getUserCouponByUserIdAndCouponId(Long userId, Long couponId);

	/**
	 * 获取中奖的用户的奖券
	 * 
	 * @param activityId
	 * @param userId
	 * @return UserCoupon
	 */
	UserCoupon getLotteryUserCoupon(Long activityId, Long userId);
}
