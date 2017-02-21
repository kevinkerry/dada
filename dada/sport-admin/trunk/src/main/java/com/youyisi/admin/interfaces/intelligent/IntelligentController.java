package com.youyisi.admin.interfaces.intelligent;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.intelligent.IntelligentService;
import com.youyisi.admin.domain.intelligent.Intelligent;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/intelligent")
public class IntelligentController {
	@Autowired
	private IntelligentService intelligentService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value="currentPage", required=false) Integer currentPage,Integer pageSize,String username,String mobile, @RequestParam(value="status", required=false) Integer status,@RequestParam(value="category", required=false) Integer category, Integer recommendFlag) {
		Page<Intelligent> page = new Page<Intelligent>();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
		page.addParam("status", status);
		page.addParam("category", category);
		try {
		    if(StringUtils.isNotEmpty(username)) {
		        page.addParam("username", URLDecoder.decode(username, "UTF-8")); 
		    }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		page.addParam("mobile", mobile);
		if(null != recommendFlag) {
            page.addParam("recommendFlag", recommendFlag);
        }
		page = intelligentService.queryPage(page);
		model.addAttribute("page", page);
		return "intelligent/list";
	}
	
	@RequestMapping(value = "/out", method = RequestMethod.POST)
	@ResponseBody
	public String out(Intelligent intelligent) {
		intelligentService.out(intelligent);
		return "success";
	}
	
	@RequestMapping(value = "/pass", method = RequestMethod.GET)
	@ResponseBody
	public String pass(Intelligent intelligent) {
		intelligentService.pass(intelligent);
		return "success";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        return "intelligent/form";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, Intelligent intelligent) {
	    intelligentService.save(intelligent);
        return list(model, 1,null, null, null, null, null, null);
    }
	
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		Intelligent u = intelligentService.get(id);
		model.addAttribute("intelligent", u);
		return "intelligent/form";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Intelligent intelligent) {
		intelligentService.save(intelligent);
		return "success";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(Model model, Long intelligentId, Integer recommendFlag, Integer orders ) {
	    Intelligent intelligent = intelligentService.get(intelligentId);
        if(intelligent != null) {
            if(null != recommendFlag) {
                intelligent.setRecommendFlag(recommendFlag);
            }
            intelligent.setOrders(orders);
            intelligentService.update(intelligent);
        }
        return list(model, 1, null, null, null,null,null,null);
    }
	
	@RequestMapping(value = "/batchRecommend")
    public String batchRecommend(Model model, Long[] intelligentIds) {
        if(null != intelligentIds && intelligentIds.length > 0) {
            for(Long IntelligentId : intelligentIds) {
                Intelligent intelligent = intelligentService.get(IntelligentId); 
                if(null != intelligent && null != intelligent.getRecommendFlag() && intelligent.getRecommendFlag() != 1) {
                    intelligent.setRecommendFlag(1);
                    intelligent.setOrders(0);
                    intelligentService.update(intelligent);
                }
            }
        }
        return list(model, 1, null, null, null,null,null,null);
    }
	
}
