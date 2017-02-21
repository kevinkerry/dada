package com.youyisi.admin.interfaces.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.application.user.UserCouponService;
import com.youyisi.admin.application.wallet.WalletDetailService;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Controller
@RequestMapping("/usercoupon")
public class UserCouponController {
	@Autowired
	private CouponService couponService;

	@Autowired
	private UserCouponService userCouponService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private WalletDetailService walletDetailService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId, String field, String sort,
			Integer type) {
		Page<UserCoupon> page = new Page<UserCoupon>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		if (null != field) {
			page.addParam("field", field);
			page.addParam("sort", sort);
		}
		if (type != null && type != 0) {
			page.addParam("type", type);
			// 过期
			if (type == 1) {
				page.addParam("expiryTime", System.currentTimeMillis());
			}
		}

		if (activityId != null) {
			page.addParam("activityId", activityId);
			model.addAttribute("activityId", activityId);
			initData(model, activityId);
			model.addAttribute("page", userCouponService.queryPage(page));
			model.addAttribute("currentTime", System.currentTimeMillis());
		}
		return "activity/hugthighincomeinfo";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("userCoupon", userCouponService.get(id));
		return "usercoupon/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(UserCoupon userCoupon) {
		userCouponService.update(userCoupon);
		return "redirect:usercoupon/list";
	}

	private Model initData(Model model, Long activityId) {
		// 1-7级福利券
		model.addAttribute("welfare1", couponService.countWelfareNum(activityId, 1));
		model.addAttribute("welfare2", couponService.countWelfareNum(activityId, 2));
		model.addAttribute("welfare3", couponService.countWelfareNum(activityId, 3));
		model.addAttribute("welfare4", couponService.countWelfareNum(activityId, 4));
		model.addAttribute("welfare5", couponService.countWelfareNum(activityId, 5));
		model.addAttribute("welfare6", couponService.countWelfareNum(activityId, 6));
		model.addAttribute("welfare7", couponService.countWelfareNum(activityId, 7));

		// 已经使用几张
		model.addAttribute("used", userCouponService.countUserCouponByActivityId(activityId, 1));
		// 未使用几张
		model.addAttribute("unused", userCouponService.countUserCouponByActivityId(activityId, 0));

		// 累计支付佣金
		Double commissionSum = couponService.sumCouponMoney(activityId);
		model.addAttribute("commissionSum", commissionSum);

		// 懒虫结算收益
		// Activity activity = activityService.get(activityId);
		Double earnings = walletDetailService.sumWalletByDateAndType(null, 7);
		if (earnings == null) {
			earnings = 0.0;
		}
		model.addAttribute("earnings", earnings);

		// 平台佣金 用户佣金
		Coupon coupon = couponService.getCouponByActivityIdAndType(activityId, 1);
		Double commissionRate = 0.0;
		if (coupon != null) {
			commissionRate = coupon.getCommissionRate();
		}
		Double platform = ArithHelper.sub(1.0, commissionRate);
		if (commissionSum == null) {
			commissionSum = 0.0;
		}
		Double platformearnings = ArithHelper.mul(commissionSum, platform);

		model.addAttribute("platformearnings", platformearnings);
		model.addAttribute("thighearnings", ArithHelper.mul(commissionSum, commissionRate));

		// 平台净利润
		model.addAttribute("profit", ArithHelper.sub(platformearnings, earnings));
		return model;
	}
}
