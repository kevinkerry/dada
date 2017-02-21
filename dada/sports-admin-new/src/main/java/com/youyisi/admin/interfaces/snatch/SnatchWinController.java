package com.youyisi.admin.interfaces.snatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.snatch.SnatchWinService;
import com.youyisi.admin.domain.snatch.SnatchWin;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Controller
@RequestMapping("/snatchwin")
public class SnatchWinController{

	@Autowired
	private SnatchWinService SnatchWinService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<SnatchWin> page = new Page<SnatchWin>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", SnatchWinService.queryPage(page));
		return "snatchwin/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("SnatchWin", SnatchWinService.get(id));
		return "snatchwin/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SnatchWin SnatchWin) {
		SnatchWinService.update(SnatchWin);
		return "redirect:snatchwin/list";
	}
}

