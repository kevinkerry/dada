package com.youyisi.app.soa.application.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.remote.distance.DistanceServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.distance.Distance;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class DistanceService implements DistanceServiceRemote {

	@Autowired
	private DistanceRepository repository;

	@Override
	public Distance get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Distance save(Distance entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Distance entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Distance entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Distance> queryPage(Page<Distance> page) throws SoaException{
		return repository.queryPage(page);
	}
	
	@Override
	public Distance getByUserIdAndDate(Long userId) throws SoaException{
		return repository.getByUserIdAndDate(userId);
	}

	@Override
	public Double getSumDistance(Long userId) {
		// TODO Auto-generated method stub
		return repository.getSumDistance(userId);
	}
}

