package com.youyisi.admin.interfaces.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.orders.OrdersService;
import com.youyisi.admin.domain.orders.Orders;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-20
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long userId) {
		Page<Orders> page = new Page<Orders>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		if (userId != null) {
			page.addParam("userId", userId);
		}
		model.addAttribute("page", ordersService.queryPage(page));
		return "orders/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("orders", ordersService.get(id));
		return "orders/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Orders orders) {
		ordersService.update(orders);
		return "redirect:orders/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public ResponseModel getOrderList(Integer currentPage, Integer pageSize, Long userId) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			Page<Orders> page = new Page<Orders>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(7);
			}
			page.addParam("userId", userId);
			page.addParam("payStatus", 1);
			response.setMap("order", ordersService.queryPage(page));
			Double countPay = ordersService.countPay(userId);
			if (countPay == null) {
				countPay = 0.0;
			}
			response.setMap("countPay", countPay);
			response.success();
		}
		return response;
	}
}
