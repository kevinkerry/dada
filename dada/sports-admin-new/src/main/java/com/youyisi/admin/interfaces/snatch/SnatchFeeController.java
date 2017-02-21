package com.youyisi.admin.interfaces.snatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.snatch.SnatchFeeService;
import com.youyisi.admin.domain.snatch.SnatchFee;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Controller
@RequestMapping("/snatchfee")
public class SnatchFeeController{

	@Autowired
	private SnatchFeeService SnatchFeeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<SnatchFee> page = new Page<SnatchFee>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", SnatchFeeService.queryPage(page));
		return "snatchfee/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("SnatchFee", SnatchFeeService.get(id));
		return "snatchfee/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SnatchFee SnatchFee) {
		SnatchFeeService.update(SnatchFee);
		return "redirect:snatchfee/list";
	}
}

