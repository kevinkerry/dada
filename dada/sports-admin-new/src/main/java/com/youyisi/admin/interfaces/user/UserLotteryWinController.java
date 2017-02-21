package com.youyisi.admin.interfaces.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.user.UserLotteryWinService;
import com.youyisi.admin.domain.user.UserLotteryWin;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-24
 */
@Controller
@RequestMapping("/userlotterywin")
public class UserLotteryWinController{

	@Autowired
	private UserLotteryWinService UserLotteryWinService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<UserLotteryWin> page = new Page<UserLotteryWin>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", UserLotteryWinService.queryPage(page));
		return "userlotterywin/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("UserLotteryWin", UserLotteryWinService.get(id));
		return "userlotterywin/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(UserLotteryWin UserLotteryWin) {
		UserLotteryWinService.update(UserLotteryWin);
		return "redirect:userlotterywin/list";
	}
}

