package com.youyisi.admin.domain.activity.activityapply;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;


/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public interface ActivityApplyRepository extends MybatisRepository<Long, ActivityApply> {
    
    /**
     * 
     * @param userName
     * @return
     */
    ActivityApply getByUserIdAndActivityId(Long userId, Long activityId);

	List<ActivityApply> queryAll(Long activityId);
}

