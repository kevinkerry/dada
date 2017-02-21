package com.youyisi.admin.interfaces.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.snatch.SnatchActivityService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private SnatchActivityService SnatchActivityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Activity> page = new Page<Activity>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", activityService.queryPage(page));
		model.addAttribute("todayDate", DateUtil.todayDate());
		model.addAttribute("currentTime", System.currentTimeMillis());
		return "activity/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activity", activityService.get(id));
		return "activity/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Activity activity) {
		activityService.update(activity);
		return "redirect:activity/list";
	}

	@RequestMapping(value = "/addActivityPage", method = RequestMethod.GET)
	public String addActivityPage(Model model, Integer type) {
		String page = "activity/addhugthigh";
		if (type == 2) {
			page = "invitefriendactivity/addinviteactivity";
		}
		if (type == 3) {
			page = "relay/addrelayraceactivity";
		}
		if (type == 4) {
			page = "snatch/addsnatchactivity";
		}
		return page;
	}

	@RequestMapping(value = "/updateActivityPage", method = RequestMethod.GET)
	public String updateActivityPage(Model model, Long activityId) {
		Activity activity = activityService.get(activityId);
		HugThighActivity ha = activity.getHugThighActivity();
		ha.setCronExpression(DateUtil.cronToDateTime(ha.getCronExpression()));
		model.addAttribute("activity", activity);
		return "activity/updatehugthigh";
	}

	@RequestMapping(value = "/addHugThigh", method = RequestMethod.POST)
	public String addHugThigh(Model model, Activity activity) {
		boolean validateDate = activityService.validateDate(1, activity.getDate());
		if (validateDate) {
			Integer row = activityService.addHugThigh(activity);
			if (row == 0) {
				model.addAttribute(result, "添加失败");
			}
		} else {
			model.addAttribute(result, "活动日期已存在");
			return "activity/addhugthigh";
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/addInviteFriend", method = RequestMethod.POST)
	public String addInviteFriend(Model model, Activity activity) {
		activity.setDate(DateUtil.timestampToDate(activity.getBeginTime()));
		boolean validateDate = activityService.validateDate(2, activity.getDate());
		if (validateDate) {
			Integer row = activityService.addInviteFriend(activity);
			if (row == 0) {
				model.addAttribute(result, "添加失败");
			}
		} else {
			model.addAttribute(result, "活动日期已存在");
			return "invitefriendactivity/addinviteactivity";
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/addRelayRace", method = RequestMethod.POST)
	public String addRelayRace(Model model, Activity activity) {
		activity.setDate(DateUtil.timestampToDate(activity.getBeginTime()));
		boolean validateDate = activityService.validateDate(3, activity.getDate());
		if (validateDate) {
			Integer row = activityService.addRelayRace(activity);
			if (row == 0) {
				model.addAttribute(result, "添加失败");
			}
		} else {
			model.addAttribute(result, "活动日期已存在");
			return "relay/addrelayraceactivity";
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/addSnatchActivity", method = RequestMethod.POST)
	public String addSnatchActivity(Model model, Activity activity) {
		activity.setDate(DateUtil.timestampToDate(activity.getBeginTime()));
		boolean validateDate = activityService.validateDate(4, activity.getDate());
		if (validateDate) {
			Integer row = activityService.addSnatchActivity(activity);
			if (row == 0) {
				model.addAttribute(result, "添加失败");
			}
		} else {
			model.addAttribute(result, "活动日期已存在");
			return "snatch/addsnatchactivity";
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
	public String gotoPage(Model model, Long activityId, String page) {
		Activity activity = activityService.get(activityId);
		activity.getHugThighActivity()
				.setCronExpression(DateUtil.cronToDateTime(activity.getHugThighActivity().getCronExpression()));
		model.addAttribute("activity", activity);
		return "activity/" + page;
	}

	@RequestMapping(value = "/delActivity", method = RequestMethod.GET)
	public String delActivity(Model model, Long activityId) {
		Integer delActivity = activityService.delActivity(activityId);
		if (delActivity == 0) {
			model.addAttribute(result, "删除失败");
		}
		if (delActivity == -1) {
			model.addAttribute(result, "即将开始的活动无法删除");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/updateHugThigh", method = RequestMethod.POST)
	public String updateHugThigh(Model model, Activity activity) {
		Integer row = activityService.updateHugThigh(activity);
		if (row == 0) {
			model.addAttribute(result, "更新失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/updateInviteFriend", method = RequestMethod.POST)
	public String updateInviteFriend(Model model, Activity activity) {
		Integer row = activityService.updateInviteFriend(activity);
		if (row <= 0) {
			model.addAttribute(result, "更新失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/updateRelayRace", method = RequestMethod.POST)
	public String updateRelayRace(Model model, Activity activity) {
		Integer row = activityService.updateRelayRace(activity);
		if (row <= 0) {
			model.addAttribute(result, "更新失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/updateSnatchActivity", method = RequestMethod.POST)
	public String updateSnatchActivity(Model model, Activity activity) {
		Integer row = activityService.updateSnatchActivity(activity);
		if (row <= 0) {
			model.addAttribute(result, "更新失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/lottery", method = RequestMethod.GET)
	public String lottery(Model model, Long activityId) {
		Integer row = SnatchActivityService.settle(SnatchActivityService.getByActivityId(activityId));
		if (row == 0) {
			model.addAttribute(result, "未到达开奖条件");
		}
		if (row == -1) {
			model.addAttribute(result, "人数未达到要求");
		}
		if (row == -2) {
			model.addAttribute(result, "进行中的活动不能开奖");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/refund", method = RequestMethod.GET)
	public String refund(Model model, Long activityId) {
		Integer row = SnatchActivityService.refund(activityId);
		if (row == 0) {
			model.addAttribute(result, "活动未结束,退款失败");
		}
		if (row == -1) {
			model.addAttribute(result, "活动已结算");
		}
		return list(model, 1, null);
	}

}
