package com.youyisi.admin.application.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.user.UserCouponService;
import com.youyisi.admin.application.wallet.impl.WalletDetailServiceImpl;
import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.admin.domain.user.UserCouponRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Service
public class UserCouponServiceImpl implements UserCouponService {

	@Autowired
	private UserCouponRepository repository;

	@Autowired
	private WalletDetailServiceImpl walletDetailServiceImpl;

	@Override
	public UserCoupon get(Long id) {
		return repository.get(id);
	}

	@Override
	public UserCoupon save(UserCoupon entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(UserCoupon entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserCoupon entity) {
		return repository.update(entity);
	}

	@Override
	public Page<UserCoupon> queryPage(Page<UserCoupon> page) {
		Page<UserCoupon> queryPage = repository.queryPage(page);
		List<UserCoupon> result = queryPage.getResult();
		for (UserCoupon userCoupon : result) {
			if (userCoupon.getStatus() == 1) {
				userCoupon.setEarnings(
						walletDetailServiceImpl.sumWallet(userCoupon.getUserId(), userCoupon.getDate(), 7));
			}
		}
		return queryPage;
	}

	@Override
	public UserCoupon getUsing(Long userId, Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		return repository.getUsing(map);
	}

	@Override
	public Integer countUserCoupon(Long beginTime, Long endTime, Integer clientType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("clientType", clientType);
		return repository.countUserCoupon(map);
	}

	@Override
	public Integer countUserCouponByCouponId(Long couponId, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("couponId", couponId);
		if (status != null) {
			map.put("status", status);
		}
		return repository.countUserCouponByCouponId(map);
	}

	@Override
	public Integer countUserCouponByActivityId(Long activityId, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		if (status != null) {
			map.put("status", status);
		}
		return repository.countUserCouponByActivityId(map);
	}

	@Override
	public List<UserCoupon> getUserCouponByUserIdAndCouponId(Long userId, Long couponId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (couponId != null) {
			map.put("couponId", couponId);
		}
		if (userId != null) {
			map.put("userId", userId);
		}
		return repository.getUserCouponByUserIdAndCouponId(map);
	}

	@Override
	public UserCoupon getLotteryUserCoupon(Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (activityId != null) {
			map.put("activityId", activityId);
		}
		if (userId != null) {
			map.put("userId", userId);
		}
		return repository.getLotteryUserCoupon(map);
	}
}
