package com.youyisi.admin.interfaces.indiana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.indiana.IndianaService;
import com.youyisi.admin.domain.indiana.Indiana;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Controller
@RequestMapping("/indiana")
public class IndianaController{

	@Autowired
	private IndianaService indianaService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Indiana> page = new Page<Indiana>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		model.addAttribute("page", indianaService.queryPage(page));
		return "indiana/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("indiana", indianaService.get(id));
		return "indiana/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Indiana indiana,Model model) {
		indianaService.update(indiana);
		return list(model,1,null);
	}
}

