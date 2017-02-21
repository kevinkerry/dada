package com.youyisi.app.soa.application.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.run.RunAssistServiceRemote;
import com.youyisi.sports.domain.run.RunAssist;
import com.youyisi.app.soa.infrastructure.persist.run.RunAssistRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-16
 */
@Service
public class RunAssistService implements RunAssistServiceRemote {

	@Autowired
	private RunAssistRepository repository;

	@Override
	public RunAssist get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RunAssist save(RunAssist entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RunAssist entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RunAssist entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RunAssist> queryPage(Page<RunAssist> page) throws SoaException{
		return repository.queryPage(page);
	}
}

