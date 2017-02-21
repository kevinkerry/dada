package com.youyisi.admin.interfaces.gold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.gold.GoldBeanDetailService;
import com.youyisi.admin.domain.gold.GoldBeanDetail;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/goldbeandetail")
public class GoldBeanDetailController{

	@Autowired
	private GoldBeanDetailService GoldBeanDetailService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<GoldBeanDetail> page = new Page<GoldBeanDetail>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", GoldBeanDetailService.queryPage(page));
		return "goldbeandetail/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("GoldBeanDetail", GoldBeanDetailService.get(id));
		return "goldbeandetail/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(GoldBeanDetail GoldBeanDetail) {
		GoldBeanDetailService.update(GoldBeanDetail);
		return "redirect:goldbeandetail/list";
	}
}

