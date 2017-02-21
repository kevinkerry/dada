package com.youyisi.app.soa.application.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.activity.InviteFriendActivityServiceRemote;
import com.youyisi.sports.domain.activity.InviteFriendActivity;
import com.youyisi.app.soa.infrastructure.persist.activity.InviteFriendActivityRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-09
 */
@Service
public class InviteFriendActivityService implements InviteFriendActivityServiceRemote {

	@Autowired
	private InviteFriendActivityRepository repository;

	@Override
	public InviteFriendActivity get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public InviteFriendActivity save(InviteFriendActivity entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(InviteFriendActivity entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(InviteFriendActivity entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<InviteFriendActivity> queryPage(Page<InviteFriendActivity> page) throws SoaException{
		return repository.queryPage(page);
	}
}

