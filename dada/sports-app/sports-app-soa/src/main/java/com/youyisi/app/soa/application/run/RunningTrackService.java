package com.youyisi.app.soa.application.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.annual.AnnualYieldService;
import com.youyisi.app.soa.application.distance.DistanceService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.run.RunningTrackRepository;
import com.youyisi.app.soa.remote.run.RunningTrackServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.run.RunningTrack;
import com.youyisi.sports.domain.user.User;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class RunningTrackService implements RunningTrackServiceRemote {

	@Autowired
	private RunningTrackRepository repository;
	@Autowired
	private DistanceService distanceService;
	@Autowired
	private AnnualYieldService annualYieldService;

	@Override
	public RunningTrack get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RunningTrack save(RunningTrack entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RunningTrack entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RunningTrack entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RunningTrack> queryPage(Page<RunningTrack> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<RunningTrack> add(List<RunningTrack> fromJson,User u) throws SoaException {
		List<RunningTrack> list = new ArrayList<RunningTrack>();
		for(RunningTrack r:fromJson){
			r.setUserId(u.getId());
			r.setCreateTime(System.currentTimeMillis());
			r = repository.save(r);
			list.add(r);
		}
		return list;
	}

	public void deleteByRunId(Long runId) {
		// TODO Auto-generated method stub
		repository.deleteByRunId(runId);
	}
}

