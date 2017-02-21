package com.youyisi.admin.application.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.application.user.UserSnatchService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.distance.DistanceRepository;
import com.youyisi.admin.domain.step.StepRepository;
import com.youyisi.admin.domain.user.UserSnatch;
import com.youyisi.admin.domain.user.UserSnatchRepository;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Service
public class UserSnatchServiceImpl implements UserSnatchService {

	@Autowired
	private UserSnatchRepository repository;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private DistanceRepository distanceRepository;

	@Autowired
	private CouponService couponService;

	@Override
	public UserSnatch get(Long id) {
		return repository.get(id);
	}

	@Override
	public UserSnatch save(UserSnatch entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(UserSnatch entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserSnatch entity) {
		return repository.update(entity);
	}

	@Override
	public Page<UserSnatch> queryPage(Page<UserSnatch> page) {
		Long activityId = (Long) page.getParams().get("activityId");
		Activity activity = activityServiceImpl.get(activityId);
		Page<UserSnatch> queryPage = repository.queryPage(page);
		List<UserSnatch> result = queryPage.getResult();
		for (UserSnatch userSnatch : result) {
			// 获取步数
			userSnatch.setStep(getSumStep(userSnatch.getUserId(), activity));
			// 获取距离
			userSnatch.setDistance(getSumDistance(userSnatch.getUserId(), activity));
			// 使用状态
			userSnatch.setEmploy(couponService.getSnatchBonusStatus(activityId, userSnatch.getUserId()));
		}

		return queryPage;
	}

	private Integer getSumStep(Long userId, Activity activity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId", userId);
		map.put("maxDistance", activity.getSnatchActivity().getMaxDistance());
		map.put("maxStep", activity.getSnatchActivity().getMaxStep());
		Integer step = stepRepository.getUserStep(map);
		if (step == null) {
			step = 0;
		}
		return step;
	}

	private Double getSumDistance(Long userId, Activity activity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId", userId);
		map.put("maxDistance", activity.getSnatchActivity().getMaxDistance());
		map.put("maxStep", activity.getSnatchActivity().getMaxStep());
		Double distance = distanceRepository.getUserDistance(map);
		if (distance == null) {
			distance = 0.00;
		}
		return distance;
	}

	@Override
	public Integer getUserSnatchCount(Long activityId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("type", type);
		return repository.getUserSnatchCount(map);
	}
}
