package com.youyisi.admin.interfaces.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.relay.RelayLineService;
import com.youyisi.admin.application.relay.RelayRaceActivityService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Controller
@RequestMapping("/relayraceactivity")
public class RelayRaceActivityController {

	@Autowired
	private RelayRaceActivityService RelayRaceActivityService;

	@Autowired
	private RelayLineService relayLineService;

	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<RelayRaceActivity> page = new Page<RelayRaceActivity>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", RelayRaceActivityService.queryPage(page));
		return "relayraceactivity/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("RelayRaceActivity", RelayRaceActivityService.get(id));
		return "relayraceactivity/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RelayRaceActivity RelayRaceActivity) {
		RelayRaceActivityService.update(RelayRaceActivity);
		return "redirect:relayraceactivity/list";
	}

	@RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
	public String gotoPage(Model model, Long activityId, String page) {
		Activity activity = activityService.get(activityId);
		if (activity.getRelayRaceActivity() != null) {
			activity.getRelayRaceActivity()
					.setCronExpression(DateUtil.cronToDateTime(activity.getRelayRaceActivity().getCronExpression()));
		}
		activity.setRelayLineList(relayLineService.getRelayLineList(activityId));
		model.addAttribute("activity", activity);
		return "relay/" + page;
	}
}
