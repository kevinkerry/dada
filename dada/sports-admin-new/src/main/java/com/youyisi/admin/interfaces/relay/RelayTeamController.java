package com.youyisi.admin.interfaces.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.relay.RelayTeamService;
import com.youyisi.admin.application.relay.impl.RelayMemberServiceImpl;
import com.youyisi.admin.domain.relay.RelayTeam;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Controller
@RequestMapping("/relayteam")
public class RelayTeamController extends BaseController {

	@Autowired
	private RelayTeamService relayTeamService;

	@Autowired
	private RelayMemberServiceImpl relayMemberServiceImpl;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId) {
		Page<RelayTeam> page = new Page<RelayTeam>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		page.addParam("activityId", activityId);
		model.addAttribute("page", relayTeamService.queryPage(page));
		model.addAttribute("relayTeamNum", relayTeamService.countRelayTeamNumByActivityId(activityId));
		model.addAttribute("num", relayMemberServiceImpl.countRelayMemberNumber(activityId));
		model.addAttribute("activityId", activityId);
		return "relay/relayteamlist";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("RelayTeam", relayTeamService.get(id));
		return "relayteam/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RelayTeam RelayTeam) {
		relayTeamService.update(RelayTeam);
		return "redirect:relayteam/list";
	}

	@RequestMapping(value = "/clearing", method = RequestMethod.GET)
	public String clearing(Model model, Long id, Long activityId) {
		Integer row = relayTeamService.clearing(id);
		if (row == -1) {
			model.addAttribute(result, "结算失败");
		}
		return list(model, 1, null, activityId);
	}

}
