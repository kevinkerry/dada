package com.youyisi.admin.interfaces.gift.gifttype;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v2.beans.BeansUtils;
import com.youyisi.admin.application.gift.gifttype.GiftTypeService;
import com.youyisi.admin.domain.gift.gifttype.GiftType;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Controller
@RequestMapping("/gifttype")
public class GiftTypeController{

	@Autowired
	private GiftTypeService giftTypeService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize,GiftType giftType, Boolean isParent) {
		Page<GiftType> page = new Page<GiftType>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(Integer.MAX_VALUE);
		Map<String, Object> params = new HashMap<String, Object>();
        try {
            if(null != giftType) {
                BeansUtils.extractAccessiblePropertiesToMap(params, giftType); 
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        params.put("isParent", isParent);
        page.addParams(params);
        
		model.addAttribute("page", giftTypeService.queryPage(page));
		return "gift/gifttype/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id,Model model) {
        model.addAttribute("giftType", giftTypeService.get(id));
        return "gift/gifttype/form";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, GiftType giftType) {
	    giftType.setSurplus(giftType.getLimit());
	    giftTypeService.save(giftType);
		return list(model, null, null, null, null);
	}

	@RequestMapping(value = "/{typeId}/update", method = RequestMethod.GET)
	@ResponseBody
    public WebResultInfoWrapper update(Model model, @PathVariable(value = "typeId")Long typeId) {
	    WebResultInfoWrapper wrapper = new WebResultInfoWrapper();
        GiftType giftType =  giftTypeService.get(typeId);
        wrapper.addResult("giftType", giftType);
        return wrapper;
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, GiftType giftType) {
		giftTypeService.update(giftType);
		return list(model, null, null, null, null);
	}
	
	@RequestMapping(value = "/{typeId}/delete")
    public String delete(Model model, @PathVariable(value = "typeId")Long typeId) {
	    GiftType giftType = giftTypeService.get(typeId);
        giftTypeService.delete(giftType);
        return list(model, null, null, null, null);
    }
	
	@RequestMapping(value = "/getAll")
	@ResponseBody
    public WebResultInfoWrapper getAll(GiftType giftType, Boolean isParent) {
	    WebResultInfoWrapper wrapper = new WebResultInfoWrapper();
	    Map<String, Object> params = new HashMap<String, Object>();
	    if(null != isParent) {
	        params.put("isParent", isParent);
	    }
	    
        List<GiftType> typeList = giftTypeService.getAll(params);
        wrapper.success();
        wrapper.addResult("typeList", typeList);
        return wrapper;
    }
}

