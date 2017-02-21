package com.youyisi.admin.interfaces.sportsvenues.sportvenueorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.sportsvenues.sportvenueorder.SportVenueOrderService;
import com.youyisi.admin.domain.sportsvenues.sportvenueorder.SportVenueOrder;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Controller
@RequestMapping("/sportvenueorder")
public class SportVenueOrderController{

	@Autowired
	private SportVenueOrderService sportVenueOrderService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		Page<SportVenueOrder> page = new Page<SportVenueOrder>();
		model.addAttribute("page", sportVenueOrderService.queryPage(page));
		return "sportvenueorder/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportVenueOrder", sportVenueOrderService.get(id));
		return "sportvenueorder/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SportVenueOrder sportVenueOrder) {
		sportVenueOrderService.update(sportVenueOrder);
		return "redirect:sportvenueorder/list";
	}
}

