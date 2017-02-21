package com.youyisi.admin.application.relay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.coupon.impl.CouponServiceImpl;
import com.youyisi.admin.application.relay.RelayMemberService;
import com.youyisi.admin.application.run.impl.RunServiceImpl;
import com.youyisi.admin.application.step.impl.StepServiceImpl;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.distance.DistanceRepository;
import com.youyisi.admin.domain.relay.RelayMember;
import com.youyisi.admin.domain.relay.RelayMemberRepository;
import com.youyisi.admin.domain.relay.RelayMemberSports;
import com.youyisi.admin.domain.relay.RelayTeam;
import com.youyisi.admin.domain.run.Run;
import com.youyisi.admin.domain.step.Step;
import com.youyisi.admin.domain.step.StepRepository;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Service
public class RelayMemberServiceImpl implements RelayMemberService {

	@Autowired
	private RelayMemberRepository repository;

	@Autowired
	private RelayTeamServiceImpl relayTeamServiceImpl;

	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private CouponServiceImpl couponServiceImpl;

	@Autowired
	private RunServiceImpl runServiceImpl;

	@Autowired
	private StepServiceImpl stepServiceImpl;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Autowired
	private RelayMemberFavourServiceImpl relayMemberFavourServiceImpl;

	private Integer topThreadNum = 0;
	private List<RelayMember> otherTapeoutList = null;
	private Integer otherTapeoutNum = 0;

	@Override
	public RelayMember get(Long id) {
		return repository.get(id);
	}

