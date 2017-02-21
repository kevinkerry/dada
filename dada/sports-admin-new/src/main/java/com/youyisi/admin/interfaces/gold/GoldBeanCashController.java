package com.youyisi.admin.interfaces.gold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.gold.GoldBeanCashService;
import com.youyisi.admin.domain.gold.GoldBeanCash;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/goldbeancash")
public class GoldBeanCashController{

	@Autowired
	private GoldBeanCashService GoldBeanCashService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<GoldBeanCash> page = new Page<GoldBeanCash>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", GoldBeanCashService.queryPage(page));
		return "goldbeancash/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("GoldBeanCash", GoldBeanCashService.get(id));
		return "goldbeancash/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(GoldBeanCash GoldBeanCash) {
		GoldBeanCashService.update(GoldBeanCash);
		return "redirect:goldbeancash/list";
	}
}

