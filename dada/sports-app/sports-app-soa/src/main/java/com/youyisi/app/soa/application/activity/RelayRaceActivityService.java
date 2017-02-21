package com.youyisi.app.soa.application.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.activity.RelayRaceActivityRepository;
import com.youyisi.app.soa.remote.activity.RelayRaceActivityServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.RelayRaceActivity;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Service
public class RelayRaceActivityService implements RelayRaceActivityServiceRemote {

	@Autowired
	private RelayRaceActivityRepository repository;

	@Override
	public RelayRaceActivity get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RelayRaceActivity save(RelayRaceActivity entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayRaceActivity entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayRaceActivity entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RelayRaceActivity> queryPage(Page<RelayRaceActivity> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public RelayRaceActivity getByActivityId(Long activityId)
			throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByActivityId(activityId);
	}
}

