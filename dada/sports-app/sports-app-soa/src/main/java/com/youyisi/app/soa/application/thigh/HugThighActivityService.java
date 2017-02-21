package com.youyisi.app.soa.application.thigh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.thigh.HugThighActivityRepository;
import com.youyisi.app.soa.remote.thigh.HugThighActivityServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.thigh.HugThighActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
@Service
public class HugThighActivityService implements HugThighActivityServiceRemote {

	@Autowired
	private HugThighActivityRepository repository;

	@Override
	public HugThighActivity get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public HugThighActivity save(HugThighActivity entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(HugThighActivity entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(HugThighActivity entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<HugThighActivity> queryPage(Page<HugThighActivity> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public HugThighActivity getByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return repository.getByActivityId(activityId);
	}
}

