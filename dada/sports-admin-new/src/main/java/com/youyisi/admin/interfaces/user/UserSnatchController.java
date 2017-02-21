package com.youyisi.admin.interfaces.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.snatch.SnatchActivityService;
import com.youyisi.admin.application.user.UserSnatchService;
import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.admin.domain.user.UserSnatch;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Controller
@RequestMapping("/usersnatch")
public class UserSnatchController {

	@Autowired
	private UserSnatchService userSnatchService;

	@Autowired
	private SnatchActivityService SnatchActivityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId, String condition) {
		Page<UserSnatch> page = new Page<UserSnatch>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		if (StrUtil.notEmpty(condition)) {
			page.addParam("condition", condition);
		}
		page.addParam("activityId", activityId);
		model.addAttribute("activityId", activityId);
		// 一共有多少人参加
		Integer userSnatchCount = userSnatchService.getUserSnatchCount(activityId, 1);
		if (userSnatchCount == null) {
			userSnatchCount = 0;
		}
		model.addAttribute("numberPeople", userSnatchCount);
		// 购买次数
		Integer userSnatchCount2 = userSnatchService.getUserSnatchCount(activityId, 2);
		if (userSnatchCount2 == null) {
			userSnatchCount2 = 0;
		}
		model.addAttribute("numberBuy", userSnatchCount2);
		// 奖金池体验金
		SnatchActivity byActivityId = SnatchActivityService.getByActivityId(activityId);
		userSnatchCount2 = byActivityId.getWinNum() - userSnatchCount2;
		model.addAttribute("bonusPool",
				(byActivityId.getContributeBonus() * userSnatchCount2) + byActivityId.getInitialBonus());
		model.addAttribute("page", userSnatchService.queryPage(page));
		return "snatch/snatchactivityuserinfo";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("UserSnatch", userSnatchService.get(id));
		return "usersnatch/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(UserSnatch UserSnatch) {
		userSnatchService.update(UserSnatch);
		return "redirect:usersnatch/list";
	}
}
