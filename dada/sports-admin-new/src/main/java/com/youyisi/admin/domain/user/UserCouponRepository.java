package com.youyisi.admin.domain.user;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-07-19
 */
public interface UserCouponRepository extends MybatisRepository<Long, UserCoupon> {

	UserCoupon getUsing(Map<String, Object> map);

	Integer countUserCoupon(Map<String, Object> map);

	Integer countUserCouponByCouponId(Map<String, Object> map);

	Integer countUserCouponByActivityId(Map<String, Object> map);

	List<UserCoupon> getUserCouponByUserIdAndCouponId(Map<String, Object> map);

	UserCoupon getLotteryUserCoupon(Map<String, Object> map);
}
