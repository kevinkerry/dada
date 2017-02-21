package com.youyisi.admin.interfaces.invitefriendactivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.application.invitefriendactivity.InviteFriendActivityService;
import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivity;
import com.youyisi.admin.domain.user.UserInvite;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-11
 */
@Controller
@RequestMapping("/invitefriendactivity")
public class InviteFriendActivityController extends BaseController {

	@Autowired
	private InviteFriendActivityService InviteFriendActivityService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserService userService;

	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<InviteFriendActivity> page = new Page<InviteFriendActivity>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		model.addAttribute("page", InviteFriendActivityService.queryPage(page));
		return "invitefriendactivity/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("InviteFriendActivity", InviteFriendActivityService.get(id));
		return "invitefriendactivity/form";
	}

	@RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
	public String gotoPage(Model model, Long activityId, String page) {
		Activity activity = activityService.get(activityId);
		activity.getInviteFriendActivity()
				.setCronExpression(DateUtil.cronToDateTime(activity.getInviteFriendActivity().getCronExpression()));
		model.addAttribute("activity", activity);
		return "invitefriendactivity/" + page;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(InviteFriendActivity InviteFriendActivity) {
		InviteFriendActivityService.update(InviteFriendActivity);
		return "redirect:invitefriendactivity/list";
	}

	@RequestMapping(value = "/getUserInvite", method = RequestMethod.GET)
	public String getUserInvite(Model model, Integer currentPage, Integer pageSize, Long activityId, String condition) {
		Page<UserInvite> page = new Page<UserInvite>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		if (StrUtil.notEmpty(condition)) {
			page.addParam("condition", condition);
		}
		page.addParam("activityId", activityId);
		model.addAttribute("page", userService.queryPageUserInvite(page));
		model.addAttribute("activityId", activityId);

		Integer yq = couponService.getInviteRegister(1, activityId);
		if (yq == null) {
			yq = 0;

		}
		Integer jr = userService.countInviteNum(activityId);
		if (jr == null) {
			jr = 0;
		}
		model.addAttribute("yq", yq);
		model.addAttribute("jr", jr);
		model.addAttribute("sum", couponService.sumInviteMoney(activityId));
		return "invitefriendactivity/inviteuserinfo";
	}

	@RequestMapping(value = "/getUserInviteDetail", method = RequestMethod.GET)
	public String getUserInviteDetail(Model model, Integer currentPage, Integer pageSize, String userCode,
			Long activityId) {
		Page<UserInvite> page = new Page<UserInvite>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		if (StrUtil.notEmpty(userCode)) {
			page.addParam("userCode", userCode);
		}
		model.addAttribute("page", userService.queryPageUserInviteDetail(page));
		model.addAttribute("userCode", userCode);
		model.addAttribute("activityId", activityId);

		Coupon coupon = couponService.getCouponByActivityIdAndType(activityId, 9);
		if (coupon != null) {
			model.addAttribute("amount", coupon.getBonus());
		} else {
			model.addAttribute("amount", 0);
		}
		return "invitefriendactivity/inviteuserdetail";
	}

}
