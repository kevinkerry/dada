package com.youyisi.admin.application.relay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.relay.RelayLineService;
import com.youyisi.admin.domain.relay.RelayLine;
import com.youyisi.admin.domain.relay.RelayLineRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Service
public class RelayLineServiceImpl implements RelayLineService {

	@Autowired
	private RelayLineRepository repository;

	@Override
	public RelayLine get(Long id) {
		return repository.get(id);
	}

	@Override
	public RelayLine save(RelayLine entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RelayLine entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayLine entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RelayLine> queryPage(Page<RelayLine> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer delByActivityId(Long activityId) {

		return repository.delByActivityId(activityId);
	}

	@Override
	public List<RelayLine> getRelayLineList(Long activityId) {

		return repository.getRelayLineList(activityId);
	}
}
