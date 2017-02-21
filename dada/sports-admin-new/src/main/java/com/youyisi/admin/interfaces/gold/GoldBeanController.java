package com.youyisi.admin.interfaces.gold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.gold.GoldBeanService;
import com.youyisi.admin.domain.gold.GoldBean;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/goldbean")
public class GoldBeanController{

	@Autowired
	private GoldBeanService GoldBeanService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<GoldBean> page = new Page<GoldBean>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", GoldBeanService.queryPage(page));
		return "goldbean/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("GoldBean", GoldBeanService.get(id));
		return "goldbean/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(GoldBean GoldBean) {
		GoldBeanService.update(GoldBean);
		return "redirect:goldbean/list";
	}
}

