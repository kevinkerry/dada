package com.youyisi.admin.interfaces.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.bet.BetCountService;
import com.youyisi.admin.domain.bet.BetCount;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/betcount")
public class BetCountController{

	@Autowired
	private BetCountService BetCountService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<BetCount> page = new Page<BetCount>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", BetCountService.queryPage(page));
		return "betcount/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("BetCount", BetCountService.get(id));
		return "betcount/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(BetCount BetCount) {
		BetCountService.update(BetCount);
		return "redirect:betcount/list";
	}
}

