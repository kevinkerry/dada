package com.youyisi.admin.application.snatch.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.coupon.impl.CouponServiceImpl;
import com.youyisi.admin.application.snatch.SnatchActivityService;
import com.youyisi.admin.application.user.impl.UserCouponServiceImpl;
import com.youyisi.admin.application.user.impl.UserMedalServiceImpl;
import com.youyisi.admin.application.wallet.impl.WalletDetailServiceImpl;
import com.youyisi.admin.application.wallet.impl.WalletServiceImpl;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.ActivityRepository;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.distance.DistanceRepository;
import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.admin.domain.snatch.SnatchActivityRepository;
import com.youyisi.admin.domain.snatch.SnatchWin;
import com.youyisi.admin.domain.snatch.SnatchWinRepository;
import com.youyisi.admin.domain.step.StepRepository;
import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.admin.domain.user.UserMedal;
import com.youyisi.admin.domain.user.UserSnatch;
import com.youyisi.admin.domain.user.UserSnatchRepository;
import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.admin.domain.wallet.WalletDetail;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Service
public class SnatchActivityServiceImpl implements SnatchActivityService {

	@Autowired
	private SnatchActivityRepository repository;
	@Autowired
	private UserSnatchRepository userSnatchRepository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private SnatchWinRepository snatchWinRepository;
	@Autowired
	private CouponServiceImpl couponServiceImpl;

	@Autowired
	private UserCouponServiceImpl userCouponServiceImpl;

	@Autowired
	private WalletServiceImpl walletServiceImpl;

	@Autowired
	private WalletDetailServiceImpl walletDetailServiceImpl;

	@Autowired
	private UserMedalServiceImpl userMedalServiceImpl;

	@Override
	public SnatchActivity get(Long id) {
		return repository.get(id);
	}

