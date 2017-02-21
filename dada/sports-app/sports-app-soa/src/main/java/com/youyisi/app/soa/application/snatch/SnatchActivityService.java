package com.youyisi.app.soa.application.snatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.activity.ActivityRepository;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchActivityRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.remote.snatch.SnatchActivityServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchActivityWithSnatchWinWithMore;
import com.youyisi.sports.domain.snatch.SnatchWinWithMore;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Service
public class SnatchActivityService implements SnatchActivityServiceRemote {

	@Autowired
	private SnatchActivityRepository repository;
	@Autowired
	private SnatchWinService snatchWinService;
	
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;
	@Override
	public SnatchActivity get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public SnatchActivity save(SnatchActivity entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(SnatchActivity entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(SnatchActivity entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<SnatchActivity> queryPage(Page<SnatchActivity> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Page<SnatchActivityWithSnatchWinWithMore> queryPageForHistory(
			Page<SnatchActivityWithSnatchWinWithMore> page) throws SoaException {
		page = repository.queryPageForHistory(page);
		List<SnatchActivityWithSnatchWinWithMore> list = new ArrayList<SnatchActivityWithSnatchWinWithMore>();
		for(SnatchActivityWithSnatchWinWithMore a : page.getResult()){
			List<SnatchWinWithMore> snatchWinWithLess = new ArrayList<SnatchWinWithMore>();
			for(SnatchWinWithMore s :a.getSnatchWinWithMore()){
				s.setDistance(getUserDistance(a, s.getUserId()));
				snatchWinWithLess.add(s);
			}
			
			list.add(a);
		}
		page.setResult(list);
		
		return page;
	}
	public Double getUserDistance(SnatchActivityWithSnatchWinWithMore s, Long userId) {
		Activity activity = activityRepository.get(s.getActivityId());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId",userId);
		map.put("maxDistance",s.getMaxDistance());
		map.put("maxStep",s.getMaxStep());
		Double distance = distanceRepository.getUserDistance(map);
		if(distance==null){
			distance=0.0;
		}
		Integer step = stepRepository.getUserStep(map);
		if(step==null){
			step = 0;
		}
		Double _step = step*1.0/s.getStepToDistance();
		return ArithHelper.add(distance, _step);
	}

	@Override
	public Double getUserDistance(ActivityWithSnatchActivity activity,
			Long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId",userId);
		map.put("maxDistance",activity.getSnatchActivity().getMaxDistance());
		map.put("maxStep",activity.getSnatchActivity().getMaxStep());
		Double distance = distanceRepository.getUserDistance(map);
		if(distance==null){
			distance=0.0;
		}
		Integer step = stepRepository.getUserStep(map);
		if(step==null){
			step = 0;
		}
		Double _step = step*1.0/activity.getSnatchActivity().getStepToDistance();
		return ArithHelper.add(distance, _step);
	}
}

