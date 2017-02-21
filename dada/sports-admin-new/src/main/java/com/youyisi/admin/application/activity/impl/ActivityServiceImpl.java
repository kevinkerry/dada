package com.youyisi.admin.application.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.coupon.impl.CouponServiceImpl;
import com.youyisi.admin.application.hugthigh.impl.HugThighActivityServiceImpl;
import com.youyisi.admin.application.invitefriendactivity.impl.InviteFriendActivityServiceImpl;
import com.youyisi.admin.application.relay.impl.RelayLineServiceImpl;
import com.youyisi.admin.application.relay.impl.RelayRaceActivityServiceImpl;
import com.youyisi.admin.application.snatch.impl.SnatchActivityServiceImpl;
import com.youyisi.admin.application.snatch.impl.SnatchFeeServiceImpl;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.ActivityRepository;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivity;
import com.youyisi.admin.domain.relay.RelayLine;
import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.admin.domain.snatch.SnatchFee;
import com.youyisi.admin.infrastructure.helper.DateHelper;
import com.youyisi.admin.infrastructure.helper.quartz.ActivityQuartzJob;
import com.youyisi.admin.infrastructure.helper.quartz.MyJob;
import com.youyisi.admin.infrastructure.helper.quartz.QuartzManager;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository repository;

	@Autowired
	private HugThighActivityServiceImpl hActivityServiceImpl;

	@Autowired
	private InviteFriendActivityServiceImpl inviteFriendActivityServiceImpl;

	@Autowired
	private RelayRaceActivityServiceImpl relayRaceActivityServiceImpl;

	@Autowired
	private SnatchActivityServiceImpl snatchActivityServiceImpl;

	@Autowired
	private SnatchFeeServiceImpl snatchFeeServiceImpl;

	@Autowired
	private RelayLineServiceImpl relayLineServiceImpl;

	@Autowired
	private CouponServiceImpl couponServiceImpl;

	@Override
	public Activity get(Long id) {
		return repository.get(id);
	}

	@Override
	public Activity save(Activity entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Activity entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Activity entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Activity> queryPage(Page<Activity> page) {
		return repository.queryPage(page);
	}

	private void addPushJob(HugThighActivity activity) {
		MyJob job = new MyJob();
		job.setCronExpression(activity.getCronExpression());
		job.setJobGroup("activity");
		job.setJobName(activity.getActivityId() + "activitywillbegin");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", activity.getMessage());
		params.put("content", activity.getMessage());
		job.setParams(params);
		QuartzManager.addJob(job, ActivityQuartzJob.class);
	}

	@Override
	public boolean validateDate(Integer type, Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("date", date);
		Integer row = repository.validateDate(map);
		if (row > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Integer delActivity(Long activityId) {
		Integer row = 0;
		Activity activity = get(activityId);
		if (activity != null) {
			// 判断未开始的活动
			if (activity.getDate() > DateHelper.currentDateForDay()) {
				// 判断是即将开始的活动
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("type", activity.getType());
				map.put("date", DateHelper.currentDateForDay());
				Activity ay = repository.validateActivity(map);
				if (ay != null) {
					if (activity.getId() == ay.getId()) {
						return -1;
					}
				}
				row += delete(activity);
				if (activity.getType() == 1) {
					row += hActivityServiceImpl.delByActivityId(activity.getId());
				}
				if (activity.getType() == 2) {
					row += inviteFriendActivityServiceImpl.delByActivityId(activity.getId());
				}
				if (activity.getType() == 3) {
					row += relayRaceActivityServiceImpl.delByActivityId(activity.getId());
				}
				if (activity.getType() == 4) {
					row += snatchActivityServiceImpl.delByActivityId(activity.getId());
					row += snatchFeeServiceImpl.delByActivityId(activity.getId());
				}
				if (activity.getType() == 1 || activity.getType() == 2) {
					row += couponServiceImpl.delByActivityId(activity.getId());
				}
			}

		}
		return row;
	}

	@Override
	public Integer addHugThigh(Activity activity) {
		Integer row = 0;
		activity.setType(1);
		activity.setTitle("每日抱大腿" + DateUtil.timestampToStr(activity.getDate() * 1000l));
		activity.setCreateTime(System.currentTimeMillis());
		activity.setUpdateTime(System.currentTimeMillis());
		activity.setStatus(0);
		activity.setHot(0l);
		Activity ay = repository.save(activity);

		if (ay.getId() != null) {
			row = 1;
			HugThighActivity hugThighActivity = activity.getHugThighActivity();
			hugThighActivity.setActivityId(ay.getId());
			hugThighActivity.setCreateTime(System.currentTimeMillis());
			hugThighActivity.setUpdateTime(System.currentTimeMillis());
			hugThighActivity.setCronExpression(DateUtil.dateTimeToCron(hugThighActivity.getCronExpression()));
			HugThighActivity ha = hActivityServiceImpl.save(hugThighActivity);
			if (ha.getId() != null) {
				row += 1;
			}
			List<Coupon> couponsList = activity.getCouponsList();
			Coupon cn = null;
			for (Coupon coupon : couponsList) {
				coupon.setActivityId(ay.getId());
				cn = couponServiceImpl.save(coupon);
				if (cn.getId() != null) {
					row += 1;
				}
			}

			addPushJob(hugThighActivity);
		}

		return row;
	}

	@Override
	public Integer addInviteFriend(Activity activity) {
		Integer row = 0;
		activity.setType(2);
		activity.setTitle("邀请注册" + DateUtil.timestampToStr(activity.getBeginTime()));
		activity.setCreateTime(System.currentTimeMillis());
		activity.setUpdateTime(System.currentTimeMillis());
		activity.setStatus(0);
		activity.setHot(0l);
		Activity ay = repository.save(activity);
		if (ay.getId() != null) {
			row = 1;
			InviteFriendActivity invite = activity.getInviteFriendActivity();
			invite.setActivityId(ay.getId());
			invite.setCreateTime(System.currentTimeMillis());
			invite.setUpdateTime(System.currentTimeMillis());
			invite.setCronExpression(DateUtil.dateTimeToCron(invite.getCronExpression()));
			InviteFriendActivity ia = inviteFriendActivityServiceImpl.save(invite);
			if (ia.getId() != null) {
				row += 1;
			}
			List<Coupon> couponsList = activity.getCouponsList();
			Coupon cn = null;
			for (Coupon coupon : couponsList) {
				coupon.setActivityId(ay.getId());
				cn = couponServiceImpl.save(coupon);
				if (cn.getId() != null) {
					row += 1;
				}
			}
			addPushJob(invite);
		}
		return row;
	}

	private void addPushJob(InviteFriendActivity activity) {
		MyJob job = new MyJob();
		job.setCronExpression(activity.getCronExpression());
		job.setJobGroup("activity");
		job.setJobName(activity.getActivityId() + "activitywillbegin");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", activity.getMessage());
		params.put("content", activity.getMessage());
		job.setParams(params);
		QuartzManager.addJob(job, ActivityQuartzJob.class);
	}

	@Override
	public Integer updateHugThigh(Activity activity) {
		Integer row = 0;
		if (activity.getId() != null) {
			Activity at = get(activity.getId());
			if (at != null) {
				at.setBeginTime(activity.getBeginTime());
				at.setCover(activity.getCover());
				at.setEndTime(activity.getEndTime());
				at.setUpdateTime(System.currentTimeMillis());
				row += update(at);
			}
		}
		HugThighActivity hugThighActivity = activity.getHugThighActivity();
		if (hugThighActivity.getId() != null) {
			HugThighActivity ha = hActivityServiceImpl.get(hugThighActivity.getId());
			if (ha != null) {
				ha.setAvspeed(hugThighActivity.getAvspeed());
				String cronExpression = hugThighActivity.getCronExpression().replace("T", " ") + ":00";
				ha.setCronExpression(DateUtil.dateTimeToCron(cronExpression));
				ha.setHugThighLimit(hugThighActivity.getHugThighLimit());
				ha.setThighLimit(hugThighActivity.getThighLimit());
				ha.setMaxspeed(hugThighActivity.getMaxspeed());
				ha.setMessage(hugThighActivity.getMessage());
				ha.setRule(hugThighActivity.getRule());
				ha.setStride(hugThighActivity.getStride());
				ha.setUpdateTime(System.currentTimeMillis());
				row += hActivityServiceImpl.update(ha);
			}
		}
		List<Coupon> couponsList = activity.getCouponsList();
		Coupon cn = null;
		for (Coupon coupon : couponsList) {
			if (coupon.getId() != null) {
				cn = couponServiceImpl.get(coupon.getId());
				cn.setAnnualYield(coupon.getAnnualYield());
				cn.setBonus(coupon.getBonus());
				cn.setCommission(coupon.getCommission());
				cn.setCommissionRate(coupon.getCommissionRate());
				cn.setDistance(coupon.getDistance());
				cn.setExpiryDay(coupon.getExpiryDay());
				row += couponServiceImpl.update(cn);
			}
		}
		return row;
	}

	@Override
	public Integer updateInviteFriend(Activity activity) {
		Integer row = 0;
		if (activity.getId() != null) {
			Activity at = get(activity.getId());
			if (at.getBeginTime() > System.currentTimeMillis()) {
				if (at != null) {
					at.setBeginTime(activity.getBeginTime());
					at.setCover(activity.getCover());
					at.setEndTime(activity.getEndTime());
					at.setUpdateTime(System.currentTimeMillis());
					row += update(at);
				}

				InviteFriendActivity ia = activity.getInviteFriendActivity();
				if (ia.getId() != null) {
					InviteFriendActivity ifa = inviteFriendActivityServiceImpl.get(ia.getId());
					if (ifa != null) {
						if (ia.getCronExpression() != null) {
							ifa.setCronExpression(DateUtil.dateTimeToCron(ia.getCronExpression()));
						}
						ifa.setMessage(ia.getMessage());
						ifa.setRule(ia.getRule());
						ifa.setUpdateTime(System.currentTimeMillis());
						row += inviteFriendActivityServiceImpl.update(ifa);
					}
				}

				List<Coupon> couponsList = activity.getCouponsList();
				Coupon cn = null;
				for (Coupon coupon : couponsList) {
					if (coupon.getId() != null) {
						cn = couponServiceImpl.get(coupon.getId());
						cn.setBonus(coupon.getBonus());
						cn.setExpiryDay(coupon.getExpiryDay());
						row += couponServiceImpl.update(cn);
					}
				}
			} else {
				row = -1;
			}
		}
		return row;
	}

	@Override
	public Integer addRelayRace(Activity activity) {
		Integer row = 0;
		activity.setType(3);
		activity.setCreateTime(System.currentTimeMillis());
		activity.setUpdateTime(System.currentTimeMillis());
		activity.setStatus(0);
		activity.setHot(0l);
		Activity ay = repository.save(activity);
		if (ay.getId() != null) {
			row = 1;
			RelayRaceActivity relayRaceActivity = activity.getRelayRaceActivity();
			relayRaceActivity.setActivityId(ay.getId());
			relayRaceActivity.setCreateTime(System.currentTimeMillis());
			relayRaceActivity.setUpdateTime(System.currentTimeMillis());
			relayRaceActivity.setCronExpression(DateUtil.dateTimeToCron(relayRaceActivity.getCronExpression()));
			RelayRaceActivity ra = relayRaceActivityServiceImpl.save(relayRaceActivity);
			if (ra.getId() != null) {
				row += 1;
			}

			List<RelayLine> relayLineList = activity.getRelayLineList();
			if (relayLineList != null) {
				for (RelayLine relayLine : relayLineList) {
					if (relayLine.getAddress() != null && relayLine.getDistance() != null) {
						relayLine.setActivityId(ay.getId());
						relayLineServiceImpl.save(relayLine);
					}
				}
			}

			addPushJob(relayRaceActivity);
		}
		return row;
	}

	private void addPushJob(RelayRaceActivity activity) {
		MyJob job = new MyJob();
		job.setCronExpression(activity.getCronExpression());
		job.setJobGroup("activity");
		job.setJobName(activity.getActivityId() + "activitywillbegin");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", activity.getMessage());
		params.put("content", activity.getMessage());
		job.setParams(params);
		QuartzManager.addJob(job, ActivityQuartzJob.class);
	}

	@Override
	public Integer updateRelayRace(Activity activity) {
		Integer row = 0;
		if (activity.getId() != null) {
			Activity at = get(activity.getId());
			if (at.getBeginTime() > System.currentTimeMillis()) {
				if (at != null) {
					at.setTitle(activity.getTitle());
					at.setBeginTime(activity.getBeginTime());
					at.setCover(activity.getCover());
					at.setEndTime(activity.getEndTime());
					at.setUpdateTime(System.currentTimeMillis());
					row += update(at);
				}
				RelayRaceActivity ra = activity.getRelayRaceActivity();
				if (ra.getId() != null) {
					RelayRaceActivity rra = relayRaceActivityServiceImpl.get(ra.getId());
					if (rra != null) {
						if (ra.getCronExpression() != null) {
							rra.setCronExpression(DateUtil.dateTimeToCron(ra.getCronExpression()));
						}
						rra.setActivityMedal(ra.getActivityMedal());
						rra.setContributeBonus(ra.getContributeBonus());
						rra.setFirstBonus(ra.getFirstBonus());
						rra.setFirstFee(ra.getFirstFee());
						rra.setInviteExpiryDay(ra.getInviteExpiryDay());
						rra.setLeagueBonusExpiryDay(ra.getLeagueBonusExpiryDay());
						rra.setLeagueMedal(ra.getLeagueMedal());
						rra.setLevelLimit(ra.getLevelLimit());
						rra.setMaxDistance(ra.getMaxDistance());
						rra.setMaxStep(ra.getMaxStep());
						rra.setOtherBonus(ra.getOtherBonus());
						rra.setOtherFee(ra.getOtherFee());
						rra.setRelayBatonLimit(ra.getRelayBatonLimit());
						rra.setStepToDistance(ra.getStepToDistance());
						rra.setTeamLeaderMedal(ra.getTeamLeaderMedal());
						rra.setTeamLimit(ra.getTeamLimit());
						rra.setMessage(ra.getMessage());
						rra.setRule(ra.getRule());
						rra.setUpdateTime(System.currentTimeMillis());
						rra.setPushCount(ra.getPushCount());
						row += relayRaceActivityServiceImpl.update(rra);
					}
				}

				relayLineServiceImpl.delByActivityId(at.getId());
				List<RelayLine> relayLineList = activity.getRelayLineList();
				if (relayLineList != null) {
					for (RelayLine relayLine : relayLineList) {
						relayLine.setId(null);
						if (relayLine.getAddress() != null && relayLine.getDistance() != null) {
							relayLine.setActivityId(at.getId());
							relayLineServiceImpl.save(relayLine);
						}
					}
				}

			} else {
				row = -1;
			}
		}
		return row;
	}

	@Override
	public String getCorrelationActivityName(Long medalId) {
		StringBuilder str = new StringBuilder();
		String tiles = null;
		List<Activity> list = repository.getCorrelationActivityName(medalId);
		if (list != null && list.size() > 0) {
			int i = 0;
			for (Activity activity : list) {
				if (i < 3) {
					str.append(activity.getTitle()).append(",");
				}
				i++;
			}
			tiles = str.substring(0, str.length() - 1);
		}
		return tiles;
	}

	@Override
	public Integer addSnatchActivity(Activity activity) {
		Integer row = 0;
		activity.setType(4);
		activity.setCreateTime(System.currentTimeMillis());
		activity.setUpdateTime(System.currentTimeMillis());
		activity.setStatus(0);
		activity.setHot(0l);
		Activity ay = repository.save(activity);
		if (ay.getId() != null) {
			row = 1;
			SnatchActivity sa = activity.getSnatchActivity();
			sa.setActivityId(ay.getId());
			sa.setActivityNum(DateUtil.dateTimeToDate(activity.getBeginTime()));
			sa.setCreateTime(System.currentTimeMillis());
			sa.setUpdateTime(System.currentTimeMillis());
			sa.setSettle(0);
			sa.setCronExpression(DateUtil.dateTimeToCron(sa.getCronExpression()));
			SnatchActivity snatchActivity = snatchActivityServiceImpl.save(sa);
			if (snatchActivity.getId() != null) {
				row += 1;
			}

			List<SnatchFee> snatchFeeList = activity.getSnatchFeeList();
			if (snatchFeeList != null) {
				for (SnatchFee snatchFee : snatchFeeList) {
					if (snatchFee.getCount() != null && snatchFee.getMoney() != null) {
						snatchFee.setActivityId(ay.getId());
						snatchFeeServiceImpl.save(snatchFee);
					}
				}
			}

			addPushJob(snatchActivity);
		}
		return row;

	}

	private void addPushJob(SnatchActivity activity) {
		MyJob job = new MyJob();
		job.setCronExpression(activity.getCronExpression());
		job.setJobGroup("activity");
		job.setJobName(activity.getActivityId() + "activitywillbegin");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", activity.getMessage());
		params.put("content", activity.getMessage());
		job.setParams(params);
		QuartzManager.addJob(job, ActivityQuartzJob.class);
	}

	@Override
	public Integer updateSnatchActivity(Activity activity) {
		Integer row = 0;
		if (activity.getId() != null) {
			Activity at = get(activity.getId());
			if (at.getBeginTime() > System.currentTimeMillis()) {
				if (at != null) {
					at.setTitle(activity.getTitle());
					at.setBeginTime(activity.getBeginTime());
					at.setCover(activity.getCover());
					at.setEndTime(activity.getEndTime());
					at.setUpdateTime(System.currentTimeMillis());
					row += update(at);
				}
				SnatchActivity sa = activity.getSnatchActivity();
				if (sa.getId() != null) {
					SnatchActivity snatchActivity = snatchActivityServiceImpl.get(sa.getId());
					if (snatchActivity != null) {
						if (sa.getCronExpression() != null) {
							snatchActivity.setCronExpression(DateUtil.dateTimeToCron(sa.getCronExpression()));
						}
						snatchActivity.setActivityNum(DateUtil.dateTimeToDate(activity.getBeginTime()));
						snatchActivity.setContributeBonus(sa.getContributeBonus());
						snatchActivity.setExpiryDay(sa.getExpiryDay());
						snatchActivity.setInitialBonus(sa.getInitialBonus());
						snatchActivity.setMaxDistance(sa.getMaxDistance());
						snatchActivity.setMaxStep(sa.getMaxStep());
						snatchActivity.setMessage(sa.getMessage());
						snatchActivity.setMinNum(sa.getMinNum());
						snatchActivity.setRule(sa.getRule());
						snatchActivity.setStepToDistance(sa.getStepToDistance());
						snatchActivity.setWinMedal(sa.getWinMedal());
						snatchActivity.setWinNum(sa.getWinNum());
						snatchActivity.setUpdateTime(System.currentTimeMillis());
						snatchActivity.setActivityMedal(sa.getActivityMedal());
						row += snatchActivityServiceImpl.update(snatchActivity);
					}
				}
				snatchFeeServiceImpl.delByActivityId(activity.getId());
				List<SnatchFee> snatchFeeList = activity.getSnatchFeeList();
				for (SnatchFee snatchFee : snatchFeeList) {
					snatchFee.setId(null);
					if (snatchFee.getCount() != null && snatchFee.getMoney() != null) {
						snatchFee.setActivityId(activity.getId());
						snatchFeeServiceImpl.save(snatchFee);
					}
				}
			} else {
				row = -1;
			}
		}
		return row;
	}
}
