package com.youyisi.admin.interfaces.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.distance.DistanceService;
import com.youyisi.admin.domain.distance.Distance;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-02
 */
@Controller
@RequestMapping("/distance")
public class DistanceController{

	@Autowired
	private DistanceService distanceService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Distance> page = new Page<Distance>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", distanceService.queryPage(page));
		return "distance/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("distance", distanceService.get(id));
		return "distance/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Distance distance) {
		distanceService.update(distance);
		return "redirect:distance/list";
	}
}

