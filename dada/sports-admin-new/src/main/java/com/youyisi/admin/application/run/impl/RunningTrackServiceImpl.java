package com.youyisi.admin.application.run.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.run.RunningTrackService;
import com.youyisi.admin.domain.run.RunningTrack;
import com.youyisi.admin.domain.run.RunningTrackRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-27
 */
@Service
public class RunningTrackServiceImpl implements RunningTrackService {

	@Autowired
	private RunningTrackRepository repository;

	@Override
	public RunningTrack get(Long id) {
		return repository.get(id);
	}

	@Override
	public RunningTrack save(RunningTrack entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RunningTrack entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RunningTrack entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RunningTrack> queryPage(Page<RunningTrack> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<RunningTrack> getRunningTrackByRunId(Long runId) {

		return repository.getRunningTrackByRunId(runId);
	}
}
