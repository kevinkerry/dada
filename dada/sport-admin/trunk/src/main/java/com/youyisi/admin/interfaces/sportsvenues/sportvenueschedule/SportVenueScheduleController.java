package com.youyisi.admin.interfaces.sportsvenues.sportvenueschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.sportsvenues.sportvenueschedule.SportVenueScheduleService;
import com.youyisi.admin.domain.sportsvenues.sportvenueschedule.SportVenueSchedule;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Controller
@RequestMapping("/sportvenueschedule")
public class SportVenueScheduleController{

	@Autowired
	private SportVenueScheduleService sportVenueScheduleService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,Integer currentPage,Integer pageSize,Long districtId) {
		Page<SportVenueSchedule> page = new Page<SportVenueSchedule>();
		if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        if(null != districtId) {
            page.addParam("districtId", districtId); 
        }
        
		model.addAttribute("page", sportVenueScheduleService.queryPage(page));
		return "sportsvenues/sportvenueschedule/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportVenueSchedule", sportVenueScheduleService.get(id));
		return "sportsvenues/sportvenueschedule/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SportVenueSchedule sportVenueSchedule) {
		sportVenueScheduleService.update(sportVenueSchedule);
		return "sportsvenues/sportvenueschedule/list";
	}
}

