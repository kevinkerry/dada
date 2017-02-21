package com.youyisi.admin.interfaces.hugthigh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.hugthigh.HugThighService;
import com.youyisi.admin.domain.hugthigh.HugThigh;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Controller
@RequestMapping("/hugthigh")
public class HugThighController {

	@Autowired
	private HugThighService hugThighService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Long thighId, Integer currentPage, Integer pageSize, String field, String sort) {
		Page<HugThigh> page = new Page<HugThigh>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		if (null != thighId) {
			page.addParam("thighId", thighId);
		}
		if (null != field) {
			page.addParam("field", field);
			page.addParam("sort", sort);
		}
		model.addAttribute("page", hugThighService.queryPage(page));
		return "hugthigh/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("hugThigh", hugThighService.get(id));
		return "hugthigh/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(HugThigh hugThigh) {
		hugThighService.update(hugThigh);
		return "redirect:hugthigh/list";
	}
}
