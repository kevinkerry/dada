package com.youyisi.app.soa.application.run;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.annual.AnnualYieldService;
import com.youyisi.app.soa.application.distance.DistanceService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.run.RunRepository;
import com.youyisi.app.soa.remote.run.RunServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.run.RunWithTrack;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class RunService implements RunServiceRemote {

	@Autowired
	private RunRepository repository;
	@Autowired
	private DistanceService distanceService;
	@Autowired
	private RunningTrackService runningTrackService;
	@Autowired
	private AnnualYieldService annualYieldService;
	@Override
	public Run get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Run save(Run entity) throws SoaException{
		entity = repository.save(entity);
		if(entity.getType()==1){
			Distance d = distanceService.getByUserIdAndDate(entity.getUserId());
			if(d==null){
				d = new Distance();
				d.setCreateTime(System.currentTimeMillis());
				d.setDistance(entity.getDistance());
				d.setDate(DateUtil.currentDateForDay());
				d.setUserId(entity.getUserId());
				d.setUpdateTime(System.currentTimeMillis());
				distanceService.save(d);
			}else{
				d.setDistance(ArithHelper.add(entity.getDistance(), d.getDistance()));
				d.setUpdateTime(System.currentTimeMillis());
				distanceService.update(d);
			}
			if(entity.getDistance()>=3.0){
				annualYieldService.updateByRun(entity);
			}
		}
		return entity;
}

	@Override
	public Integer delete(Run entity) throws SoaException{
		runningTrackService.deleteByRunId(entity.getId());
		return repository.delete(entity);
	}

	@Override
	public Integer update(Run entity) throws SoaException{
		
		Run r = repository.get(entity.getId());
		if(r.getDate().longValue()==DateUtil.currentDateForDay().longValue()){
			Distance d = distanceService.getByUserIdAndDate(entity.getUserId());
			if(entity.getStatus().intValue()==0){
				if(d==null){
					d = new Distance();
					d.setCreateTime(System.currentTimeMillis());
					d.setDistance(entity.getDistance());
					d.setDate(DateUtil.currentDateForDay());
					d.setUserId(entity.getUserId());
					d.setUpdateTime(System.currentTimeMillis());
					distanceService.save(d);
				}else{
					if(r.getDistance()==null||r.getDistance()==0.0){
						d.setDistance(ArithHelper.add(entity.getDistance(), d.getDistance()));
						d.setUpdateTime(System.currentTimeMillis());
						distanceService.update(d);
					}
				}
			}
			
			if(entity.getDistance()>=3.0&&entity.getStatus().intValue()==0){
				annualYieldService.updateByRun(entity);
			}
			return repository.update(entity);
		}else{
			return repository.update(entity);
		}
		
	}
	@Override
	public Page<Run> queryPage(Page<Run> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public RunWithTrack getDetail(Long id) throws SoaException {
		return repository.getDetail(id);
	}

	@Override
	public Run correction(Run r) {
	 repository.update(r);
	 return r;
	}

	@Override
	public Run getMaxRun(Long userId, Long date, Double avspeed,
			Double maxspeed) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("date", date);
		map.put("avspeed", avspeed);
		map.put("maxspeed", maxspeed);
		return repository.getMaxRun(map);
	}
}

