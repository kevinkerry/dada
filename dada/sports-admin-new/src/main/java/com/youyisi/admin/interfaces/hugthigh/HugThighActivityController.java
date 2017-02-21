package com.youyisi.admin.interfaces.hugthigh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.hugthigh.HugThighActivityService;
import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Controller
@RequestMapping("/hugthighactivity")
public class HugThighActivityController{

	@Autowired
	private HugThighActivityService hugThighActivityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<HugThighActivity> page = new Page<HugThighActivity>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", hugThighActivityService.queryPage(page));
		return "hugthighactivity/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("hugThighActivity", hugThighActivityService.get(id));
		return "hugthighactivity/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(HugThighActivity hugThighActivity) {
		hugThighActivityService.update(hugThighActivity);
		return "redirect:hugthighactivity/list";
	}
}

