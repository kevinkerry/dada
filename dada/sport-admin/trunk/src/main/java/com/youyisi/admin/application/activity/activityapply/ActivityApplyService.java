package com.youyisi.admin.application.activity.activityapply;

import java.util.List;

import com.youyisi.admin.domain.activity.activityapply.ActivityApply;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public interface ActivityApplyService {
    
    public ActivityApply save(ActivityApply activityapply);
    
    ActivityApply get(Long id);
    
    Integer delete(ActivityApply entity);
    
    Integer update(ActivityApply entity);
    
    Page<ActivityApply> queryPage(Page<ActivityApply> page);
    
    /**
     * 
     * @param userName
     * @return
     */
    ActivityApply getByUserIdAndActivityId(Long userId, Long activityId);

	List<ActivityApply> queryAll(Long activityId);
}

