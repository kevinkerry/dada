package com.youyisi.app.soa.application.relay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayLineRepository;
import com.youyisi.app.soa.remote.relay.RelayLineServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.relay.RelayLine;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Service
public class RelayLineService implements RelayLineServiceRemote {

	@Autowired
	private RelayLineRepository repository;

	@Override
	public RelayLine get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RelayLine save(RelayLine entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayLine entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayLine entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RelayLine> queryPage(Page<RelayLine> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<RelayLine> getByActivityId(Long activityId) {
		// TODO Auto-generated method stub
		return repository.getByActivityId(activityId);
	}
}

