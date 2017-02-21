package com.youyisi.admin.application.activity.activityimage;

import com.youyisi.admin.domain.activity.activityimage.ActivityImage;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 7, 2015
 */
public interface ActivityImageService {
    
    public ActivityImage save(ActivityImage activityImage);
    
    public ActivityImage get(Long imageId);
    
    public Integer delete(ActivityImage activityImage);
    
    public Integer update(ActivityImage activityImage);
    
    public Page<ActivityImage> queryPage(Page<ActivityImage> page);
}

