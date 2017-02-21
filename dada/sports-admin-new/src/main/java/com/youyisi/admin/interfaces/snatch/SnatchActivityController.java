package com.youyisi.admin.interfaces.snatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.snatch.SnatchActivityService;
import com.youyisi.admin.application.snatch.SnatchFeeService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Controller
@RequestMapping("/snatchactivity")
public class SnatchActivityController extends BaseController {

	@Autowired
	private SnatchActivityService SnatchActivityService;

	@Autowired
	private SnatchFeeService snatchFeeService;

	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<SnatchActivity> page = new Page<SnatchActivity>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", SnatchActivityService.queryPage(page));
		return "snatchactivity/list";
	}

	@RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
	public String gotoPage(Model model, Long activityId, String page) {
		Activity activity = activityService.get(activityId);
		activity.getSnatchActivity()
				.setCronExpression(DateUtil.cronToDateTime(activity.getSnatchActivity().getCronExpression()));
		activity.setSnatchFeeList(snatchFeeService.getSnatchFeeByActivityId(activityId));
		model.addAttribute("activity", activity);
		return "snatch/" + page;
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("SnatchActivity", SnatchActivityService.get(id));
		return "snatchactivity/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SnatchActivity SnatchActivity) {
		SnatchActivityService.update(SnatchActivity);
		return "redirect:snatchactivity/list";
	}

}
