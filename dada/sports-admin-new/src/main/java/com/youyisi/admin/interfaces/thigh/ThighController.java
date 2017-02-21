package com.youyisi.admin.interfaces.thigh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.application.hugthigh.HugThighService;
import com.youyisi.admin.application.run.RunService;
import com.youyisi.admin.application.thigh.ThighService;
import com.youyisi.admin.application.user.UserCouponService;
import com.youyisi.admin.application.wallet.WalletDetailService;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.hugthigh.HugThigh;
import com.youyisi.admin.domain.thigh.Thigh;
import com.youyisi.admin.domain.user.UserCoupon;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Controller
@RequestMapping("/thigh")
public class ThighController {

	@Autowired
	private ThighService thighService;

	@Autowired
	private HugThighService hugThighService;

	@Autowired
	private WalletDetailService walletDetailService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private UserCouponService userCouponService;

	@Autowired
	private RunService runService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId, String field, String sort) {
		Page<Thigh> page = new Page<Thigh>();
		if (activityId != null) {
			Integer all = thighService.countThighByActivityId(activityId, null, null);
			Integer valid = thighService.countThighByActivityId(activityId, 1, null);
			model.addAttribute("all", all);
			model.addAttribute("valid", valid);
			model.addAttribute("invalid", all - valid);
			model.addAttribute("activityId", activityId);
			for (int i = 1; i <= 7; i++) {
				model.addAttribute("hugthigh" + i, thighService.countThighByActivityId(activityId, 1, i));
			}
			model.addAttribute("number", hugThighService.countHugThigh(activityId, null, 1));
			model.addAttribute("persons", hugThighService.countHugThigh(activityId, null, 2));
			model.addAttribute("commissionSum", couponService.sumCouponMoney(activityId));

			page.addParam("activityId", activityId);
		}
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
		model.addAttribute("page", thighService.queryPage(page));
		return "activity/hugthighuserinfo";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("thigh", thighService.get(id));
		return "thigh/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Thigh thigh) {
		thighService.update(thigh);
		return "redirect:thigh/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getThighRecordByUserId", method = RequestMethod.GET)
	public ResponseModel getThighRecordByUserId(Long userId, Integer currentPage, Integer pageSize) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			Page<Thigh> page = new Page<Thigh>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(7);
			}
			page.addParam("userId", userId);
			response.setMap("activityRecord", thighService.queryPageByUserId(page));

			// 累计参加次数
			response.setMap("number", thighService.countJoin(userId));

			// 累计收益
			Double sumWalletByUserId = walletDetailService.sumWalletByUserId(userId, 5);
			if (sumWalletByUserId == null) {
				sumWalletByUserId = 0.0;
			}
			response.setMap("earnings", sumWalletByUserId);
			response.success();
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/getThighOrSlackerInfo", method = RequestMethod.GET)
	public ResponseModel getThighOrSlacker(Long thighId, Long userId, Integer currentPage, Integer pageSize,
			String field, String sort) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			Page<HugThigh> page = new Page<HugThigh>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(7);
			}
			Thigh thigh = thighService.get(thighId);
			response.setMap("thigh", thigh);

			if (null != thighId) {
				page.addParam("thighId", thighId);
			}
			if (null != field) {
				page.addParam("field", field);
				page.addParam("sort", sort);
			}
			response.setMap("page", hugThighService.queryPage(page));
			Coupon coupon = couponService.getCouponByActivityIdAndType(thigh.getActivityId(), thigh.getType());

			response.setMap("commission", coupon.getCommission());

			if (thigh.getUserId() != userId) {
				List<HugThigh> query = hugThighService
						.query(new HugThigh(thigh.getId(), userId, thigh.getActivityId()));
				if (query != null && query.size() > 0) {
					HugThigh hugThigh = query.get(0);
					response.setMap("hugThigh", hugThigh);
					response.setMap("coupon", coupon);
					List<UserCoupon> userCoupon = userCouponService.getUserCouponByUserIdAndCouponId(userId,
							coupon.getId());
					if (userCoupon != null && userCoupon.size() > 0) {
						response.setMap("userCoupon", userCoupon.get(0));
					}
				}

			}

			response.success();
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/getRunRecord", method = RequestMethod.GET)
	public ResponseModel getRunRecord(Long userId, Long activityId) {
		ResponseModel response = new ResponseModel();
		if (userId != null && activityId != null) {
			response.setMap("run", runService.getThighRun(userId, activityId));
			response.success();
		}
		return response;
	}

}
