package com.youyisi.admin.interfaces.sportsvenues;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.sportsvenues.SportsVenuesService;
import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Controller
@RequestMapping("/sportsvenues")
public class SportsVenuesController{

	@Autowired
	private SportsVenuesService sportsvenuesService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, String venueName, String province, String city, String district) {
		Page<SportsVenues> page = new Page<SportsVenues>();
		if(null != currentPage) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        
        try {
            if(StringUtils.isNotEmpty(venueName)) {
                page.addParam("venueName", URLDecoder.decode(venueName, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(province)) {
                page.addParam("province", URLDecoder.decode(province, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(city)) {
                page.addParam("city", URLDecoder.decode(city, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(district)) {
                page.addParam("district", URLDecoder.decode(district, "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		model.addAttribute("page", sportsvenuesService.queryPage(page));
		return "sportsvenues/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        return "sportsvenues/add";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, SportsVenues sportsvenues) {
        sportsvenuesService.save(sportsvenues);
        return list(model, 1 ,null, null, null, null, null);
    }
	
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportsvenues", sportsvenuesService.get(id));
		return "sportsvenues/form";
	}
	
	@RequestMapping(value = "/{venueId}/update", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable(value = "venueId")Long venueId) {
	    model.addAttribute("sportsVenue", sportsvenuesService.get(venueId));
		return "sportsvenues/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, SportsVenues sportsvenues) {
        sportsvenuesService.update(sportsvenues);
        model.addAttribute("sportsVenue", sportsvenues);
        return "sportsvenues/update";
    }
	
	@RequestMapping(value = "/{venueId}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(value = "venueId")Long venueId) {
        SportsVenues venue = sportsvenuesService.get(venueId);
        if(null != venue) {//只做逻辑删除
            sportsvenuesService.delete(venue);
        }
        return list(model, 1 ,null, null, null, null, null);
    }
}

