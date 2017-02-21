package com.youyisi.admin.interfaces.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.lottery.LotteryWinBaseService;
import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/lotterywinbase")
public class LotteryWinBaseController{

	@Autowired
	private LotteryWinBaseService LotteryWinBaseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<LotteryWinBase> page = new Page<LotteryWinBase>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", LotteryWinBaseService.queryPage(page));
		return "lotterywinbase/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("LotteryWinBase", LotteryWinBaseService.get(id));
		return "lotterywinbase/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(LotteryWinBase LotteryWinBase) {
		LotteryWinBaseService.update(LotteryWinBase);
		return "redirect:lotterywinbase/list";
	}
}

