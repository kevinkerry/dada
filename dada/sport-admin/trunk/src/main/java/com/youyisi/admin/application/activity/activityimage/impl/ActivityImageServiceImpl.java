package com.youyisi.admin.application.activity.activityimage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.activity.activityimage.ActivityImageService;
import com.youyisi.admin.domain.activity.activityimage.ActivityImage;
import com.youyisi.admin.domain.activity.activityimage.ActivityImageRepository;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 7, 2015
 */
@Service
public class ActivityImageServiceImpl implements ActivityImageService {
    
    @Autowired
    private ActivityImageRepository repository;
    
    @Override
    public ActivityImage get(Long imgId) {
        return repository.get(imgId);
    }
    
    @Override
    public ActivityImage save(ActivityImage activityImage) {
        return repository.save(activityImage);
    }
    
    @Override
    public Integer delete(ActivityImage activityImage) {
        return repository.delete(activityImage);
    }
    
    @Override
    public Integer update(ActivityImage activityImage) {
        return repository.update(activityImage);
    }
    
    @Override
    public Page<ActivityImage> queryPage(Page<ActivityImage> page) {
        return repository.queryPage(page);
    }
}

