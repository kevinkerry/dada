package com.youyisi.admin.interfaces.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.bet.BetCountBaseService;
import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/betcountbase")
public class BetCountBaseController{

	@Autowired
	private BetCountBaseService BetCountBaseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<BetCountBase> page = new Page<BetCountBase>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", BetCountBaseService.queryPage(page));
		return "betcountbase/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("BetCountBase", BetCountBaseService.get(id));
		return "betcountbase/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(BetCountBase BetCountBase) {
		BetCountBaseService.update(BetCountBase);
		return "redirect:betcountbase/list";
	}
}

