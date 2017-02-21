package com.youyisi.admin.interfaces.gold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.gold.GoldBeanRechargeService;
import com.youyisi.admin.domain.gold.GoldBeanRecharge;
import com.youyisi.admin.interfaces.BaseController;

/**
 * @author shuye
 * @time 2016-10-26
 */
@Controller
@RequestMapping("/goldbeanrecharge")
public class GoldBeanRechargeController extends BaseController {

	@Autowired
	private GoldBeanRechargeService goldBeanRechargeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", goldBeanRechargeService.getGoldBeanRechargeList());
		return "gold/paritiesset";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("GoldBeanRecharge", goldBeanRechargeService.get(id));
		return "goldbeanrecharge/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(GoldBeanRecharge GoldBeanRecharge) {
		goldBeanRechargeService.update(GoldBeanRecharge);
		return "redirect:goldbeanrecharge/list";
	}

	@RequestMapping(value = "/saveGoldBeanRecharge", method = RequestMethod.POST)
	public String saveGoldBeanRecharge(Model model, GoldBeanRecharge goldBeanRecharge) {
		Integer row = goldBeanRechargeService.saveGoldBeanRecharge(goldBeanRecharge);
		if (row == 0) {
			model.addAttribute(result, "保存失败");
		}
		return list(model);
	}

}
