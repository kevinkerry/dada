package com.youyisi.admin.interfaces.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.lottery.LotteryWinService;
import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/lotterywin")
public class LotteryWinController {

	@Autowired
	private LotteryWinService LotteryWinService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<LotteryWin> page = new Page<LotteryWin>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", LotteryWinService.queryPage(page));
		return "lotterywin/info";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("LotteryWin", LotteryWinService.get(id));
		return "lotterywin/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(LotteryWin LotteryWin) {
		LotteryWinService.update(LotteryWin);
		return "redirect:lotterywin/list";
	}
}
