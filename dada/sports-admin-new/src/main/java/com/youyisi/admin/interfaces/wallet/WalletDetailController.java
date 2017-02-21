package com.youyisi.admin.interfaces.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.wallet.WalletDetailService;
import com.youyisi.admin.domain.wallet.WalletDetail;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Controller
@RequestMapping("/walletdetail")
public class WalletDetailController extends BaseController {

	@Autowired
	private WalletDetailService WalletDetailService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<WalletDetail> page = new Page<WalletDetail>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", WalletDetailService.queryPage(page));
		return "walletdetail/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("WalletDetail", WalletDetailService.get(id));
		return "walletdetail/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(WalletDetail WalletDetail) {
		WalletDetailService.update(WalletDetail);
		return "redirect:walletdetail/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getListByUserId", method = RequestMethod.GET)
	public ResponseModel getListByUserId(Model model, Long userId, Integer currentPage, Integer pageSize) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			Page<WalletDetail> page = new Page<WalletDetail>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(7);
			}
			page.addParam("userId", userId);
			response.setMap("record", WalletDetailService.queryPageByUserId(page));
			response.success();
		}
		return response;
	}
}
