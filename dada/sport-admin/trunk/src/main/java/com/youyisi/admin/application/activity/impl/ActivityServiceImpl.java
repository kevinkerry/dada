package com.youyisi.admin.application.activity.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.ActivityRepository;
import com.youyisi.admin.domain.activity.activityimage.ActivityImage;
import com.youyisi.admin.domain.activity.activityimage.ActivityImageRepository;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 7, 2015
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Autowired
    private ActivityRepository repository;
    
    @Autowired
    private ActivityImageRepository imageRepository;
    @Autowired
    private ActiveMqSender activeMqSender;
    
    @Override
    public Activity get(Long activityId) {
        return repository.get(activityId);
    }
    @Override
    public Activity save(Activity activity) {
        Long currentUserId = -1000l;
        Date curDate = new Date();
        activity.setCreator(currentUserId);
        activity.setModifier(currentUserId);
        activity.setCreatedTime(curDate);
        activity.setUpdatedTime(curDate);
        activity.setActivityState(1);
        activity.setActivityType(1);
        activity.setStatus("A");
        activity.setRecommendFlag(0);
        
        List<ActivityImage> activityImages = activity.getActivityImages();
        Activity a = repository.save(activity);
        if(null != activityImages && activityImages.size() > 0) {
            for(ActivityImage activityImage : activityImages){
                activityImage.setCreator(currentUserId);
                activityImage.setModifier(currentUserId);
                activityImage.setCreatedTime(curDate);
                activityImage.setUpdatedTime(curDate);
                activityImage.setActivityId(activity.getActivityId());
                activityImage.setStatus("A");
                imageRepository.save(activityImage);
            }
        }
        activeMqSender.send(a.getActivityId().toString(),null);
        return a;
    }
    @Override
    public Integer delete(Activity activity) {
        //只做逻辑删除，修改状态
        activity.setStatus("I");
        /*List<ActivityImage> activityImages = activity.getActivityImages();
        if(null != activityImages && activityImages.size() > 0) {
            for(ActivityImage activityImage : activityImages){
                imageRepository.delete(activityImage);
            }
        }*/
        Integer count = repository.update(activity);
        activeMqSender.send(activity.getActivityId().toString(),null);
        return count;
    }
    @Override
    public Integer update(Activity activity) {
        Long currentUserId = -1000l;
        Activity existActivity =  repository.get(activity.getActivityId());
        Date curDate = new Date();
        activity.setActivityState(existActivity.getActivityState());
        activity.setActivityType(existActivity.getActivityType());
        activity.setStatus(existActivity.getStatus());
        activity.setUpdatedTime(curDate);
        activity.setCreator(existActivity.getCreator());
        activity.setModifier(currentUserId);
        activity.setCreatedTime(existActivity.getCreatedTime());
        
        List<ActivityImage> activityImages = activity.getActivityImages();
        if(null != activityImages && activityImages.size() > 0) {
            for(ActivityImage activityImage : activityImages){
                if(null == activityImage.getImgId()) {
                    activityImage.setCreator(currentUserId);
                    activityImage.setModifier(currentUserId);
                    activityImage.setCreatedTime(curDate);
                    activityImage.setUpdatedTime(curDate);
                    activityImage.setActivityId(activity.getActivityId());
                    activityImage.setStatus("A");
                }else {
                    ActivityImage existedActivityImage = imageRepository.get(activityImage.getImgId());
                    activityImage.setUpdatedTime(curDate);
                    activityImage.setActivityId(activity.getActivityId());
                    activityImage.setCreator(existedActivityImage.getCreator());
                    activityImage.setModifier(currentUserId);
                    activityImage.setCreatedTime(existedActivityImage.getCreatedTime());
                    activityImage.setUpdatedTime(curDate);
                    activityImage.setStatus(existedActivityImage.getStatus());
                }
                imageRepository.save(activityImage);
            }
        }
        
        Integer count =  repository.update(activity);
        activeMqSender.send(activity.getActivityId().toString(),null);
        return count;
    }
    @Override
    public Page<Activity> queryPage(Page<Activity> page) {
        return repository.queryPage(page);
    }
}

