package com.youyisi.admin.interfaces.sportsvenues.sportvenuedistrict;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.sportsvenues.sportsvenueschild.SportsVenuesChildService;
import com.youyisi.admin.application.sportsvenues.sportvenuedistrict.SportVenueDistrictService;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.admin.domain.sportsvenues.sportvenuedistrict.SportVenueDistrict;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Controller
@RequestMapping("/sportvenuedistrict")
public class SportVenueDistrictController{

	@Autowired
	private SportVenueDistrictService districtService;
	
	@Autowired
    private SportsVenuesChildService venueChildService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage,Integer pageSize,Long childVenueId) {
		Page<SportVenueDistrict> page = new Page<SportVenueDistrict>();
		if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        
        if(null != childVenueId) {
            page.addParam("childVenueId", childVenueId);
        }
        
        SportsVenuesChild sVenuesChild = venueChildService.get(childVenueId);
        model.addAttribute("childVenueId", childVenueId);
        model.addAttribute("childVenueName", sVenuesChild.getChildVenueName());
		model.addAttribute("page", districtService.queryPage(page));
		
		return "sportsvenues/sportvenuedistrict/list";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public WebResultInfoWrapper check(Model model,SportVenueDistrict sportVenueDistrict) {
        WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
        String msg = "";
        String districtCode = sportVenueDistrict.getDistrictCode();
        if(StringUtils.isEmpty(districtCode)) {
            msg = "片区编号为空，请输入片区编号！";
            webResultInfoWrapper.failed();
            webResultInfoWrapper.addResult("msg", msg);
            return webResultInfoWrapper;
        }else {
            if(districtService.isExistDistrict(sportVenueDistrict)) {
                msg = "片区编号已存在，请重新输入片区编号！";
                webResultInfoWrapper.failed();
                webResultInfoWrapper.addResult("msg", msg);
                return webResultInfoWrapper;
            }
        }
        
        webResultInfoWrapper.success();
        
        return webResultInfoWrapper;
    }
	
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportVenueDistrict", districtService.get(id));
		return "sportsvenues/sportvenuedistrict/form";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, Long childVenueId) {
        model.addAttribute("childVenueId", childVenueId);
        return "sportsvenues/sportvenuedistrict/add";
    }
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, SportVenueDistrict sportVenueDistrict) {
	    districtService.save(sportVenueDistrict);
	    SportsVenuesChild sportsVenuesChild = venueChildService.get(sportVenueDistrict.getChildVenueId());
        return list(model,1,null,sportsVenuesChild.getChildVenueId());
    }
	@RequestMapping(value = "/{districtId}/update", method = RequestMethod.GET)
	@ResponseBody
	public WebResultInfoWrapper update(Model model, @PathVariable(value = "districtId")Long districtId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
        webResultInfoWrapper.success();
        webResultInfoWrapper.addResult("venueDistrict", districtService.get(districtId));
		return webResultInfoWrapper;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, SportVenueDistrict sportVenueDistrict) {
	    districtService.update(sportVenueDistrict);
	    SportsVenuesChild sportsVenuesChild = venueChildService.get(sportVenueDistrict.getChildVenueId());
        return list(model, 1 ,null,sportsVenuesChild.getChildVenueId());
    }
	
	@RequestMapping(value = "/{districtId}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(value = "districtId")Long districtId) {
        SportVenueDistrict district = districtService.get(districtId);
        SportsVenuesChild sportsVenuesChild = null;
        if(null != district) {
            sportsVenuesChild = district.getSportsVenuesChild();
            districtService.delete(district);
        }
        Long childVenueId = null;
        if(null != sportsVenuesChild) {
            childVenueId = sportsVenuesChild.getChildVenueId();
        }
        return list(model, 1 ,null, childVenueId);
    }
	
	@RequestMapping(value = "/modify")
    public String modify(Model model, Long districtId , Integer districtStatus) {
	    SportVenueDistrict district = districtService.get(districtId);
	    district.setDistrictStatus(districtStatus);
        districtService.update(district);
        SportsVenuesChild sportsVenuesChild = venueChildService.get(district.getChildVenueId());
        return list(model, 1 ,null,sportsVenuesChild.getChildVenueId());
    }
}

