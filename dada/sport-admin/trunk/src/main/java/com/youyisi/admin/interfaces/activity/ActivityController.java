package com.youyisi.admin.interfaces.activity;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2015-07-03 14 51 01
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    
	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage,Integer pageSize,String activityOrg, String activityTitle, String parentCategory, String beginTime, String endTime, Integer recommendFlag) {
		Page<Activity> page = new Page<Activity>();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        try {
            if(StringUtils.isNotEmpty(activityOrg)) {
                page.addParam("activityOrg", URLDecoder.decode(activityOrg, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(activityTitle)) {
                page.addParam("activityTitle", URLDecoder.decode(activityTitle, "UTF-8"));
            }
            page.addParam("parentCategory", parentCategory);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            if(StringUtils.isNotEmpty(beginTime)) {
                Date beginDate =sdf.parse(beginTime);  
                page.addParam("beginTime", beginDate);
            }
            if(StringUtils.isNotEmpty(endTime)) {
                Date endDate =sdf.parse(endTime); 
                page.addParam("endTime", endDate);
            }
            if(null != recommendFlag) {
                page.addParam("recommendFlag", recommendFlag);
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        model.addAttribute("curDate", new Date());
		model.addAttribute("page", activityService.queryPage(page));
		return "activity/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "activity/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model,Activity activity, String applyStartTime, String applyEndTime, String actStartTime, String actEndTime){
	    formatTime(activity, applyStartTime, applyEndTime, actStartTime, actEndTime);
	    activityService.save(activity);
		return list(model, 1,null, null,null,null,null,null,null);
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activity", activityService.get(id));
		return "activity/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
    public String goUpdate(Model model, Long activityId) {
	    Activity activity = activityService.get(activityId);
	    model.addAttribute("activity", activity);
        return "activity/update";
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Activity activity,Model model, String applyStartTime, String applyEndTime, String actStartTime, String actEndTime) {
	    formatTime(activity, applyStartTime, applyEndTime, actStartTime, actEndTime);
		activityService.update(activity);
		return list(model, 1, null, null,null, null,null,null,null);
	}
	
	@RequestMapping(value = "{activityId}/delete")
    public String delete(Model model, @PathVariable("activityId") Long activityId) {
        Activity activity = activityService.get(activityId);
        if(null != activity) {
            activityService.delete(activity); 
        }
        return list(model, 1, null,null, null, null,null,null,null);
    }
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(Model model, Long activityId, Integer recommendFlag, Integer orders ) {
        Activity activity = activityService.get(activityId);
        if(activity != null) {
            if(null != recommendFlag) {
                activity.setRecommendFlag(recommendFlag);
            }
            activity.setOrders(orders);
            activityService.update(activity);
        }
        return list(model, 1, null, null,null, null,null,null,null);
    }
	
	@RequestMapping(value = "/batchRecommend")
    public String batchRecommend(Model model, Long[] activityIds) {
        if(null != activityIds && activityIds.length > 0) {
            for(Long activityId : activityIds) {
                Activity activity = activityService.get(activityId); 
                if(null != activity && null != activity.getRecommendFlag() && activity.getRecommendFlag() != 1) {
                    activity.setRecommendFlag(1);
                    activity.setOrders(0);
                    activityService.update(activity);
                }
            }
        }
        return list(model, 1, null, null,null, null,null,null,null);
    }
	
	private void formatTime(Activity activity, String applyStartTime, String applyEndTime, String actStartTime, String actEndTime) {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    
	    try {
	        Date startApplyDate = null;
	        Date endApplyDate = null;
	        Date actStartDate = null;
	        Date actEndDate = null;
    	    if(StringUtils.isNotEmpty(applyStartTime)) {
    	        applyStartTime = applyStartTime.replace("T", " ");
    	        startApplyDate = sdf.parse(applyStartTime);
    	    }
            if(StringUtils.isNotEmpty(applyEndTime)) {
                applyEndTime = applyEndTime.replace("T", " "); 
                endApplyDate = sdf.parse(applyEndTime);
            }
            if(StringUtils.isNotEmpty(actStartTime)) {
                actStartTime = actStartTime.replace("T", " ");
                actStartDate = sdf.parse(actStartTime);
            }
            if(StringUtils.isNotEmpty(actEndTime)) {
                actEndTime = actEndTime.replace("T", " "); 
                actEndDate = sdf.parse(actEndTime);
            }
        
            activity.setStartApplyTime(startApplyDate);
            activity.setEndApplyTime(endApplyDate);
            activity.setBeginTime(actStartDate);
            activity.setEndTime(actEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
}
