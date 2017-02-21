package com.youyisi.admin.interfaces.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.lottery.LotteryBaseService;
import com.youyisi.admin.domain.lottery.LotteryBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/lotterybase")
public class LotteryBaseController{

	@Autowired
	private LotteryBaseService LotteryBaseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<LotteryBase> page = new Page<LotteryBase>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", LotteryBaseService.queryPage(page));
		return "lotterybase/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("LotteryBase", LotteryBaseService.get(id));
		return "lotterybase/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(LotteryBase LotteryBase) {
		LotteryBaseService.update(LotteryBase);
		return "redirect:lotterybase/list";
	}
}

