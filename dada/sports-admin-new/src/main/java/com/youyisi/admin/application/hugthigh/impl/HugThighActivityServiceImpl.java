package com.youyisi.admin.application.hugthigh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.hugthigh.HugThighActivityService;
import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.admin.domain.hugthigh.HugThighActivityRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Service
public class HugThighActivityServiceImpl implements HugThighActivityService {

	@Autowired
	private HugThighActivityRepository repository;

	@Override
	public HugThighActivity get(Long id) {
		return repository.get(id);
	}

	@Override
	public HugThighActivity save(HugThighActivity entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(HugThighActivity entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(HugThighActivity entity) {
		return repository.update(entity);
	}

	@Override
	public Page<HugThighActivity> queryPage(Page<HugThighActivity> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer delByActivityId(Long activityId) {
		return repository.delByActivityId(activityId);
	}

	@Override
	public HugThighActivity getByActivityId(Long activityId) {

		return repository.getByActivityId(activityId);
	}
}
