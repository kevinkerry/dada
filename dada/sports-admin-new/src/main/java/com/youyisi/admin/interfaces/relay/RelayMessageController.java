package com.youyisi.admin.interfaces.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.relay.RelayMessageService;
import com.youyisi.admin.domain.relay.RelayMessage;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Controller
@RequestMapping("/relaymessage")
public class RelayMessageController {

	@Autowired
	private RelayMessageService relayMessageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId, Long teamId) {
		Page<RelayMessage> page = new Page<RelayMessage>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		if (teamId != null) {
			page.addParam("teamId", teamId);
		}
		model.addAttribute("teamId", teamId);
		model.addAttribute("activityId", activityId);
		model.addAttribute("page", relayMessageService.queryPage(page));
		return "relay/relaymessage";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("RelayMessage", relayMessageService.get(id));
		return "relaymessage/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RelayMessage RelayMessage) {
		relayMessageService.update(RelayMessage);
		return "redirect:relaymessage/list";
	}
}