	@Override
	public RelayMember save(RelayMember entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RelayMember entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayMember entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RelayMember> queryPage(Page<RelayMember> page) {
		Long activityId = (Long) page.getParams().get("activityId");
		Page<RelayMember> queryPage = repository.queryPage(page);
		List<RelayMember> result = queryPage.getResult();
		for (RelayMember relayMember : result) {
			// 设置累计奖金
			Double sumRelayRaceMoney = couponServiceImpl.sumRelayRaceMoney(activityId, relayMember.getUserId());
			if (sumRelayRaceMoney == null) {
				sumRelayRaceMoney = 0.00;
			}
			relayMember.setCountBonus(sumRelayRaceMoney);
			// 设置冠军奖金
			relayMember.setBonusFirst(couponServiceImpl.getBonusByFirst(activityId, relayMember.getUserId()));
			// 设置未使用体验金
			relayMember.setUnusedMoney(couponServiceImpl.sumUnusedMoney(activityId, relayMember.getUserId()));
			// 设置所属战队
			RelayTeam relayTeam = relayTeamServiceImpl.get(relayMember.getTeamId());
			relayMember.setTeamName(relayTeam.getName());
		}
		return queryPage;
	}

	public Double getSumDistance(Long userId, Activity activity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId", userId);
		map.put("maxDistance", activity.getRelayRaceActivity().getMaxDistance());
		map.put("maxStep", activity.getRelayRaceActivity().getMaxStep());
		Double distance = distanceRepository.getUserDistance(map);
		if (distance == null) {
			distance = 0.0;
		}
		Integer step = stepRepository.getUserStep(map);
		if (step == null) {
			step = 0;
		}
		Double _step = step * 1.0 / activity.getRelayRaceActivity().getStepToDistance();
		return ArithHelper.add(distance, _step);
	}

	private Double getSumStep(Long userId, Activity activity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId", userId);
		map.put("maxDistance", activity.getRelayRaceActivity().getMaxDistance());
		map.put("maxStep", activity.getRelayRaceActivity().getMaxStep());
		Integer step = stepRepository.getUserStep(map);
		if (step == null) {
			step = 0;
		}
		Double _step = step * 1.0 / activity.getRelayRaceActivity().getStepToDistance();
		return _step;
	}

	@Override
	public Integer countRelayMemberNumber(Long activityId) {

		return repository.countRelayMemberNumber(activityId);
	}

	@Override
	public List<RelayMember> getRelayMemberByTeamId(Long teamId) {

		return repository.getRelayMemberByTeamId(teamId);
	}

	@Override
	public List<RelayMember> getRelayMemberList(Long activityId, Long id, Long parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (activityId != null) {
			map.put("activityId", activityId);
		}
		if (id != null) {
			map.put("id", id);
		}
		if (parentId != null) {
			map.put("parentId", parentId);
		}
		return repository.getRelayMemberList(map);
	}

	@Override
	public Integer countRelayMember(Long activityId, Long parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("parentId", parentId);
		return repository.countRelayMember(map);
	}

	@Override
	public List<RelayMember> getRelayMemberByActivityId(Long activityId) {

		return repository.getRelayMemberByActivityId(activityId);
	}

	@Override
	public Page<RelayMember> queryPageMemberlist(Page<RelayMember> page) {
		Long activityId = (Long) page.getParams().get("activityId");
		Page<RelayMember> queryPage = repository.queryPage(page);
		List<RelayMember> result = queryPage.getResult();
		for (RelayMember relayMember : result) {
			// 设置累计奖金
			Double sumRelayRaceMoney = couponServiceImpl.sumRelayRaceMoney(activityId, relayMember.getUserId());
			if (sumRelayRaceMoney == null) {
				sumRelayRaceMoney = 0.00;
			}
			relayMember.setCountBonus(ArithHelper.cutRoundDouble(sumRelayRaceMoney));
			Double bonusByFirst = couponServiceImpl.getBonusByFirst(activityId, relayMember.getUserId());
			if (bonusByFirst == null) {
				bonusByFirst = 0.00;
			}
			// 设置冠军奖金
			relayMember.setBonusFirst(ArithHelper.cutRoundDouble(bonusByFirst));

			// 上线人数
			topThreadNum = 0;
			if (relayMember.getParentId() > 0) {
				relayMember.setTopThreadNum(getTopThreadNum(activityId, relayMember.getParentId()));
			} else {
				relayMember.setTopThreadNum(topThreadNum);
			}

			relayMember.setPraise(relayMemberFavourServiceImpl.countNum(relayMember.getId(), 1));
			relayMember.setStimulate(relayMemberFavourServiceImpl.countNum(relayMember.getId(), 2));

			// 一级下线
			relayMember.setStairTapeout(getStairTapeout(activityId, relayMember.getId()));

			// 其他下线
			otherTapeoutNum = 0;
			if (otherTapeoutList != null && otherTapeoutList.size() > 0) {
				Integer otherTapeoutNum2 = 0;
				for (RelayMember r2 : otherTapeoutList) {
					otherTapeoutNum2 += getOtherTapeout(activityId, r2.getId());
					otherTapeoutNum = 0;
				}
				relayMember.setOtherTapeout(otherTapeoutNum2);
			} else {
				relayMember.setOtherTapeout(otherTapeoutNum);
			}

		}
		return queryPage;
	}

	private Integer getOtherTapeout(Long activityId, Long id) {
		List<RelayMember> relayMemberList = getRelayMemberList(activityId, null, id);
		if (relayMemberList != null && relayMemberList.size() > 0) {
			otherTapeoutNum += relayMemberList.size();
			for (RelayMember relayMember : relayMemberList) {
				getOtherTapeout(activityId, relayMember.getId());
			}
		}
		return otherTapeoutNum;
	}

	private Integer getStairTapeout(Long activityId, Long id) {
		Integer num = 0;
		otherTapeoutList = getRelayMemberList(activityId, null, id);
		if (otherTapeoutList != null && otherTapeoutList.size() > 0) {
			num = otherTapeoutList.size();
		}
		return num;
	}

	private Integer getTopThreadNum(Long activityId, Long parentId) {
		if (parentId > 0) {
			List<RelayMember> relayMemberList = getRelayMemberList(activityId, parentId, null);
			if (relayMemberList != null && relayMemberList.size() > 0) {
				topThreadNum += relayMemberList.size();
				for (RelayMember relayMember : relayMemberList) {
					getTopThreadNum(activityId, relayMember.getParentId());
				}
			}
		}
		return topThreadNum;
	}

	@Override
	public Page<RelayMember> queryPageSportslist(Page<RelayMember> page) {
		Long activityId = (Long) page.getParams().get("activityId");
		Page<RelayMember> queryPage = repository.queryPage(page);
		List<RelayMember> result = queryPage.getResult();
		RelayMemberSports rms = null;
		Run sumDistance = null;
		Step sumStep = null;
		Activity activity = activityServiceImpl.get(activityId);
		for (RelayMember relayMember : result) {
			rms = new RelayMemberSports();
			sumDistance = runServiceImpl.sumDistance(relayMember.getUserId(), 1,
					DateUtil.getDateForDay(activity.getBeginTime()), DateUtil.getDateForDay(activity.getEndTime()));
			if (sumDistance != null) {
				rms.setDistanceSN(ArithHelper.cutRoundDouble(sumDistance.getDistance()));
				rms.setDistanceTimeSN(sumDistance.getCreateTime());
			} else {
				rms.setDistanceSN(0.00);
			}
			sumDistance = runServiceImpl.sumDistance(relayMember.getUserId(), 2,
					DateUtil.getDateForDay(activity.getBeginTime()), DateUtil.getDateForDay(activity.getEndTime()));
			if (sumDistance != null) {
				if (sumDistance.getDistance() != null) {
					rms.setDistanceSW(ArithHelper.cutRoundDouble(sumDistance.getDistance()));
					rms.setDistanceTimeSW(sumDistance.getCreateTime());
				}
			} else {
				rms.setDistanceSW(0.00);
			}
			sumStep = stepServiceImpl.sumStep(relayMember.getUserId(), DateUtil.getDateForDay(activity.getBeginTime()),
					DateUtil.getDateForDay(activity.getEndTime()));
			if (sumStep != null) {
				rms.setStep(sumStep.getStep());
				rms.setStepTime(sumStep.getCreateTime());
			}
			Double add = 0.0;
			if (rms.getDistanceSN() != null && rms.getDistanceSW() != null) {
				add = ArithHelper.add(rms.getDistanceSN(), rms.getDistanceSW());
			}
			Double ss = getSumStep(relayMember.getUserId(), activity);
			rms.setDistanceSum(ArithHelper.cutRoundDouble(ArithHelper.add(add, ss)));

			relayMember.setRelayMemberSports(rms);
		}
		return queryPage;
	}

}
