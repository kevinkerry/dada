package com.youyisi.admin.interfaces.show;

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
import com.youyisi.admin.application.show.ShowService;
import com.youyisi.admin.domain.show.Show;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Controller
@RequestMapping("/show")
public class ShowController{

	@Autowired
	private ShowService showService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize, Show show) {
		Page<Show> page = new Page<Show>();
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
	        if(null != show) {
	            BeansUtils.extractAccessiblePropertiesToMap(params, show);
	        }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
	    page.addParams(params);
		model.addAttribute("page", showService.queryPage(page));
		return "show/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("show", showService.get(id));
		return "show/form";
	}

	@RequestMapping(value = "/{showId}/update", method = RequestMethod.GET)
	public String update(Model model, @PathVariable("showId")Long showId) {
		Show show = showService.get(showId);
		model.addAttribute("show", show);
		return "show/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Show show) {
        showService.update(show);
        return "redirect:show/list";
    }
	
	 @RequestMapping(value = "{showId}/delete")
    public String delete(Model model, @PathVariable("showId") Long showId) {
        Show show = showService.get(showId);
        if(null != show) {
            showService.delete(show); 
        }
        return list(model, 1, null, null);
    }
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(Model model, Long showId, Integer recommendFlag, Integer recommendOrder,String status) {
        Show show = showService.get(showId);
        if(show != null) {
            if(null != recommendFlag) {
                show.setRecommendFlag(recommendFlag);
            }
            if(StringUtils.isNotBlank(status)) {
                show.setStatus(status);
            }
            if(null != recommendOrder) {
                show.setFocusWeight(recommendOrder);
            }
            
            showService.update(show);
        }
        return list(model, 1, null, null);
    }
    
    @RequestMapping(value = "/batchRecommend")
    public String batchRecommend(Model model, Long[] showIds) {
        if(null != showIds && showIds.length > 0) {
            for(Long showId : showIds) {
                Show show = showService.get(showId); 
                if(null != show && null != show.getRecommendFlag() && show.getRecommendFlag() != 1) {
                    show.setRecommendFlag(1);
                    show.setFocusWeight(0);
                    showService.update(show);
                }
            }
        }
        return list(model, 1, null, null);
    }
}

