package com.youyisi.admin.application.coupon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.application.user.impl.UserCouponServiceImpl;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.coupon.CouponRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponRepository repository;

	@Autowired
	private UserCouponServiceImpl userCouponServiceImpl;

	@Override
	public Coupon get(Long id) {
		return repository.get(id);
	}

	@Override
	public Coupon save(Coupon entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Coupon entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Coupon entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Coupon> queryPage(Page<Coupon> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer delByActivityId(Long activityId) {

		return repository.delByActivityId(activityId);
	}

	@Override
	public Integer countCouponNum(Long activityId, Integer resultType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("resultType", resultType);
		return repository.countCouponNum(map);
	}

	@Override
	public Coupon getCouponByActivityIdAndType(Long activityId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("type", type);
		return repository.getCouponByActivityIdAndType(map);
	}

	@Override
	public Integer countWelfareNum(Long activityId, Integer type) {
		Coupon coupon = getCouponByActivityIdAndType(activityId, type);
		Integer count = 0;
		if (coupon != null) {
			count = userCouponServiceImpl.countUserCouponByCouponId(coupon.getId(), null);
		}
		return count;
	}

	@Override
	public Double sumCouponMoney(Long activityId) {

		return repository.sumCouponMoney(activityId);
	}

	@Override
	public Double sumSlackerMoney(Long activityId) {
		Double money = 0.0;
		// List<Coupon> list = repository.sumSlackerMoney(activityId);
		// for (Coupon coupon : list) {
		//
		// }
		return money;
	}

	@Override
	public List<Coupon> getCouponByActivityId(Long activityId) {

		return repository.getCouponByActivityId(activityId);
	}

	@Override
	public Double sumInviteMoney(Long activityId) {

		return repository.sumInviteMoney(activityId);
	}

	@Override
	public Double getBonusByFirst(Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		Double bonusByFirst = repository.getBonusByFirst(map);
		if (bonusByFirst == null) {
			bonusByFirst = 0.00;
		}
		return bonusByFirst;
	}

	@Override
	public Double sumCouponMoneyByActivityId(Long activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		return repository.sumCouponMoneyByActivityId(map);
	}

	@Override
	public Double sumUnusedMoney(Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.sumUnusedMoney(map);
	}

	@Override
	public Double sumRelayRaceMoney(Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.sumRelayRaceMoney(map);
	}

	@Override
	public Integer getSnatchBonusStatus(Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.getSnatchBonusStatus(map);
	}

	@Override
	public Integer getInviteRegister(Integer type, Long activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("type", type);
		return repository.getInviteRegister(map);
	}

}
