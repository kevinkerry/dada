package com.youyisi.admin.application.hugthigh.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.coupon.impl.CouponServiceImpl;
import com.youyisi.admin.application.hugthigh.HugThighService;
import com.youyisi.admin.application.thigh.impl.ThighServiceImpl;
import com.youyisi.admin.application.wallet.impl.WalletDetailServiceImpl;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.hugthigh.HugThigh;
import com.youyisi.admin.domain.hugthigh.HugThighRepository;
import com.youyisi.admin.domain.thigh.Thigh;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Service
public class HugThighServiceImpl implements HugThighService {

	@Autowired
	private HugThighRepository repository;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Autowired
	private WalletDetailServiceImpl walletDetailServiceImpl;

	@Autowired
	private CouponServiceImpl couponServiceImpl;

	@Autowired
	private ThighServiceImpl thighServiceImpl;

	@Override
	public HugThigh get(Long id) {
		return repository.get(id);
	}

	@Override
	public HugThigh save(HugThigh entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(HugThigh entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(HugThigh entity) {
		return repository.update(entity);
	}

	@Override
	public Page<HugThigh> queryPage(Page<HugThigh> page) {
		Page<HugThigh> queryPage = repository.queryPage(page);
		List<HugThigh> result = queryPage.getResult();
		for (HugThigh hugThigh : result) {
			Thigh thigh = thighServiceImpl.get(hugThigh.getThighId());
			if (thigh != null) {
				Coupon coupon = couponServiceImpl.getCouponByActivityIdAndType(thigh.getActivityId(), thigh.getType());
				if (coupon != null) {
					hugThigh.setCommission(coupon.getCommission());
				}
			}
		}
		return queryPage;
	}

	@Override
	public Integer countHugThigh(Long activityId, Long thighId, Integer resultType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("resultType", resultType);
		if (thighId != null) {
			map.put("thighId", thighId);
		}
		return repository.countHugThigh(map);
	}

	@Override
	public Integer countHugThighByUserId(Long userId) {

		return repository.countHugThighByUserId(userId);
	}

	@Override
	public List<HugThigh> query(HugThigh hugThigh) {

		return repository.query(hugThigh);
	}
}
