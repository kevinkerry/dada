package com.youyisi.admin.application.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.annual.impl.AnnualYieldServiceImpl;
import com.youyisi.admin.application.coupon.impl.CouponServiceImpl;
import com.youyisi.admin.application.distance.impl.DistanceServiceImpl;
import com.youyisi.admin.application.experience.impl.ExperienceAccountServiceImpl;
import com.youyisi.admin.application.run.impl.RunServiceImpl;
import com.youyisi.admin.application.step.impl.StepServiceImpl;
import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.application.wallet.impl.WalletServiceImpl;
import com.youyisi.admin.application.withdraw.impl.WithdrawServiceImpl;
import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.experience.ExperienceAccount;
import com.youyisi.admin.domain.managemoney.ManageMoney;
import com.youyisi.admin.domain.sportrecord.SportRecord;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserInvite;
import com.youyisi.admin.domain.user.UserRepository;
import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.helper.DateHelper;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-16
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private WalletServiceImpl walletServiceImpl;

	@Autowired
	private AnnualYieldServiceImpl annualYieldServiceImpl;

	@Autowired
	private ExperienceAccountServiceImpl experienceAccountServiceImpl;

	@Autowired
	private WithdrawServiceImpl withdrawServiceImpl;

	@Autowired
	private StepServiceImpl stepServiceImpl;

	@Autowired
	private DistanceServiceImpl distanceServiceImpl;

	@Autowired
	private RunServiceImpl runServiceImpl;

	@Autowired
	private CouponServiceImpl couponServiceImpl;

	@Override
	public User get(Long id) {
		return repository.get(id);
	}

	@Override
	public User save(User entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(User entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(User entity) {
		return repository.update(entity);
	}

	@Override
	public Page<User> queryPage(Page<User> page) {
		return repository.queryPage(page);
	}

	@Override
	public Page<User> queryPageUserList(Page<User> page) {
		page = repository.queryPageUserList(page);
		return page;
	}

	@Override
	public Integer countUser(Long beginTime, Long endTime, Integer clientType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (beginTime != null && endTime != null) {
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
		}
		if (clientType != null) {
			map.put("clientType", clientType);
		}
		return repository.countUser(map);
	}

	@Override
	public Page<User> queryPagePushUser(Integer currentPage, Integer pageSize, Integer clientType) {
		Page<User> page = new Page<User>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(1000);
		}
		if (clientType != null && clientType != 0) {
			page.addParam("clientType", clientType);
		}
		return repository.queryPagePushUser(page);
	}

	@Override
	public ManageMoney getManageMoney(Long userId) {
		ManageMoney manageMoney = new ManageMoney();
		Wallet wallet = walletServiceImpl.getByUserId(userId);
		if (wallet != null) {
			manageMoney.setTotalAsset(wallet.getTotalAsset());
			manageMoney.setPrincipal(wallet.getPrincipal());
			manageMoney.setEarningsBalance(ArithHelper.sub(wallet.getTotalAsset(), wallet.getPrincipal()));
			manageMoney.setIncome(wallet.getIncome());
		}

		AnnualYield annualYield = annualYieldServiceImpl.getByUserAndDate(userId, DateHelper.currentDateForDay());
		if (annualYield != null) {
			manageMoney.setAnnualYield(annualYield.getAnnualYield());
		}

		ExperienceAccount experienceAccount = experienceAccountServiceImpl.getByUserId(userId);
		if (experienceAccount != null) {
			if (experienceAccount.getExpiryTime() > System.currentTimeMillis()) {
				manageMoney.setExperienceMoney(experienceAccount.getMoney().toString());
			} else {
				manageMoney.setExperienceMoney("0.0");
			}
		} else {
			manageMoney.setExperienceMoney("0.0");
		}
		Double sumWithdraw = withdrawServiceImpl.sumWithdraw(userId, 0);
		if (sumWithdraw != null) {
			manageMoney.setStayWithdraw(sumWithdraw);
		} else {
			manageMoney.setStayWithdraw(0.0);
		}

		return manageMoney;
	}

	@Override
	public SportRecord getSportRecord(Long userId) {
		SportRecord sportRecord = new SportRecord();
		Long currentDateForDay = DateUtil.currentDateForDay();
		Integer todayStep = stepServiceImpl.countStepByUserId(userId, currentDateForDay);
		if (todayStep == null) {
			todayStep = 0;
		}
		sportRecord.setTodayStep(todayStep);

		Double todayDistance = distanceServiceImpl.countDistanceByUserId(userId, currentDateForDay);
		if (todayDistance == null) {
			todayDistance = 0.0;
		}
		sportRecord.setTodayDistance(todayDistance);

		Integer todayDistanceTime = runServiceImpl.countRunByTotalTime(userId, currentDateForDay);
		if (todayDistanceTime != null) {
			todayDistanceTime = todayDistanceTime / 60;
			if (todayDistanceTime < 0) {
				todayDistanceTime = 0;
			}
		} else {
			todayDistanceTime = 0;
		}
		sportRecord.setTodayDistanceTime(String.valueOf(todayDistanceTime));

		Integer countStep = stepServiceImpl.countStepByUserId(userId, null);
		sportRecord.setCountStep(countStep);

		Double countDistance = distanceServiceImpl.countDistanceByUserId(userId, null);
		sportRecord.setCountDistance(countDistance);

		Integer countDistanceTime = runServiceImpl.countRunByTotalTime(userId, null);
		if (countDistanceTime != null) {
			countDistanceTime = countDistanceTime / 60;
		} else {
			countDistanceTime = 0;
		}
		sportRecord.setCountDistanceTime(String.valueOf(countDistanceTime));

		Integer countStepNum5000 = stepServiceImpl.countStepNum(userId, null, 5000);
		sportRecord.setStep5000DN(countStepNum5000);

		Integer countStepNum5000_9999 = stepServiceImpl.countStepNum(userId, 5000, 9999);
		sportRecord.setStep5000_9999(countStepNum5000_9999);

		Integer countStepNum10000_14999 = stepServiceImpl.countStepNum(userId, 10000, 14999);
		sportRecord.setStep10000_14999(countStepNum10000_14999);

		Integer countStepNum15000_19999 = stepServiceImpl.countStepNum(userId, 15000, 19999);
		sportRecord.setStep15000_19999(countStepNum15000_19999);

		Integer countStepNum20000 = stepServiceImpl.countStepNum(userId, 20000, null);
		sportRecord.setStep20000UP(countStepNum20000);

		Integer countDistanceNum0_3 = distanceServiceImpl.countDistanceNum(userId, 0, 3);
		sportRecord.setDistance0_3KM(countDistanceNum0_3);

		Integer countDistanceNum3_5 = distanceServiceImpl.countDistanceNum(userId, 3, 5);
		sportRecord.setDistance3_5KM(countDistanceNum3_5);

		Integer countDistanceNum5_8 = distanceServiceImpl.countDistanceNum(userId, 5, 8);
		sportRecord.setDistance5_8KM(countDistanceNum5_8);

		Integer countDistanceNum8_10 = distanceServiceImpl.countDistanceNum(userId, 8, 10);
		sportRecord.setDistance8_10KM(countDistanceNum8_10);

		Integer countDistanceNum10UP = distanceServiceImpl.countDistanceNum(userId, 10, null);
		sportRecord.setDistance10KMUP(countDistanceNum10UP);

		// 1：室内跑步，2：室外跑步
		Integer countRunByType1 = runServiceImpl.countRunByType(userId, 1);
		sportRecord.setSnCountRunning(countRunByType1);
		Integer countRunByType2 = runServiceImpl.countRunByType(userId, 2);
		sportRecord.setSwCountRunning(countRunByType2);
		return sportRecord;
	}

	@Override
	public List<User> getClientIdAndClientIdNum() {

		return repository.getClientIdAndClientIdNum();
	}

	@Override
	public Page<UserInvite> queryPageUserInvite(Page<UserInvite> page) {
		Long activityId = (Long) page.getParams().get("activityId");
		Page<UserInvite> queryPageUserInvite = repository.queryPageUserInvite(page);
		Coupon coupon = couponServiceImpl.getCouponByActivityIdAndType(activityId, 8);
		if (coupon != null) {
			List<UserInvite> result = queryPageUserInvite.getResult();
			for (UserInvite userInvite : result) {
				userInvite.setCountExperience(userInvite.getInviteNum() * coupon.getBonus());
			}
		}
		return queryPageUserInvite;
	}

	@Override
	public Integer countInviteNum(Long activityId) {
		Integer num = repository.countInviteNum(activityId);
		if (num == null) {
			num = 0;
		}
		return num;
	}

	@Override
	public Page<UserInvite> queryPageUserInviteDetail(Page<UserInvite> page) {
		return repository.queryPageUserInviteDetail(page);
	}

	@Override
	public Page<User> queryPageForPush(Page<User> page) {
		// TODO Auto-generated method stub
		return repository.queryPageForPush(page);
	}
}
