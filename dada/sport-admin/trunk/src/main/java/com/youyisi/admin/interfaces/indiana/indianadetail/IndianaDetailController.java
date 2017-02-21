package com.youyisi.admin.interfaces.indiana.indianadetail;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v2.beans.BeansUtils;
import com.youyisi.admin.application.indiana.indianadetail.IndianaDetailService;
import com.youyisi.admin.domain.indiana.indianadetail.IndianaDetail;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Controller
@RequestMapping("/indianadetail")
public class IndianaDetailController{

	@Autowired
	private IndianaDetailService indianaDetailService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize, IndianaDetail indianaDetail) {
		Page<IndianaDetail> page = new Page<IndianaDetail>();
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
		    if(null != params) {
		        BeansUtils.extractAccessiblePropertiesToMap(params, indianaDetail); 
		    }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
		page.addParams(params);
		
		model.addAttribute("page", indianaDetailService.queryPage(page));
		return "indiana/indianadetail/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("indianaDetail", indianaDetailService.get(id));
		return "indiana/indianadetail/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(IndianaDetail indianaDetail) {
		indianaDetailService.update(indianaDetail);
		return "redirect:indianadetail/list";
	}
	
	@RequestMapping(value = "/getAll")
	@ResponseBody
    public WebResultInfoWrapper getAll() {
	    WebResultInfoWrapper wrapper = new WebResultInfoWrapper();
	    Page<IndianaDetail> page = new Page<IndianaDetail>();
	    wrapper.addResult("page", indianaDetailService.queryPage(page));
        return wrapper;
    }
}

