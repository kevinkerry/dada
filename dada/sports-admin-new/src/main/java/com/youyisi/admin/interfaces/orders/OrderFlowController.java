package com.youyisi.admin.interfaces.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.orders.OrderFlowService;
import com.youyisi.admin.domain.orders.OrderFlow;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-20
 */
@Controller
@RequestMapping("/orderflow")
public class OrderFlowController{

	@Autowired
	private OrderFlowService orderFlowService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<OrderFlow> page = new Page<OrderFlow>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", orderFlowService.queryPage(page));
		return "orderflow/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("orderFlow", orderFlowService.get(id));
		return "orderflow/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(OrderFlow orderFlow) {
		orderFlowService.update(orderFlow);
		return "redirect:orderflow/list";
	}
}

