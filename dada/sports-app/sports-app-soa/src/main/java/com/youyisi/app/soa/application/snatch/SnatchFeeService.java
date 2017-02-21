package com.youyisi.app.soa.application.snatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchFeeRepository;
import com.youyisi.app.soa.remote.snatch.SnatchFeeServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.snatch.SnatchFee;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Service
public class SnatchFeeService implements SnatchFeeServiceRemote {

	@Autowired
	private SnatchFeeRepository repository;

	@Override
	public SnatchFee get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public SnatchFee save(SnatchFee entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(SnatchFee entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(SnatchFee entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<SnatchFee> queryPage(Page<SnatchFee> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<SnatchFee> getByActivityId(Long activityId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByActivityId(activityId);
	}
}

