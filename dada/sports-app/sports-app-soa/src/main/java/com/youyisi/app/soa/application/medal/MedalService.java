package com.youyisi.app.soa.application.medal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.medal.MedalRepository;
import com.youyisi.app.soa.remote.medal.MedalServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.medal.Medal;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Service
public class MedalService implements MedalServiceRemote {

	@Autowired
	private MedalRepository repository;

	@Override
	public Medal get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Medal save(Medal entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Medal entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Medal entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Medal> queryPage(Page<Medal> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<Medal> getNoHaveByUserId(Long userId) {
		// TODO Auto-generated method stub
		return repository.getNoHaveByUserId(userId);
	}
}

