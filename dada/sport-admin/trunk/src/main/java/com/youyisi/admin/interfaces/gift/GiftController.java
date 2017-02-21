package com.youyisi.admin.interfaces.gift;

import java.beans.IntrospectionException;
import java.util.ArrayList;
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
import com.youyisi.admin.application.gift.GiftService;
import com.youyisi.admin.domain.gift.Gift;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Controller
@RequestMapping("/gift")
public class GiftController{

	@Autowired
	private GiftService giftService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize, Gift gift) {
		Page<Gift> page = new Page<Gift>();
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
            if(null != gift) {
                BeansUtils.extractAccessiblePropertiesToMap(params, gift);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        page.addParams(params);
		model.addAttribute("page", giftService.queryPage(page));
		return "gift/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("gift", giftService.get(id));
		return "gift/form";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String detail(Model model, Gift gift, Integer[] levels) {
	    List<Gift> giftList = new ArrayList<Gift>();
	    if(null != levels && levels.length > 0) {
	        for(Integer level : levels) {
	            Gift newFGift = new Gift();
	            newFGift.setName(gift.getName());
	            newFGift.setProbability(gift.getProbability());
	            newFGift.setTypeId(gift.getTypeId());
	            newFGift.setLevel(level);
	            giftList.add(newFGift);
	        }
	    }
	    giftService.saveBatch(giftList);
        return list(model, 1, null, null);
    }

	@RequestMapping(value = "/{giftId}/get", method = RequestMethod.GET)
	@ResponseBody
	public WebResultInfoWrapper get(@PathVariable(value = "giftId")Long giftId) {
	    WebResultInfoWrapper wrapper = new WebResultInfoWrapper();
	    wrapper.addResult("gift", giftService.get(giftId));
		return wrapper;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, Gift gift) {
        giftService.update(gift);
        return list(model, 1, null, null);
    }
	
	@RequestMapping(value = "/{giftId}/delete")
    public String update(Model model, @PathVariable(value = "giftId")Long giftId) {
	    Gift gift = giftService.get(giftId);
        giftService.delete(gift);
        return list(model, 1, null, null);
    }
}

