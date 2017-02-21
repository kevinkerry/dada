package com.youyisi.app.soa.application.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.version.AppVersionRepository;
import com.youyisi.app.soa.remote.version.AppVersionServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.version.AppVersion;

/**
 * @author shuye
 * @time 2016-08-30
 */
@Service
public class AppVersionService implements AppVersionServiceRemote {

	@Autowired
	private AppVersionRepository repository;

	@Override
	public AppVersion get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public AppVersion save(AppVersion entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(AppVersion entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(AppVersion entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<AppVersion> queryPage(Page<AppVersion> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public AppVersion getByChannel(String channel) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByChannel(channel);
	}
}

