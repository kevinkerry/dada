package com.youyisi.admin.interfaces.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.annual.AnnualYieldDetailService;
import com.youyisi.admin.domain.annual.AnnualYieldDetail;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Controller
@RequestMapping("/annualyielddetail")
public class AnnualYieldDetailController{

	@Autowired
	private AnnualYieldDetailService AnnualYieldDetailService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<AnnualYieldDetail> page = new Page<AnnualYieldDetail>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", AnnualYieldDetailService.queryPage(page));
		return "annualyielddetail/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("AnnualYieldDetail", AnnualYieldDetailService.get(id));
		return "annualyielddetail/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(AnnualYieldDetail AnnualYieldDetail) {
		AnnualYieldDetailService.update(AnnualYieldDetail);
		return "redirect:annualyielddetail/list";
	}
}

