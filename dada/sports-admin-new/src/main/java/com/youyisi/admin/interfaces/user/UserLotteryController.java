package com.youyisi.admin.interfaces.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.lottery.LotteryService;
import com.youyisi.admin.application.lottery.LotteryWinService;
import com.youyisi.admin.application.user.UserLotteryService;
import com.youyisi.admin.domain.user.UserLottery;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-22
 */
@Controller
@RequestMapping("/userlottery")
public class UserLotteryController extends BaseController {

	@Autowired
	private UserLotteryService userLotteryService;

	@Autowired
	private LotteryService lotteryService;

	@Autowired
	private LotteryWinService lotteryWinService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long lotteryId) {
		Page<UserLottery> page = new Page<UserLottery>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		page.addParam("lotteryId", lotteryId);
		model.addAttribute("lotteryId", lotteryId);
		model.addAttribute("page", userLotteryService.queryPage(page));
		model.addAttribute("lottery", lotteryService.getLotteryById(lotteryId));
		model.addAttribute("grant", lotteryWinService.sumGrantGoldBean(lotteryId));
		return "lottery/info";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("UserLottery", userLotteryService.get(id));
		return "userlottery/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(UserLottery UserLottery) {
		userLotteryService.update(UserLottery);
		return "redirect:userlottery/list";
	}
}
