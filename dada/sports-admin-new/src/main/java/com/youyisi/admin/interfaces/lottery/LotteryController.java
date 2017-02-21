package com.youyisi.admin.interfaces.lottery;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.lottery.LotteryBaseService;
import com.youyisi.admin.application.lottery.LotteryService;
import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.admin.domain.lottery.Lottery;
import com.youyisi.admin.domain.lottery.LotteryBase;
import com.youyisi.admin.domain.lottery.LotteryTemplate;
import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

@Controller
@RequestMapping("/lottery")
public class LotteryController extends BaseController {

	@Autowired
	private LotteryService LotteryService;

	@Autowired
	private LotteryBaseService LotteryBaseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Lottery> page = new Page<Lottery>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", LotteryService.queryPage(page));
		return "lottery/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("Lottery", LotteryService.get(id));
		return "lottery/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Lottery Lottery) {
		LotteryService.update(Lottery);
		return "redirect:lottery/list";
	}

	@RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
	public String gotoPage(Model model, String page) {
		LotteryBase lotteryBase = LotteryBaseService.getLotteryBase();
		if (lotteryBase != null) {
			lotteryBase.setCronExpression(DateUtil.cronToDateTime(lotteryBase.getCronExpression()));
		} else {
			lotteryBase = new LotteryBase();
			lotteryBase.setBetCountBaseList(new ArrayList<BetCountBase>());
			lotteryBase.setDelayLotteryBaseList(new ArrayList<DelayLotteryBase>());
			lotteryBase.setLotteryWinBaseList(new ArrayList<LotteryWinBase>());
		}
		model.addAttribute("lotteryBase", lotteryBase);
		return "lottery/" + page;
	}

	@RequestMapping(value = "/addLottery", method = RequestMethod.POST)
	public String addLottery(Model model, LotteryBase lotteryBase) {
		Integer row = LotteryBaseService.addLotteryBase(lotteryBase);
		if (row == 0) {
			model.addAttribute(result, "设置失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/runLottery", method = RequestMethod.GET)
	public String runLottery(Model model, LotteryTemplate lotteryTemplate) {
		Integer row = LotteryService.runLottery(lotteryTemplate);
		if (row == 0) {
			model.addAttribute(result, "开奖失败");
		}
		return list(model, 1, null);
	}
}
