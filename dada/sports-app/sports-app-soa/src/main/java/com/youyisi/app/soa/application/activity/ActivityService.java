package com.youyisi.app.soa.application.activity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.activity.ActivityRepository;
import com.youyisi.app.soa.remote.activity.ActivityServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.activity.ActivityWithHugThighActivity;
import com.youyisi.sports.domain.activity.ActivityWithInviteFriendActivity;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;

/**
 * @author shuye
 * @time 2016-07-13
 */
@Service
public class ActivityService implements ActivityServiceRemote {

	@Autowired
	private ActivityRepository repository;

	@Override
	public Activity get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Activity save(Activity entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Activity entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Activity entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Activity> queryPage(Page<Activity> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public ActivityWithHugThighActivity getActivityWithHugThighActivityById(Long id) throws SoaException {
		updateHot(id);
		return repository.getActivityWithHugThighActivityById(id);
	}

	@Override
	public ActivityWithInviteFriendActivity getActivityWithInviteFriendActivityById(
			Long id) throws SoaException{
		updateHot(id);
		return repository.getActivityWithInviteFriendActivityById(id);
	}

	private void updateHot(Long id) {
		Activity activity = repository.get(id);
		if(activity.getEndTime()>System.currentTimeMillis()){
			if(activity.getHot()!=null){
				activity.setHot(activity.getHot()+1l);
			}else{
				activity.setHot(1l);
			}
			repository.update(activity);
		}
	}

	@Override
	public ActivityWithRelayRaceActivity getActivityWithRelayRaceActivityById(
			Long id) throws SoaException {
		updateHot(id);
		return repository.getActivityWithRelayRaceActivityById(id);
	}

	@Override
	public ActivityWithSnatchActivity getActivityWithSnatchActivityById(Long id) {
		updateHot(id);
		return repository.getActivityWithSnatchActivityById(id);
	}

	@Override
	public ActivityWithSnatchActivity getLatest(long time, int type) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("time", time);
		return repository.getLatest(map);
	}
}

