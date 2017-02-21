package com.youyisi.admin.domain.coupon;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-07-18
 */
public interface CouponRepository extends MybatisRepository<Long, Coupon> {

	Integer delByActivityId(Long activityId);

	Integer countCouponNum(Map<String, Object> map);

	Coupon getCouponByActivityIdAndType(Map<String, Object> map);

	Double sumCouponMoney(Long activityId);

	List<Coupon> sumSlackerMoney(Long activityId);

	List<Coupon> getCouponByActivityId(Long activityId);

	Double getBonusByFirst(Map<String, Object> map);

	Double sumCouponMoneyByActivityId(Map<String, Object> map);

	Double sumUnusedMoney(Map<String, Object> map);

	Double sumRelayRaceMoney(Map<String, Object> map);

	Integer getSnatchBonusStatus(Map<String, Object> map);

	Integer getInviteRegister(Map<String, Object> map);

	Double sumInviteMoney(Long activityId);
}
