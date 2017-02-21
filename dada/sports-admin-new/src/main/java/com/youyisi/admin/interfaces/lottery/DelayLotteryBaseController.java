package com.youyisi.admin.interfaces.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.lottery.DelayLotteryBaseService;
import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-24
 */
@Controller
@RequestMapping("/delaylotterybase")
public class DelayLotteryBaseController{

	@Autowired
	private DelayLotteryBaseService DelayLotteryBaseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<DelayLotteryBase> page = new Page<DelayLotteryBase>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", DelayLotteryBaseService.queryPage(page));
		return "delaylotterybase/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("DelayLotteryBase", DelayLotteryBaseService.get(id));
		return "delaylotterybase/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(DelayLotteryBase DelayLotteryBase) {
		DelayLotteryBaseService.update(DelayLotteryBase);
		return "redirect:delaylotterybase/list";
	}
}

