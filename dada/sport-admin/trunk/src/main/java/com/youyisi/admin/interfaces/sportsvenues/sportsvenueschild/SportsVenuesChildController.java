package com.youyisi.admin.interfaces.sportsvenues.sportsvenueschild;

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
import com.youyisi.admin.application.sportsvenues.sportsvenueschild.SportsVenuesChildService;
import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Controller
@RequestMapping("/sportsvenueschild")
public class SportsVenuesChildController{

	@Autowired
	private SportsVenuesChildService sportsVenuesChildService;
	
	@Autowired
    private SportsVenuesService sportsVenuesService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage,Integer pageSize, Long venueId, String childVenueName) {
	    Page<SportsVenuesChild> page = new Page<SportsVenuesChild>();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        try {
            if(StringUtils.isNotBlank(childVenueName)) {
                childVenueName = URLDecoder.decode(childVenueName,"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
 
        page.addParam("venueId", venueId);
        page.addParam("childVenueName", childVenueName);
        
        model.addAttribute("page", sportsVenuesChildService.queryPage(page));
        model.addAttribute("venueId", venueId);
        SportsVenues sVenues = sportsVenuesService.get(venueId);
        model.addAttribute("venueName", sVenues.getVenueName());
        
		model.addAttribute("page", sportsVenuesChildService.queryPage(page));
		return "sportsvenues/sportsvenueschild/list";
	}
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportsVenuesChild", sportsVenuesChildService.get(id));
		return "sportsvenues/sportsvenueschild/form";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, Long venueId) {
	    model.addAttribute("venueId", venueId);
        return "sportsvenues/sportsvenueschild/add";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, SportsVenuesChild sportsVenuesChild) {
	    sportsVenuesChildService.save(sportsVenuesChild);
        SportsVenues sportsVenue = sportsVenuesService.get(sportsVenuesChild.getVenueId());
        return list(model, 1 ,null, sportsVenue.getVenueId(), null);
    }
	
	@RequestMapping(value = "/{childVenueId}/update", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable(value = "childVenueId")Long childVenueId) {
	    model.addAttribute("childVenue", sportsVenuesChildService.get(childVenueId));
		return "sportsvenues/sportsvenueschild/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, SportsVenuesChild sportsVenuesChild) {
        sportsVenuesChildService.update(sportsVenuesChild);
        model.addAttribute("childVenue", sportsVenuesChild);
        return "sportsvenues/sportsvenueschild/update";
    }
	
	@RequestMapping(value = "/{childVenueId}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(value = "childVenueId")Long childVenueId) {
        SportsVenuesChild childVenue = sportsVenuesChildService.get(childVenueId);
        SportsVenues sportsVenue  = null;
        if(null != childVenue) {
            sportsVenue = childVenue.getSportsVenue();
            sportsVenuesChildService.delete(childVenue);
        }
        Long venueId = null;
        if(null != sportsVenue) {
            venueId = sportsVenue.getVenueId();
        }
        return list(model, 1 ,null, venueId, null);
    }
}

