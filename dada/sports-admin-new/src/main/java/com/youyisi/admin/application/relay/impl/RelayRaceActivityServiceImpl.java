package com.youyisi.admin.application.relay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.relay.RelayRaceActivityService;
import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.admin.domain.relay.RelayRaceActivityRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
@Service
public class RelayRaceActivityServiceImpl implements RelayRaceActivityService {

	@Autowired
	private RelayRaceActivityRepository repository;

	@Override
	public RelayRaceActivity get(Long id) {
		return repository.get(id);
	}

	@Override
	public RelayRaceActivity save(RelayRaceActivity entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RelayRaceActivity entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayRaceActivity entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RelayRaceActivity> queryPage(Page<RelayRaceActivity> page) {
		return repository.queryPage(page);
	}

	@Override
	public RelayRaceActivity getByActivityId(Long activityId) {

		return repository.getByActivityId(activityId);
	}

	@Override
	public Integer delByActivityId(Long activityId) {

		return repository.delByActivityId(activityId);
	}
}
