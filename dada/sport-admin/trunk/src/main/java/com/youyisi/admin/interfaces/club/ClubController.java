package com.youyisi.admin.interfaces.club;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mchange.v2.beans.BeansUtils;
import com.youyisi.admin.application.club.ClubService;
import com.youyisi.admin.domain.club.Club;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Controller
@RequestMapping("/club")
public class ClubController{

	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize, Club club) {
		Page<Club> page = new Page<Club>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		try {
		    if(null != club) {
		       BeansUtils.extractAccessiblePropertiesToMap(params, club);
		    }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
		
		page.addParams(params);
		model.addAttribute("page", clubService.queryPage(page));
		return "club/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("club", clubService.get(id));
		return "club/form";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "club/add";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, Club club) {
	    clubService.save(club);
        return list(model, 1, null, null);
    }
	
	@RequestMapping(value = "{clubId}/update", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "clubId")Long clubId) {
        model.addAttribute("club", clubService.get(clubId));
        return "club/update";
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, Club club) {
		clubService.update(club);
		model.addAttribute("club", club);
		return "club/update";
	}
	
	@RequestMapping(value = "/modify")
    public String modify(Model model, Long clubId, Integer recommendFlag, String clubStatus) {
        Club club = clubService.get(clubId);
        if(club != null) {
            if(null != recommendFlag) {
                club.setRecommendFlag(recommendFlag);
            }
            if(StringUtils.isNotBlank(clubStatus)) {
                club.setStatus(clubStatus);
            }
            
            clubService.modify(club);
        }
        return list(model, 1, null, null);
    }
    
    @RequestMapping(value = "/batchRecommend")
    public String batchRecommend(Model model, Long[] clubIds) {
        if(null != clubIds && clubIds.length > 0) {
            for(Long clubId : clubIds) {
                Club club = clubService.get(clubId); 
                if(null != club) {
                    club.setRecommendFlag(1);
                    clubService.update(club);
                }
            }
        }
        return list(model, 1, null, null);
    }
}

