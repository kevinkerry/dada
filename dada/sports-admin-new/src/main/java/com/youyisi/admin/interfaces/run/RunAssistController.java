package com.youyisi.admin.interfaces.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.run.RunAssistService;
import com.youyisi.admin.domain.run.RunAssist;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-25
 */
@Controller
@RequestMapping("/runassist")
public class RunAssistController {

	@Autowired
	private RunAssistService runAssistService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<RunAssist> page = new Page<RunAssist>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", runAssistService.queryPage(page));
		return "runassist/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("RunAssist", runAssistService.get(id));
		return "runassist/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RunAssist RunAssist) {
		runAssistService.update(RunAssist);
		return "redirect:runassist/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getRunAssistList", method = RequestMethod.GET)
	public ResponseModel getRunAssistList(Long runId) {
		ResponseModel response = new ResponseModel();
		if (runId != null) {
			response.setMap("record", runAssistService.getRunAssistByRunId(runId));
			response.success();
		}
		return response;
	}
}
