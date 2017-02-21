package com.youyisi.admin.interfaces.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.run.RunService;
import com.youyisi.admin.domain.run.Run;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-27
 */
@Controller
@RequestMapping("/run")
public class RunController {

	@Autowired
	private RunService runService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Run> page = new Page<Run>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		page.addParam("date", DateUtil.calculateDate(DateUtil.currentDateForDay(), -1));
		model.addAttribute("page", runService.queryPage(page));
		return "run/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("run", runService.get(id));
		return "run/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Run run) {
		runService.update(run);
		return "redirect:run/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getListByUserId", method = RequestMethod.GET)
	public ResponseModel getListByUserId(Integer currentPage, Integer pageSize, Long userId) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			Page<Run> page = new Page<Run>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(10);
			}
			page.addParam("userId", userId);
			response.setMap("run", runService.queryPageByUserId(page));
			response.success();
		}
		return response;
	}

}
