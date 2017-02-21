package com.youyisi.admin.application.run.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.run.RunAssistService;
import com.youyisi.admin.domain.run.RunAssist;
import com.youyisi.admin.domain.run.RunAssistRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-25
 */
@Service
public class RunAssistServiceImpl implements RunAssistService {

	@Autowired
	private RunAssistRepository repository;

	@Override
	public RunAssist get(Long id) {
		return repository.get(id);
	}

	@Override
	public RunAssist save(RunAssist entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RunAssist entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RunAssist entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RunAssist> queryPage(Page<RunAssist> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<RunAssist> getRunAssistByRunId(Long runId) {

		return repository.getRunAssistByRunId(runId);
	}
}
