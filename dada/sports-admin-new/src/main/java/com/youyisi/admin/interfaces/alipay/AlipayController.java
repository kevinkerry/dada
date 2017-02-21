package com.youyisi.admin.interfaces.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.alipay.AlipayService;
import com.youyisi.admin.domain.alipay.Alipay;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController{

	@Autowired
	private AlipayService alipayService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Alipay> page = new Page<Alipay>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", alipayService.queryPage(page));
		return "alipay/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("alipay", alipayService.get(id));
		return "alipay/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Alipay alipay) {
		alipayService.update(alipay);
		return "redirect:alipay/list";
	}
}

