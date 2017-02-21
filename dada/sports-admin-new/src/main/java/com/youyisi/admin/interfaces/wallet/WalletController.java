package com.youyisi.admin.interfaces.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.wallet.WalletService;
import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Controller
@RequestMapping("/wallet")
public class WalletController{

	@Autowired
	private WalletService WalletService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Wallet> page = new Page<Wallet>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", WalletService.queryPage(page));
		return "wallet/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("Wallet", WalletService.get(id));
		return "wallet/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Wallet Wallet) {
		WalletService.update(Wallet);
		return "redirect:wallet/list";
	}
}

