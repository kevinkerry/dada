package com.youyisi.admin.interfaces.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.annual.AnnualYieldService;
import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Controller
@RequestMapping("/annualyield")
public class AnnualYieldController{

	@Autowired
	private AnnualYieldService AnnualYieldService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<AnnualYield> page = new Page<AnnualYield>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", AnnualYieldService.queryPage(page));
		return "annualyield/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("AnnualYield", AnnualYieldService.get(id));
		return "annualyield/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(AnnualYield AnnualYield) {
		AnnualYieldService.update(AnnualYield);
		return "redirect:annualyield/list";
	}
}

