package com.youyisi.admin.interfaces.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.step.StepService;
import com.youyisi.admin.domain.step.Step;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-02
 */
@Controller
@RequestMapping("/step")
public class StepController extends BaseController {

	@Autowired
	private StepService stepService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Step> page = new Page<Step>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(50);
		}
		page.addParam("step", 30000);
		page.addParam("date", DateUtil.currentDateForDay());
		model.addAttribute("page", stepService.queryPage(page));
		return "step/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("step", stepService.get(id));
		return "step/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Step step) {
		stepService.update(step);
		return "redirect:step/list";
	}

	@RequestMapping(value = "/freeze", method = RequestMethod.GET)
	public String freeze(Model model, Long stepId) {
		Integer freeze = stepService.freeze(stepId);
		if (freeze < 2) {
			model.addAttribute(result, "冻结失败");
		}
		return list(model, 1, null);
	}
}
