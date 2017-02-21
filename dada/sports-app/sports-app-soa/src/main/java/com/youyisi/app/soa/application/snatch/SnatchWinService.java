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
import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchWinRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.remote.snatch.SnatchWinServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchWin;
import com.youyisi.sports.domain.snatch.SnatchWinWithMore;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Service
public class SnatchWinService implements SnatchWinServiceRemote {

	@Autowired
	private SnatchWinRepository repository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;
	@Override
	public SnatchWin get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public SnatchWin save(SnatchWin entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(SnatchWin entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(SnatchWin entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<SnatchWin> queryPage(Page<SnatchWin> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<SnatchWinWithMore> winResult(Long activityId) {
		List<SnatchWinWithMore> list = repository.winResult(activityId);
		List<SnatchWinWithMore> result = new ArrayList<SnatchWinWithMore>(); 
		ActivityWithSnatchActivity activityWithSnatchActivity = activityRepository.getActivityWithSnatchActivityById(activityId);
		for(SnatchWinWithMore s:list){
			s.setDistance(getUserDistance(activityWithSnatchActivity,s.getUserId()));
			result.add(s);
		}
		return result;
	}

	public Double getUserDistance(ActivityWithSnatchActivity activity, Long userId) {
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

	@Override
	public SnatchWin getByUserId(Long userId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByUserId(userId);
	}
}

