package com.youyisi.app.soa.application.relay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.activity.ActivityRepository;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayTeamRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.remote.relay.RelayTeamServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.relay.RelayTeamWithMore;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Service
public class RelayTeamService implements RelayTeamServiceRemote {

	@Autowired
	private RelayTeamRepository repository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;
	
	@Autowired
	private RelayMemberService relayMemberService;
	@Override
	public RelayTeam get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RelayTeam save(RelayTeam entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayTeam entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayTeam entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RelayTeam> queryPage(Page<RelayTeam> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public RelayTeam getByActivityIdAndUserId(Long activityId, Long userId)
			throws SoaException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.getByActivityIdAndUserId(map);
	}

	@Override
	public List<RelayTeamWithMore> getList(Long activityId,User user,Integer end) throws SoaException {
		List<RelayTeamWithMore> list = repository.getList(activityId);
		List<RelayTeamWithMore> result = new ArrayList<RelayTeamWithMore>();
		List<RelayTeamWithMore> other = new ArrayList<RelayTeamWithMore>();
		RelayMember relayMember = relayMemberService.getByActivityIdAndUserId(activityId,user.getId());
		ActivityWithRelayRaceActivity activity = activityRepository.getActivityWithRelayRaceActivityById(activityId);
		for(RelayTeamWithMore r : list){
			r.setDistance(getSumDistance(r,activity));
			if(relayMember!=null&&r.getId().longValue()==relayMember.getTeamId().longValue()){
				result.add(r);
			}else{
				other.add(r);
			}
		}
		result.addAll(other);
		if(end!=null&&end.intValue()==1){
			Collections.sort(result);
		}
		return result;
	}

	private Double getSumDistance(RelayTeamWithMore r,ActivityWithRelayRaceActivity activity) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("teamId",r.getId());
		map.put("maxDistance",activity.getRelayRaceActivity().getMaxDistance());
		map.put("maxStep",activity.getRelayRaceActivity().getMaxStep());
		Double distance = distanceRepository.getTeamDistance(map);
		Integer step = stepRepository.getTeamStep(map);
		if(step==null){
			step = 0;
		}
		Double _step = step*1.0/activity.getRelayRaceActivity().getStepToDistance();
		if(distance==null){
			distance=0.0;
		}
		return ArithHelper.add(distance, _step);
	}

	@Override
	public Integer getCountByActivityId(Long activityId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getCountByActivityId(activityId);
	}
}

