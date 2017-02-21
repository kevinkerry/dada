package com.youyisi.admin.application.snatch.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.snatch.SnatchWinService;
import com.youyisi.admin.domain.snatch.SnatchWin;
import com.youyisi.admin.domain.snatch.SnatchWinRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Service
public class SnatchWinServiceImpl implements SnatchWinService {

	@Autowired
	private SnatchWinRepository repository;

	@Override
	public SnatchWin get(Long id) {
		return repository.get(id);
	}

	@Override
	public SnatchWin save(SnatchWin entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(SnatchWin entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SnatchWin entity) {
		return repository.update(entity);
	}

	@Override
	public Page<SnatchWin> queryPage(Page<SnatchWin> page) {
		return repository.queryPage(page);
	}

	@Override
	public Double getSnatchWinByActivityIdAndUserId(Long activityId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.getSnatchWinByActivityIdAndUserId(map);
	}
}
