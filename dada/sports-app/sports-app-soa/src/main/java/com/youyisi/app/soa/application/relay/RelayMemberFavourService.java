package com.youyisi.app.soa.application.relay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayMemberFavourRepository;
import com.youyisi.app.soa.remote.relay.RelayMemberFavourServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.relay.RelayMemberFavour;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Service
public class RelayMemberFavourService implements RelayMemberFavourServiceRemote {

	@Autowired
	private RelayMemberFavourRepository repository;

	@Override
	public RelayMemberFavour get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RelayMemberFavour save(RelayMemberFavour entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayMemberFavour entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayMemberFavour entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RelayMemberFavour> queryPage(Page<RelayMemberFavour> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public RelayMemberFavour getByMap(Map<String, Object> map)
			throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByMap(map);
	}
}

