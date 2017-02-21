package com.youyisi.admin.interfaces.overview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.distance.DistanceService;
import com.youyisi.admin.application.step.StepService;
import com.youyisi.admin.application.thigh.ThighService;
import com.youyisi.admin.application.user.UserCouponService;
import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.application.wallet.WalletDetailService;
import com.youyisi.admin.domain.overview.Overview;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.helper.DateHelper;
import com.youyisi.admin.interfaces.BaseController;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Controller
@RequestMapping("/overview")
public class OverviewController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private WalletDetailService walletDetailService;

	@Autowired
	private DistanceService distanceService;

	@Autowired
	private StepService stepService;

	@Autowired
	private ThighService thighService;

	@Autowired
	private UserCouponService userCouponService;

	private Overview overview = new Overview();

	// 今日时间
	private Long todaybeginTime = null;
	private Long todayendTime = null;
	// 统计昨日
	private Long yesterdaybeginTime = null;
	private Long yesterdayendTime = null;
	// 统计7日
	private Long sevenDaysbeginTime = null;
	// 统计30日
	private Long thirtyDaysbeginTime = null;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		initDate();
		// 获取新增用户统计
		getNewUserCount(model);
		// 获取活跃用户统计
		getActiveUserCount(model);
		// 统计充值提现
		getWalletDetailCount(model);
		// 统计跑步
		getDistanceCount(model);
		// 统计抱大腿
		getHugThighCount(model);

		return "overview/list";
	}

	private void initDate() {
		// 今日时间
		todaybeginTime = DateHelper.getTimeScope(0, true);
		todayendTime = DateHelper.getTimeScope(0, false);
		// 统计昨日
		yesterdaybeginTime = DateHelper.getTimeScope(-1, true);
		yesterdayendTime = DateHelper.getTimeScope(-1, false);
		// 统计7日
		sevenDaysbeginTime = DateHelper.getTimeScope(-7, true);
		// 统计30日
		thirtyDaysbeginTime = DateHelper.getTimeScope(-30, true);
	}

	/**
	 * 统计充值提现
	 * 
	 * @param model
	 * @return Model
	 */
	private Model getWalletDetailCount(Model model) {
		// 累计充值 累计提现
		Double countPay = walletDetailService.countWallet(null, null, 1, null, null);
		Double countWithdraw = walletDetailService.countWallet(null, null, 2, null, null);
		if (countPay == null) {
			countPay = 0.0;
		}
		if (countWithdraw == null) {
			countWithdraw = 0.0;
		}
		model.addAttribute("countPay", countPay);
		model.addAttribute("countWithdraw", countWithdraw);

		overview.setCountPay(countPay);
		overview.setCountWithdraw(countWithdraw);

		// 统计今日充值次数
		Double todayWalletCsAndroid = walletDetailService.countWallet(todaybeginTime, todayendTime, 1, 1, 1);
		Double todayWalletCsIOS = walletDetailService.countWallet(todaybeginTime, todayendTime, 1, 2, 1);
		if (todayWalletCsAndroid == null) {
			todayWalletCsAndroid = 0.0;
		}
		if (todayWalletCsIOS == null) {
			todayWalletCsIOS = 0.0;
		}
		Double todayWalletCsSum = ArithHelper.add(todayWalletCsAndroid, todayWalletCsIOS);

		model.addAttribute("todayWalletCsAndroid", todayWalletCsAndroid.intValue());
		model.addAttribute("todayWalletCsIOS", todayWalletCsIOS.intValue());
		model.addAttribute("todayWalletCsSum", todayWalletCsSum.intValue());

		overview.setTodayWalletCsAndroid(todayWalletCsAndroid);
		overview.setTodayWalletCsIOS(todayWalletCsIOS);
		overview.setTodayWalletCsSum(todayWalletCsSum);

		// 统计昨日充值次数
		Double yesterdayWalletCsAndroid = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 1, 1,
				1);
		Double yesterdayWalletCsIOS = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 1, 2, 1);
		if (yesterdayWalletCsAndroid == null) {
			yesterdayWalletCsAndroid = 0.0;
		}
		if (yesterdayWalletCsIOS == null) {
			yesterdayWalletCsIOS = 0.0;
		}
		Double yesterdayWalletCsSum = ArithHelper.add(yesterdayWalletCsAndroid, yesterdayWalletCsIOS);

		model.addAttribute("yesterdayWalletCsAndroid", yesterdayWalletCsAndroid.intValue());
		model.addAttribute("yesterdayWalletCsIOS", yesterdayWalletCsIOS.intValue());
		model.addAttribute("yesterdayWalletCsSum", yesterdayWalletCsSum.intValue());

		overview.setYesterdayWalletCsAndroid(yesterdayWalletCsAndroid);
		overview.setYesterdayWalletCsIOS(yesterdayWalletCsIOS);
		overview.setYesterdayWalletCsSum(yesterdayWalletCsSum);

		// 统计今日充值充值总额（元）
		Double todayWalletZeAndroid = walletDetailService.countWallet(todaybeginTime, todayendTime, 1, 1, 2);
		Double todayWalletZeIOS = walletDetailService.countWallet(todaybeginTime, todayendTime, 1, 2, 2);
		if (todayWalletZeAndroid == null) {
			todayWalletZeAndroid = 0.0;
		}
		if (todayWalletZeIOS == null) {
			todayWalletZeIOS = 0.0;
		}
		Double todayWalletZeSum = ArithHelper.add(todayWalletZeAndroid, todayWalletZeIOS);

		model.addAttribute("todayWalletZeAndroid", todayWalletZeAndroid);
		model.addAttribute("todayWalletZeIOS", todayWalletZeIOS);
		model.addAttribute("todayWalletZeSum", todayWalletZeSum);

		overview.setTodayWalletZeAndroid(todayWalletZeAndroid);
		overview.setTodayWalletZeIOS(todayWalletZeIOS);
		overview.setTodayWalletZeSum(todayWalletZeSum);

		// 统计昨日充值充值总额（元）
		Double yesterdayWalletZeAndroid = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 1, 1,
				2);
		Double yesterdayWalletZeIOS = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 1, 2, 2);
		if (yesterdayWalletZeAndroid == null) {
			yesterdayWalletZeAndroid = 0.0;
		}
		if (yesterdayWalletZeIOS == null) {
			yesterdayWalletZeIOS = 0.0;
		}
		Double yesterdayWalletZeSum = ArithHelper.add(yesterdayWalletZeAndroid, yesterdayWalletZeIOS);

		model.addAttribute("yesterdayWalletZeAndroid", yesterdayWalletZeAndroid);
		model.addAttribute("yesterdayWalletZeIOS", yesterdayWalletZeIOS);
		model.addAttribute("yesterdayWalletZeSum", yesterdayWalletZeSum);

		overview.setYesterdayWalletZeAndroid(yesterdayWalletZeAndroid);
		overview.setYesterdayWalletZeIOS(yesterdayWalletZeIOS);
		overview.setYesterdayWalletZeSum(yesterdayWalletZeSum);

		// 统计今天提现次数
		Double todayWalletTxCsAndroid = walletDetailService.countWallet(todaybeginTime, todayendTime, 2, 1, 1);
		Double todayWalletTxCsIOS = walletDetailService.countWallet(todaybeginTime, todayendTime, 2, 2, 1);
		if (todayWalletTxCsAndroid == null) {
			todayWalletTxCsAndroid = 0.0;
		}
		if (todayWalletTxCsIOS == null) {
			todayWalletTxCsIOS = 0.0;
		}
		Double todayWalletTxCsSum = ArithHelper.add(todayWalletTxCsAndroid, todayWalletTxCsIOS);
		model.addAttribute("todayWalletTxCsAndroid", todayWalletTxCsAndroid.intValue());
		model.addAttribute("todayWalletTxCsIOS", todayWalletTxCsIOS.intValue());
		model.addAttribute("todayWalletTxCsSum", todayWalletTxCsSum.intValue());

		overview.setTodayWalletTxCsAndroid(todayWalletTxCsAndroid);
		overview.setTodayWalletTxCsIOS(todayWalletTxCsIOS);
		overview.setTodayWalletTxCsSum(todayWalletTxCsSum);

		// 统计昨天提现次数
		Double yesterdayWalletTxCsAndroid = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 2, 1,
				1);
		Double yesterdayWalletTxCsIOS = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 2, 2, 1);
		if (yesterdayWalletTxCsAndroid == null) {
			yesterdayWalletTxCsAndroid = 0.0;
		}
		if (yesterdayWalletTxCsIOS == null) {
			yesterdayWalletTxCsIOS = 0.0;
		}
		Double yesterdayWalletTxCsSum = ArithHelper.add(yesterdayWalletTxCsAndroid, yesterdayWalletTxCsIOS);
		model.addAttribute("yesterdayWalletTxCsAndroid", yesterdayWalletTxCsAndroid.intValue());
		model.addAttribute("yesterdayWalletTxCsIOS", yesterdayWalletTxCsIOS.intValue());
		model.addAttribute("yesterdayWalletTxCsSum", yesterdayWalletTxCsSum.intValue());

		overview.setYesterdayWalletTxCsAndroid(yesterdayWalletTxCsAndroid);
		overview.setYesterdayWalletTxCsIOS(yesterdayWalletTxCsIOS);
		overview.setYesterdayWalletTxCsSum(yesterdayWalletTxCsSum);

		// 统计今天提现总额
		Double todayWalletTxZeAndroid = walletDetailService.countWallet(todaybeginTime, todayendTime, 2, 1, 2);
		Double todayWalletTxZeIOS = walletDetailService.countWallet(todaybeginTime, todayendTime, 2, 2, 2);
		if (todayWalletTxZeAndroid == null) {
			todayWalletTxZeAndroid = 0.0;
		}
		if (todayWalletTxZeIOS == null) {
			todayWalletTxZeIOS = 0.0;
		}
		Double todayWalletTxZeSum = ArithHelper.add(todayWalletTxZeAndroid, todayWalletTxZeIOS);
		model.addAttribute("todayWalletTxZeAndroid", todayWalletTxZeAndroid);
		model.addAttribute("todayWalletTxZeIOS", todayWalletTxZeIOS);
		model.addAttribute("todayWalletTxZeSum", todayWalletTxZeSum);

		overview.setTodayWalletTxZeAndroid(todayWalletTxZeAndroid);
		overview.setTodayWalletTxZeIOS(todayWalletTxZeIOS);
		overview.setTodayWalletTxZeSum(todayWalletTxZeSum);

		// 统计昨天提现总额
		Double yesterdayWalletTxZeAndroid = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 2, 1,
				2);
		Double yesterdayWalletTxZeIOS = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime, 2, 2, 2);
		if (yesterdayWalletTxZeAndroid == null) {
			yesterdayWalletTxZeAndroid = 0.0;
		}
		if (yesterdayWalletTxZeIOS == null) {
			yesterdayWalletTxZeIOS = 0.0;
		}
		Double yesterdayWalletTxZeSum = ArithHelper.add(yesterdayWalletTxZeAndroid, yesterdayWalletTxZeIOS);
		model.addAttribute("yesterdayWalletTxZeAndroid", yesterdayWalletTxZeAndroid);
		model.addAttribute("yesterdayWalletTxZeIOS", yesterdayWalletTxZeIOS);
		model.addAttribute("yesterdayWalletTxZeSum", yesterdayWalletTxZeSum);

		overview.setYesterdayWalletTxZeAndroid(yesterdayWalletTxZeAndroid);
		overview.setYesterdayWalletTxZeIOS(yesterdayWalletTxZeIOS);
		overview.setYesterdayWalletTxZeSum(yesterdayWalletTxZeSum);
		return model;
	}

	/***
	 * 统计活跃
	 * 
	 * @param model
	 * @return Model
	 */
	private Model getActiveUserCount(Model model) {
		// 今日活跃用户
		Integer todayActiveUserAndroid = stepService.countStep(todaybeginTime, todayendTime, 1);
		Integer todayActiveUserIOS = stepService.countStep(todaybeginTime, todayendTime, 2);
		Integer todayActiveUserSum = todayActiveUserAndroid + todayActiveUserIOS;
		model.addAttribute("todayActiveUserAndroid", todayActiveUserAndroid);
		model.addAttribute("todayActiveUserIOS", todayActiveUserIOS);
		model.addAttribute("todayActiveUserSum", todayActiveUserSum);
		overview.setTodayActiveUserAndroid(todayActiveUserAndroid);
		overview.setTodayActiveUserIOS(todayActiveUserIOS);
		overview.setTodayActiveUserSum(todayActiveUserSum);
		// 昨日活跃用户
		Integer yesterdayActiveUserAndroid = stepService.countStep(yesterdaybeginTime, yesterdayendTime, 1);
		Integer yesterdayActiveUserIOS = stepService.countStep(yesterdaybeginTime, yesterdayendTime, 2);
		Integer yesterdayActiveUserSum = yesterdayActiveUserAndroid + yesterdayActiveUserIOS;
		model.addAttribute("yesterdayActiveUserAndroid", yesterdayActiveUserAndroid);
		model.addAttribute("yesterdayActiveUserIOS", yesterdayActiveUserIOS);
		model.addAttribute("yesterdayActiveUserSum", yesterdayActiveUserSum);
		overview.setYesterdayActiveUserAndroid(yesterdayActiveUserAndroid);
		overview.setYesterdayActiveUserIOS(yesterdayActiveUserIOS);
		overview.setYesterdayActiveUserSum(yesterdayActiveUserSum);
		// 7日活跃用户
		Integer sevenDaysActiveUserAndroid = stepService.countStep(sevenDaysbeginTime, todayendTime, 1);
		Integer sevenDaysActiveUserIOS = stepService.countStep(sevenDaysbeginTime, todayendTime, 2);
		Integer sevenDaysActiveUserSum = sevenDaysActiveUserAndroid + sevenDaysActiveUserIOS;
		model.addAttribute("sevenDaysActiveUserAndroid", sevenDaysActiveUserAndroid);
		model.addAttribute("sevenDaysActiveUserIOS", sevenDaysActiveUserIOS);
		model.addAttribute("sevenDaysActiveUserSum", sevenDaysActiveUserSum);
		overview.setSevenDaysActiveUserAndroid(sevenDaysActiveUserAndroid);
		overview.setSevenDaysActiveUserIOS(sevenDaysActiveUserIOS);
		overview.setSevenDaysActiveUserSum(sevenDaysActiveUserSum);
		// 30日累计活跃
		Integer thirtyDaysActiveUserAndroid = stepService.countStep(thirtyDaysbeginTime, todayendTime, 1);
		Integer thirtyDaysActiveUserIOS = stepService.countStep(thirtyDaysbeginTime, todayendTime, 2);
		Integer thirtyDaysActiveUserSum = thirtyDaysActiveUserAndroid + thirtyDaysActiveUserIOS;
		model.addAttribute("thirtyDaysActiveUserAndroid", thirtyDaysActiveUserAndroid);
		model.addAttribute("thirtyDaysActiveUserIOS", thirtyDaysActiveUserIOS);
		model.addAttribute("thirtyDaysActiveUserSum", thirtyDaysActiveUserSum);
		overview.setThirtyDaysActiveUserAndroid(thirtyDaysActiveUserAndroid);
		overview.setThirtyDaysActiveUserIOS(thirtyDaysActiveUserIOS);
		overview.setThirtyDaysActiveUserSum(thirtyDaysActiveUserSum);
		return model;
	}

	/***
	 * 统计新增用户
	 * 
	 * @param model
	 * @return Model
	 */
	private Model getNewUserCount(Model model) {

		Integer todayAndroid = userService.countUser(todaybeginTime, todayendTime, 1);
		Integer todayIOS = userService.countUser(todaybeginTime, todayendTime, 2);
		Integer todaySum = todayAndroid + todayIOS;

		model.addAttribute("todayAndroid", todayAndroid);
		model.addAttribute("todayIOS", todayIOS);
		model.addAttribute("todaySum", todaySum);
		overview.setTodayAndroid(todayAndroid);
		overview.setTodayIOS(todayIOS);
		overview.setTodaySum(todaySum);

		Integer yesterdayAndroid = userService.countUser(yesterdaybeginTime, yesterdayendTime, 1);
		Integer yesterdayIOS = userService.countUser(yesterdaybeginTime, yesterdayendTime, 2);
		Integer yesterdaySum = yesterdayAndroid + yesterdayIOS;

		model.addAttribute("yesterdayAndroid", yesterdayAndroid);
		model.addAttribute("yesterdayIOS", yesterdayIOS);
		model.addAttribute("yesterdaySum", yesterdaySum);
		overview.setYesterdayAndroid(yesterdayAndroid);
		overview.setYesterdayIOS(yesterdayIOS);
		overview.setYesterdaySum(yesterdaySum);

		Integer sevenDaysAndroid = userService.countUser(sevenDaysbeginTime, todayendTime, 1);
		Integer sevenDaysIOS = userService.countUser(sevenDaysbeginTime, todayendTime, 2);
		Integer sevenDaysSum = sevenDaysAndroid + sevenDaysIOS;

		model.addAttribute("sevenDaysAndroid", sevenDaysAndroid);
		model.addAttribute("sevenDaysIOS", sevenDaysIOS);
		model.addAttribute("sevenDaysSum", sevenDaysSum);
		overview.setSevenDaysAndroid(sevenDaysAndroid);
		overview.setSevenDaysIOS(sevenDaysIOS);
		overview.setSevenDaysSum(sevenDaysSum);

		Integer thirtyDaysAndroid = userService.countUser(thirtyDaysbeginTime, todayendTime, 1);
		Integer thirtyDaysIOS = userService.countUser(thirtyDaysbeginTime, todayendTime, 2);
		Integer thirtyDaysSum = thirtyDaysAndroid + thirtyDaysIOS;

		model.addAttribute("thirtyDaysAndroid", thirtyDaysAndroid);
		model.addAttribute("thirtyDaysIOS", thirtyDaysIOS);
		model.addAttribute("thirtyDaysSum", thirtyDaysSum);
		overview.setThirtyDaysAndroid(thirtyDaysAndroid);
		overview.setThirtyDaysIOS(thirtyDaysIOS);
		overview.setThirtyDaysSum(thirtyDaysSum);

		Integer countUser = userService.countUser(null, null, null);
		model.addAttribute("countUser", countUser);
		overview.setCountUser(countUser);
		return model;
	}

	private Model getDistanceCount(Model model) {
		// 今天跑步人数
		Double todayDistanceRSAndroid = distanceService.countDistance(todaybeginTime, todayendTime, 1, 1);
		Double todayDistanceRSIOS = distanceService.countDistance(todaybeginTime, todayendTime, 2, 1);
		if (todayDistanceRSAndroid == null) {
			todayDistanceRSAndroid = 0.0;
		}
		if (todayDistanceRSIOS == null) {
			todayDistanceRSIOS = 0.0;
		}
		Double todayDistanceRSSum = todayDistanceRSAndroid + todayDistanceRSIOS;
		model.addAttribute("todayDistanceRSAndroid", todayDistanceRSAndroid.intValue());
		model.addAttribute("todayDistanceRSIOS", todayDistanceRSIOS.intValue());
		model.addAttribute("todayDistanceRSSum", todayDistanceRSSum.intValue());
		overview.setTodayDistanceRSAndroid(todayDistanceRSAndroid);
		overview.setTodayDistanceRSIOS(todayDistanceRSIOS);
		overview.setTodayDistanceRSSum(todayDistanceRSSum);

		// 昨天跑步人数
		Double yesterdayDistanceRSAndroid = distanceService.countDistance(yesterdaybeginTime, yesterdayendTime, 1, 1);
		Double yesterdayDistanceRSIOS = distanceService.countDistance(yesterdaybeginTime, yesterdayendTime, 2, 1);
		if (yesterdayDistanceRSAndroid == null) {
			yesterdayDistanceRSAndroid = 0.0;
		}
		if (yesterdayDistanceRSIOS == null) {
			yesterdayDistanceRSIOS = 0.0;
		}
		Double yesterdayDistanceRSSum = yesterdayDistanceRSAndroid + yesterdayDistanceRSIOS;
		model.addAttribute("yesterdayDistanceRSAndroid", yesterdayDistanceRSAndroid.intValue());
		model.addAttribute("yesterdayDistanceRSIOS", yesterdayDistanceRSIOS.intValue());
		model.addAttribute("yesterdayDistanceRSSum", yesterdayDistanceRSSum.intValue());
		overview.setYesterdayDistanceRSAndroid(yesterdayDistanceRSAndroid);
		overview.setYesterdayDistanceRSIOS(yesterdayDistanceRSIOS);
		overview.setYesterdayDistanceRSSum(yesterdayDistanceRSSum);

		// 今日跑步总量
		Double todayDistanceZLAndroid = distanceService.countDistance(todaybeginTime, todayendTime, 1, 2);
		Double todayDistanceZLIOS = distanceService.countDistance(todaybeginTime, todayendTime, 2, 2);
		if (todayDistanceZLAndroid == null) {
			todayDistanceZLAndroid = 0.0;
		}
		if (todayDistanceZLIOS == null) {
			todayDistanceZLIOS = 0.0;
		}
		Double todayDistanceZLSum = todayDistanceZLAndroid + todayDistanceZLIOS;
		model.addAttribute("todayDistanceZLAndroid", todayDistanceZLAndroid);
		model.addAttribute("todayDistanceZLIOS", todayDistanceZLIOS);
		model.addAttribute("todayDistanceZLSum", ArithHelper.cutDouble(todayDistanceZLSum));
		overview.setTodayDistanceZLAndroid(todayDistanceZLAndroid);
		overview.setTodayDistanceZLIOS(todayDistanceZLIOS);
		overview.setTodayDistanceZLSum(todayDistanceZLSum);

		// 昨日跑步总量
		Double yesterdayDistanceZLAndroid = distanceService.countDistance(yesterdaybeginTime, yesterdayendTime, 1, 2);
		Double yesterdayDistanceZLIOS = distanceService.countDistance(yesterdaybeginTime, yesterdayendTime, 2, 2);
		if (yesterdayDistanceZLAndroid == null) {
			yesterdayDistanceZLAndroid = 0.0;
		}
		if (yesterdayDistanceZLIOS == null) {
			yesterdayDistanceZLIOS = 0.0;
		}
		Double yesterdayDistanceZLSum = ArithHelper.add(yesterdayDistanceZLAndroid, yesterdayDistanceZLIOS);
		model.addAttribute("yesterdayDistanceZLAndroid", yesterdayDistanceZLAndroid);
		model.addAttribute("yesterdayDistanceZLIOS", yesterdayDistanceZLIOS);
		model.addAttribute("yesterdayDistanceZLSum", ArithHelper.cutDouble(yesterdayDistanceZLSum));
		overview.setYesterdayDistanceZLAndroid(yesterdayDistanceZLAndroid);
		overview.setYesterdayDistanceZLIOS(yesterdayDistanceZLIOS);
		overview.setYesterdayDistanceZLSum(yesterdayDistanceZLSum);
		return model;
	}

	private Model getHugThighCount(Model model) {
		// 今日安卓参与人数
		Integer todayCountThighAndroid = thighService.countThigh(todaybeginTime, todayendTime, 1);
		Integer todayCountUserCouponAndroid = userCouponService.countUserCoupon(todaybeginTime, todayendTime, 1);
		if (todayCountThighAndroid == null) {
			todayCountThighAndroid = 0;
		}
		if (todayCountUserCouponAndroid == null) {
			todayCountUserCouponAndroid = 0;
		}
		Integer todayHugThighCountAndroid = todayCountThighAndroid + todayCountUserCouponAndroid;
		model.addAttribute("todayHugThighCountAndroid", todayHugThighCountAndroid);
		overview.setTodayHugThighCountAndroid(todayHugThighCountAndroid);

		// 今日IOS参与人数
		Integer todayCountThighIOS = thighService.countThigh(todaybeginTime, todayendTime, 2);
		Integer todayCountUserCouponIOS = userCouponService.countUserCoupon(todaybeginTime, todayendTime, 2);
		if (todayCountThighIOS == null) {
			todayCountThighIOS = 0;
		}
		if (todayCountUserCouponIOS == null) {
			todayCountUserCouponIOS = 0;
		}
		Integer todayHugThighCountIOS = todayCountThighIOS + todayCountUserCouponIOS;
		model.addAttribute("todayHugThighCountIOS", todayHugThighCountIOS);
		overview.setTodayHugThighCountIOS(todayHugThighCountIOS);
		// 今日总和参加
		Integer todayHugThighCountSum = todayHugThighCountAndroid + todayHugThighCountIOS;
		model.addAttribute("todayHugThighCountSum", todayHugThighCountSum);
		overview.setTodayHugThighCountSum(todayHugThighCountSum);
		// 昨日安卓参与人数
		Integer yesterdayCountThighAndroid = thighService.countThigh(yesterdaybeginTime, yesterdayendTime, 1);
		Integer yesterdayCountUserCouponAndroid = userCouponService.countUserCoupon(yesterdaybeginTime,
				yesterdayendTime, 1);
		if (yesterdayCountThighAndroid == null) {
			yesterdayCountThighAndroid = 0;
		}
		if (yesterdayCountUserCouponAndroid == null) {
			yesterdayCountUserCouponAndroid = 0;
		}
		Integer yesterdayHugThighCountAndroid = yesterdayCountThighAndroid + yesterdayCountUserCouponAndroid;
		model.addAttribute("yesterdayHugThighCountAndroid", yesterdayHugThighCountAndroid);
		overview.setYesterdayHugThighCountAndroid(yesterdayHugThighCountAndroid);
		// 昨日IOS参与人数
		Integer yesterdayCountThighIOS = thighService.countThigh(yesterdaybeginTime, yesterdayendTime, 2);
		Integer yesterdayCountUserCouponIOS = userCouponService.countUserCoupon(yesterdaybeginTime, yesterdayendTime,
				2);
		if (yesterdayCountThighIOS == null) {
			yesterdayCountThighIOS = 0;
		}
		if (yesterdayCountUserCouponIOS == null) {
			yesterdayCountUserCouponIOS = 0;
		}
		Integer yesterdayHugThighCountIOS = yesterdayCountThighIOS + yesterdayCountUserCouponIOS;
		model.addAttribute("yesterdayHugThighCountIOS", yesterdayHugThighCountIOS);
		overview.setYesterdayHugThighCountIOS(yesterdayHugThighCountIOS);
		// 昨日总和参加
		Integer yesterdayHugThighCountSum = yesterdayHugThighCountAndroid + yesterdayHugThighCountIOS;
		model.addAttribute("yesterdayHugThighCountSum", yesterdayHugThighCountSum);
		overview.setYesterdayHugThighCountSum(yesterdayHugThighCountSum);
		// 今日安卓交易金额
		Double todayCountWalletHugThighAndroid = walletDetailService.countWallet(todaybeginTime, todayendTime, 6, 1, 2);
		// 今日IOS交易金额
		Double todayCountWalletHugThighIOS = walletDetailService.countWallet(todaybeginTime, todayendTime, 6, 2, 2);
		if (todayCountWalletHugThighAndroid == null) {
			todayCountWalletHugThighAndroid = 0.0;
		}
		if (todayCountWalletHugThighIOS == null) {
			todayCountWalletHugThighIOS = 0.0;
		}
		// 今日总金额
		Double todayCountWalletHugThighSum = ArithHelper.add(todayCountWalletHugThighAndroid,
				todayCountWalletHugThighIOS);

		model.addAttribute("todayCountWalletHugThighAndroid", todayCountWalletHugThighAndroid);
		model.addAttribute("todayCountWalletHugThighIOS", todayCountWalletHugThighIOS);
		model.addAttribute("todayCountWalletHugThighSum", todayCountWalletHugThighSum);
		overview.setTodayCountWalletHugThighAndroid(todayCountWalletHugThighAndroid);
		overview.setTodayCountWalletHugThighIOS(todayCountWalletHugThighIOS);
		overview.setTodayCountWalletHugThighSum(todayCountWalletHugThighSum);

		// 昨日安卓交易金额
		Double yesterdayCountWalletHugThighAndroid = walletDetailService.countWallet(yesterdaybeginTime,
				yesterdayendTime, 6, 1, 2);
		// 昨日IOS交易金额
		Double yesterdayCountWalletHugThighIOS = walletDetailService.countWallet(yesterdaybeginTime, yesterdayendTime,
				6, 2, 2);
		if (yesterdayCountWalletHugThighAndroid == null) {
			yesterdayCountWalletHugThighAndroid = 0.0;
		}
		if (yesterdayCountWalletHugThighIOS == null) {
			yesterdayCountWalletHugThighIOS = 0.0;
		}
		// 昨日总金额
		Double yesterdayCountWalletHugThighSum = ArithHelper.add(yesterdayCountWalletHugThighAndroid,
				yesterdayCountWalletHugThighIOS);

		model.addAttribute("yesterdayCountWalletHugThighAndroid", yesterdayCountWalletHugThighAndroid);
		model.addAttribute("yesterdayCountWalletHugThighIOS", yesterdayCountWalletHugThighIOS);
		model.addAttribute("yesterdayCountWalletHugThighSum", yesterdayCountWalletHugThighSum);
		overview.setYesterdayCountWalletHugThighAndroid(yesterdayCountWalletHugThighAndroid);
		overview.setYesterdayCountWalletHugThighIOS(yesterdayCountWalletHugThighIOS);
		overview.setYesterdayCountWalletHugThighSum(yesterdayCountWalletHugThighSum);
		return model;
	}

}
