package com.youyisi.admin.application.activity;

import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 7, 2015
 */
public interface ActivityService {
    
    Activity save(Activity activity);
    
    Activity get(Long activityId);
    
    Integer delete(Activity activity);
    
    Integer update(Activity activity);
    
    Page<Activity> queryPage(Page<Activity> page);
}

