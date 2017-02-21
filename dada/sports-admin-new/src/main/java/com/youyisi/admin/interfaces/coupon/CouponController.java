package com.youyisi.admin.interfaces.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.coupon.CouponService;
import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Coupon> page = new Page<Coupon>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", couponService.queryPage(page));
		return "coupon/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("coupon", couponService.get(id));
		return "coupon/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Coupon coupon) {
		couponService.update(coupon);
		return "redirect:coupon/list";
	}

}
