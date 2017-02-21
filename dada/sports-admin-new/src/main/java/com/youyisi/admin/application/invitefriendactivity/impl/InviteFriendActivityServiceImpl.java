package com.youyisi.admin.application.invitefriendactivity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.invitefriendactivity.InviteFriendActivityService;
import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivity;
import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivityRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-11
 */
@Service
public class InviteFriendActivityServiceImpl implements InviteFriendActivityService {

	@Autowired
	private InviteFriendActivityRepository repository;

	@Override
	public InviteFriendActivity get(Long id) {
		return repository.get(id);
	}

	@Override
	public InviteFriendActivity save(InviteFriendActivity entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(InviteFriendActivity entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(InviteFriendActivity entity) {
		return repository.update(entity);
	}

	@Override
	public Page<InviteFriendActivity> queryPage(Page<InviteFriendActivity> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer delByActivityId(Long activityId) {

		return repository.delByActivityId(activityId);
	}

	@Override
	public InviteFriendActivity getByActivityId(Long activityId) {

		return repository.getByActivityId(activityId);
	}

}
