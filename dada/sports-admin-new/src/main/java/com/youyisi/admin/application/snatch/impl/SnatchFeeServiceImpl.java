package com.youyisi.admin.application.snatch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.snatch.SnatchFeeService;
import com.youyisi.admin.domain.snatch.SnatchFee;
import com.youyisi.admin.domain.snatch.SnatchFeeRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Service
public class SnatchFeeServiceImpl implements SnatchFeeService {

	@Autowired
	private SnatchFeeRepository repository;

	@Override
	public SnatchFee get(Long id) {
		return repository.get(id);
	}

	@Override
	public SnatchFee save(SnatchFee entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(SnatchFee entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SnatchFee entity) {
		return repository.update(entity);
	}

	@Override
	public Page<SnatchFee> queryPage(Page<SnatchFee> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer delByActivityId(Long activityId) {

		return repository.delByActivityId(activityId);
	}

	@Override
	public List<SnatchFee> getSnatchFeeByActivityId(Long activityId) {

		return repository.getSnatchFeeByActivityId(activityId);
	}
}
