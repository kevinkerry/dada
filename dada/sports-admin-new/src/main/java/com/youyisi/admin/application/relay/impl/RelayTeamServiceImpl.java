package com.youyisi.admin.application.relay.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.coupon.impl.CouponServiceImpl;
import com.youyisi.admin.application.relay.RelayTeamService;
import com.youyisi.admin.application.user.impl.UserCouponServiceImpl;
import com.youyisi.admin.application.user.impl.UserMedalServiceImpl;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.distance.DistanceRepository;
import com.youyisi.admin.domain.relay.RelayMember;
import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.admin.domain.relay.RelayTeam;
import com.youyisi.admin.domain.relay.RelayTeamRepository;
import com.youyisi.admin.domain.step.StepRepository;
import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.admin.domain.user.UserMedal;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Service
public class RelayTeamServiceImpl implements RelayTeamService {

	@Autowired
	private RelayTeamRepository repository;

	@Autowired
	private CouponServiceImpl couponServiceImpl;

	@Autowired
	private UserCouponServiceImpl userCouponServiceImpl;

	@Autowired
	private RelayRaceActivityServiceImpl relayRaceActivityServiceImpl;

	@Autowired
	private RelayMemberServiceImpl relayMemberServiceImpl;

	@Autowired
	private UserMedalServiceImpl userMedalServiceImpl;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;

	@Override
	public RelayTeam get(Long id) {
		return repository.get(id);
	}

	@Override
	public RelayTeam save(RelayTeam entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RelayTeam entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayTeam entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RelayTeam> queryPage(Page<RelayTeam> page) {
		Long activityId = (Long) page.getParams().get("activityId");
		Activity activity = activityServiceImpl.get(activityId);
		page = repository.queryPage(page);
		List<RelayTeam> list = page.getResult();
		List<RelayTeam> result = new ArrayList<RelayTeam>();
		for (RelayTeam r : list) {
			Double sumDistance = getSumDistance(r, activity);
			if (sumDistance != null) {
				DecimalFormat df = new DecimalFormat("#.00");
				sumDistance = Double.valueOf(df.format(sumDistance));
			}
			r.setSumDistance(sumDistance);
			result.add(r);
		}
		page.setResult(result);
		return page;
	}

	private Double getSumDistance(RelayTeam r, Activity activity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("teamId", r.getId());
		map.put("maxDistance", activity.getRelayRaceActivity().getMaxDistance());
		map.put("maxStep", activity.getRelayRaceActivity().getMaxStep());
		Double distance = distanceRepository.getTeamDistance(map);
		Integer step = stepRepository.getTeamStep(map);
		if (step == null) {
			step = 0;
		}
		Double _step = step * 1.0 / activity.getRelayRaceActivity().getStepToDistance();
		if (distance == null) {
			distance = 0.0;
		}
		return ArithHelper.add(distance, _step);
	}

	@Override
	public Integer clearing(Long id) {
		Integer row = 0;
		// 中奖的队伍
		RelayTeam relayTeam = repository.get(id);

		Activity activity = activityServiceImpl.get(relayTeam.getActivityId());
		if (System.currentTimeMillis() > activity.getEndTime()) {
			// 本次活动
			RelayRaceActivity relayRaceActivity = relayRaceActivityServiceImpl
					.getByActivityId(relayTeam.getActivityId());
			// 本次活动所有有效人数
			Integer relayMemberNumber = relayMemberServiceImpl.countRelayMemberNumber(relayTeam.getActivityId());
			if (relayMemberNumber == null) {
				relayMemberNumber = 0;
			}
			// 统计奖金池金额
			Double contributeBonus = ArithHelper.mul(relayRaceActivity.getContributeBonus(), relayMemberNumber);
			// 当前队伍总跑量
			Double relayTeamSumDistance = getSumDistance(relayTeam, activity);
			if (relayTeamSumDistance == null) {
				relayTeamSumDistance = 0.00;
			}
			relayTeamSumDistance = ArithHelper.cutRoundDouble(relayTeamSumDistance);
			List<RelayMember> list = relayMemberServiceImpl.getRelayMemberByTeamId(relayTeam.getId());
			Double oneselfDistance = null;
			// 每个用户获得的奖金
			Double bonus = null;

			// 设置每个用户的数据
			for (RelayMember relayMember : list) {
				// 当前用户跑量
				oneselfDistance = relayMemberServiceImpl.getSumDistance(relayMember.getUserId(), activity);
				if (oneselfDistance == null) {
					oneselfDistance = 0.00;
				}
				oneselfDistance = ArithHelper.cutRoundDouble(oneselfDistance);
				bonus = ArithHelper.div((ArithHelper.mul(contributeBonus, oneselfDistance)), relayTeamSumDistance);
				// 设置奖金券
				if (bonus > 0) {
					setCouponAndUserCoupon(relayRaceActivity.getActivityId(), ArithHelper.cutRoundDouble(bonus),
							relayRaceActivity.getLeagueBonusExpiryDay(), relayMember.getUserId());
				}
				// 设置勋章
				setUserMedal(relayRaceActivity.getLeagueMedal(), relayMember.getUserId());
			}
			// 改变本次活动队伍的状态
			updateSettleByActivityId(relayTeam.getActivityId(), -1);
			relayTeam.setSettle(1);
			row = update(relayTeam);
		} else {
			row = -1;
		}
		return row;
	}

	private void setUserMedal(Long medalId, Long userId) {
		UserMedal userMedal = new UserMedal();
		userMedal.setMedalId(medalId);
		userMedal.setUserId(userId);
		userMedal.setCreateTime(System.currentTimeMillis());
		userMedalServiceImpl.save(userMedal);
	}

	private void setCouponAndUserCoupon(Long activityId, Double bonus, Integer expiryDay, Long userId) {
		Coupon coupon = new Coupon();
		coupon.setActivityId(activityId);
		coupon.setBonus(bonus);
		coupon.setType(12);
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
			userCoupon.setCategory(4);
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DATE, expiryDay);
			userCoupon.setExpiryTime(calendar.getTime().getTime());
			userCouponServiceImpl.save(userCoupon);
		}
	}

	@Override
	public Double getRelayTeamSumDistance(Long teamId, Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (teamId != null) {
			map.put("teamId", teamId);
		}
		if (activityId != null) {
			map.put("activityId", activityId);
		}
		if (userId != null) {
			map.put("userId", userId);
		}
		return repository.getRelayTeamSumDistance(map);
	}

	@Override
	public List<RelayTeam> getRelayTeamByActivityId(Long activityId) {

		return repository.getRelayTeamByActivityId(activityId);
	}

	@Override
	public Integer updateSettleByActivityId(Long activityId, Integer settle) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("settle", settle);
		return repository.updateSettleByActivityId(map);
	}

	@Override
	public Integer countRelayTeamNumByActivityId(Long activityId) {

		return repository.countRelayTeamNumByActivityId(activityId);
	}

	@Override
	public Double getBonusPool(Long activityId) {
		Integer num = countRelayTeamNumByActivityId(activityId);
		RelayRaceActivity byActivityId = relayRaceActivityServiceImpl.getByActivityId(activityId);
		if (num == null) {
			num = 0;
		}
		Double contributeBonus = 0.00;
		if (byActivityId != null) {
			if (byActivityId.getContributeBonus() != null) {
				contributeBonus = byActivityId.getContributeBonus();
			}
		}
		return ArithHelper.mul(num, contributeBonus);
	}

}
