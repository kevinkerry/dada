package com.youyisi.admin.interfaces.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.application.relay.RelayMemberService;
import com.youyisi.admin.application.relay.RelayTeamService;
import com.youyisi.admin.domain.relay.RelayMember;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Controller
@RequestMapping("/relaymember")
public class RelayMemberController {

	@Autowired
	private RelayMemberService relayMemberService;

	@Autowired
	private RelayTeamService relayTeamService;

	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId) {
		Page<RelayMember> page = new Page<RelayMember>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		// 参加人数
		model.addAttribute("relayTeamNum", relayMemberService.countRelayMemberNumber(activityId));
		// 奖金池
		model.addAttribute("bonusPool", relayTeamService.getBonusPool(activityId));
		// 统计发放的体验金
		Double sumCouponMoneyByActivityId = couponService.sumCouponMoneyByActivityId(activityId);
		if (sumCouponMoneyByActivityId == null) {
			sumCouponMoneyByActivityId = 0.00;
		}
		model.addAttribute("countExperience", ArithHelper.cutDouble(sumCouponMoneyByActivityId));

		page.addParam("activityId", activityId);
		page.addParam("status", 1);
		model.addAttribute("activityId", activityId);
		model.addAttribute("page", relayMemberService.queryPage(page));
		return "relay/relayteamincome";
	}

	@RequestMapping(value = "/relayTeamMemberlist", method = RequestMethod.GET)
	public String relayTeamMemberlist(Model model, Integer currentPage, Integer pageSize, Long activityId, Long teamId,
			String condition) {
		Page<RelayMember> page = new Page<RelayMember>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		page.addParam("teamId", teamId);
		page.addParam("activityId", activityId);
		if (StrUtil.notEmpty(condition)) {
			page.addParam("condition", condition);
			model.addAttribute("condition", condition);
		}
		model.addAttribute("activityId", activityId);
		model.addAttribute("teamId", teamId);
		model.addAttribute("page", relayMemberService.queryPageMemberlist(page));
		return "relay/relayteammemberlist";
	}

	@RequestMapping(value = "/relayTeamSportslist", method = RequestMethod.GET)
	public String relayTeamSportslist(Model model, Integer currentPage, Integer pageSize, Long activityId, Long teamId,
			String condition) {
		Page<RelayMember> page = new Page<RelayMember>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		page.addParam("teamId", teamId);
		page.addParam("activityId", activityId);
		if (StrUtil.notEmpty(condition)) {
			page.addParam("condition", condition);
			model.addAttribute("condition", condition);
		}
		model.addAttribute("activityId", activityId);
		model.addAttribute("teamId", teamId);
		model.addAttribute("page", relayMemberService.queryPageSportslist(page));
		return "relay/relayteamsportslist";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("RelayMember", relayMemberService.get(id));
		return "relaymember/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RelayMember RelayMember) {
		relayMemberService.update(RelayMember);
		return "redirect:relaymember/list";
	}
}