	@Override
	public SnatchActivity save(SnatchActivity entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(SnatchActivity entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SnatchActivity entity) {
		return repository.update(entity);
	}

	@Override
	public Page<SnatchActivity> queryPage(Page<SnatchActivity> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer delByActivityId(Long activityId) {

		return repository.delByActivityId(activityId);
	}

	@Override
	public SnatchActivity getByActivityId(Long activityId) {

		return repository.getByActivityId(activityId);
	}

	@Override
	public Integer settle(SnatchActivity snatchActivity) {
		Integer row = 0;
		Activity activity = activityRepository.get(snatchActivity.getActivityId());
		if (activity.getEndTime() > System.currentTimeMillis()) {
			return -2;
		}
		snatchActivity = activity.getSnatchActivity();
		List<UserSnatch> list = userSnatchRepository.getByActivityId(snatchActivity.getActivityId());
		Double totalDistance = 0.0;
		List<Double> listdistance = new ArrayList<Double>();
		Integer totalCount = 0;
		for (int index = 0; index < list.size(); index++) {
			totalCount += list.get(index).getCount();
			Double d = ArithHelper.mul(list.get(index).getCount(),
					getSumDistance(list.get(index).getUserId(), activity));
			/*
			 * System.out.println("index" + index + "count" +
			 * list.get(index).getCount() + "distance" +
			 * getSumDistance(list.get(index).getUserId(), activity) + "total" +
			 * d);
			 */
			listdistance.add(d);
			totalDistance = ArithHelper.add(totalDistance, d);
		}
		if (list == null || snatchActivity.getMinNum() > totalCount) {
			return -1;
		}
		if (totalDistance > 0) {
			Random r = new Random();
			UserSnatch[] lucks = new UserSnatch[activity.getSnatchActivity().getWinNum()];
			int[] luck = new int[activity.getSnatchActivity().getWinNum()];
			
			
			double[] luckdistance = new double[activity.getSnatchActivity().getWinNum()];
			Double totalLuckDistance = 0.0;
			
			List<UserSnatch> _list = list;
			
			for (int num = 0; num < activity.getSnatchActivity().getWinNum(); num++) {
				if(totalDistance>0){
					luck[num] = r.nextInt(totalDistance.intValue());
				}else{
					luck[num] = 0;
				}
				
				Double _totalDistance = 0.0;
				for (int index = 0; index < _list.size(); index++) {
					_totalDistance = ArithHelper.add(_totalDistance, listdistance.get(index));
					//for (int num = 0; num < activity.getSnatchActivity().getWinNum(); num++) {
						if (luck[num] <= _totalDistance) {
							lucks[num] = (_list.get(index));
							luckdistance[num] = listdistance.get(index);
							totalLuckDistance = ArithHelper.add(totalLuckDistance, listdistance.get(index));
							luck[num] = totalDistance.intValue() + 1;
							
							totalDistance = ArithHelper.sub(totalDistance, listdistance.get(index));
							
							listdistance.remove(index);
							_list.remove(index);
							index--;
							
						}
					//}
				
				}
				
				
			}
			
			

			setWiner(snatchActivity, activity, list, lucks, luckdistance, totalLuckDistance, totalCount);
			snatchActivity.setSettle(1);
			row = repository.update(snatchActivity);
		}
		return row;
	}

	private void setMedal(UserSnatch userSnatch, SnatchActivity snatchActivity) {
		UserMedal userMedal = new UserMedal();
		userMedal.setUserId(userSnatch.getUserId());
		userMedal.setMedalId(snatchActivity.getWinMedal());
		userMedal.setCreateTime(System.currentTimeMillis());
		userMedalServiceImpl.save(userMedal);
	}

	private void setWiner(SnatchActivity snatchActivity, Activity activity, List<UserSnatch> list, UserSnatch[] lucks,
			double[] luckdistance, Double totalLuckDistance, Integer totalCount) {
		for (int index = 0; index < lucks.length; index++) {
			SnatchWin entity = new SnatchWin();
			entity.setActivityId(activity.getId());
			entity.setActivityNum(snatchActivity.getActivityNum());
			entity.setCreateTime(System.currentTimeMillis());
			entity.setStatus(0);
			entity.setUserId(lucks[index].getUserId());
			entity.setBonus(getBonus(totalLuckDistance, index, luckdistance, snatchActivity, totalCount));
			snatchWinRepository.save(entity);
			setCouponAndUserCoupon(activity.getId(), entity.getBonus(), snatchActivity.getExpiryDay(),
					entity.getUserId());
			setMedal(lucks[index], snatchActivity);
		}
	}

	private void setCouponAndUserCoupon(Long activityId, Double bonus, Integer expiryDay, Long userId) {
		Coupon coupon = new Coupon();
		coupon.setActivityId(activityId);
		coupon.setBonus(bonus);
		coupon.setType(13);
		coupon.setExpiryDay(expiryDay);
		couponServiceImpl.save(coupon);

		if (coupon.getId() != null) {
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setCouponId(coupon.getId());
			userCoupon.setUserId(userId);
			userCoupon.setDate(DateUtil.currentDateForDay());
			userCoupon.setCreateTime(System.currentTimeMillis());
			userCoupon.setUpdateTime(System.currentTimeMillis());
			userCoupon.setStatus(0);
			userCoupon.setCategory(5);
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DATE, expiryDay);
			userCoupon.setExpiryTime(calendar.getTime().getTime());
			userCouponServiceImpl.save(userCoupon);
		}
	}

	private Double getBonus(Double totalLuckDistance, int index, double[] luckdistance, SnatchActivity snatchActivity,
			Integer totalCount) {
		return ArithHelper.div(
				ArithHelper.mul(ArithHelper.mul(snatchActivity.getContributeBonus(), totalCount), luckdistance[index]),
				totalLuckDistance);
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
			distance = 0.0;
		}
		Integer step = stepRepository.getUserStep(map);
		if (step == null) {
			step = 0;
		}
		Double _step = step * 1.0 / activity.getSnatchActivity().getStepToDistance();
		return ArithHelper.round(distance + _step, 2);
	}

	@Override
	public Integer refund(Long activityId) {
		Integer row = 0;
		Activity activity = activityRepository.get(activityId);
		SnatchActivity snatchActivity = repository.getByActivityId(activityId);
		if (snatchActivity.getSettle() == 0) {
			if (activity.getEndTime() < System.currentTimeMillis()) {
				List<UserSnatch> byActivityId = userSnatchRepository.getByActivityId(activityId);
				Wallet wallet = null;
				for (UserSnatch userSnatch : byActivityId) {
					wallet = walletServiceImpl.getByUserId(userSnatch.getUserId());
					if (userSnatch.getMoney() != null) {
						// 退款
						wallet.setTotalAsset(ArithHelper.add(wallet.getTotalAsset(), userSnatch.getMoney()));
						walletServiceImpl.update(wallet);
						// 退款记录
						WalletDetail walletDetail = new WalletDetail();
						walletDetail.setUserId(userSnatch.getUserId());
						walletDetail.setType(10);
						walletDetail.setMoney(userSnatch.getMoney());
						walletDetail.setDate(DateUtil.currentDateForDay());
						walletDetail.setCreateTime(System.currentTimeMillis());
						walletDetail.setResult(wallet.getTotalAsset());
						walletDetailServiceImpl.save(walletDetail);
					}
				}
				snatchActivity.setSettle(-1);
				row += repository.update(snatchActivity);
			}
		} else {
			row = -1;
		}

		return row;
	}

}
