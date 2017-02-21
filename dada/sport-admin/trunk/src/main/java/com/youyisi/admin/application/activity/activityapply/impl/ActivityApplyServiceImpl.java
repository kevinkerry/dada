package com.youyisi.admin.application.activity.activityapply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.activityapply.ActivityApplyService;
import com.youyisi.admin.domain.activity.activityapply.ActivityApply;
import com.youyisi.admin.domain.activity.activityapply.ActivityApplyRepository;
import com.youyisi.admin.domain.member.MemberRepository;
import com.youyisi.admin.domain.sportuser.SportUserRepository;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {
    
    @Autowired
    private ActivityApplyRepository repository;
    
    @Autowired
    private SportUserRepository sportUserRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private ActiveMqSender activeMqSender;
    
    @Override
    public ActivityApply get(Long id) {
        return repository.get(id);
    }
    
    @Override
    public ActivityApply save(ActivityApply activityapply) {  
        return repository.save(activityapply);
    }
    
    @Override
    public Integer delete(ActivityApply entity) {
        return repository.delete(entity);
    }
    
    @Override
    public Integer update(ActivityApply entity) {
        return repository.update(entity);
    }
    
    @Override
    public Page<ActivityApply> queryPage(Page<ActivityApply> page) {
        return repository.queryPage(page);
    }

    @Override
    public ActivityApply getByUserIdAndActivityId(Long userId, Long activityId) {
        return repository.getByUserIdAndActivityId(userId, activityId);
    }

	@Override
	public List<ActivityApply> queryAll(Long activityId) {
		// TODO Auto-generated method stub
		return repository.queryAll(activityId);
	}
}

