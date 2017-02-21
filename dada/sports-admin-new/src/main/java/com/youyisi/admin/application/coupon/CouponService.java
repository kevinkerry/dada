package com.youyisi.admin.application.coupon;

import java.util.List;

import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
public interface CouponService {

	Coupon save(Coupon entity);

	Coupon get(Long id);

	Integer delete(Coupon entity);

	Integer update(Coupon entity);

	Page<Coupon> queryPage(Page<Coupon> page);

	Integer delByActivityId(Long activityId);

	/**
	 * 
	 * @param activityId
	 *            活动ID
	 * @param resultType
	 *            结果类型 1 统计次数 2统计金额
	 * @return Integer
	 */
	Integer countCouponNum(Long activityId, Integer resultType);

	/**
	 * 根据活动ID 类型获取数据
	 * 
	 * @param activityId
	 * @param type
	 * @return Coupon
	 */
	Coupon getCouponByActivityIdAndType(Long activityId, Integer type);

	/**
	 * 统计福利券数量
	 * 
	 * @param activityId
	 * @param type
	 * @return Integer
	 */
	Integer countWelfareNum(Long activityId, Integer type);

	Double sumCouponMoney(Long activityId);

	Double sumSlackerMoney(Long activityId);

	List<Coupon> getCouponByActivityId(Long activityId);

	Double sumInviteMoney(Long activityId);

	/** 统计冠军奖金 **/
	Double getBonusByFirst(Long activityId, Long userId);

	/** 统计发放的体验金(接力赛) **/
	Double sumCouponMoneyByActivityId(Long activityId);

	/** 查询未使用的体验金(接力赛) **/
	Double sumUnusedMoney(Long activityId, Long userId);

	/** 统计用户获得的体验金券(接力赛) **/
	Double sumRelayRaceMoney(Long activityId, Long userId);

	/** 获取一元夺宝奖金使用状态 **/
	Integer getSnatchBonusStatus(Long activityId, Long userId);

	/**
	 * 获取邀请和注册数
	 * 
	 * @param type
	 *            1 邀请人 去重 , 2 邀请次数
	 * @param activityId
	 *            活动ID
	 * @return Integer
	 */
	Integer getInviteRegister(Integer type, Long activityId);

}
