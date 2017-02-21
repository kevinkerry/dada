package com.youyisi.admin.interfaces.sportsvenues.sportvenueorderitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.sportsvenues.sportvenueorderitem.SportVenueOrderItemService;
import com.youyisi.admin.domain.sportsvenues.sportvenueorderitem.SportVenueOrderItem;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Controller
@RequestMapping("/sportvenueorderitem")
public class SportVenueOrderItemController{

	@Autowired
	private SportVenueOrderItemService sportVenueOrderItemService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
	    Page<SportVenueOrderItem> page = new Page<SportVenueOrderItem>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		model.addAttribute("page", sportVenueOrderItemService.queryPage(page));
		return "sportvenueorderitem/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportVenueOrderItem", sportVenueOrderItemService.get(id));
		return "sportvenueorderitem/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SportVenueOrderItem sportVenueOrderItem) {
	    sportVenueOrderItemService.update(sportVenueOrderItem);
		return "redirect:sportvenueorderitem/list";
	}
}

