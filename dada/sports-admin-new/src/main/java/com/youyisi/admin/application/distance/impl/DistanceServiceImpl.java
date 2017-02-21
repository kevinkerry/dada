package com.youyisi.admin.application.distance.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.distance.DistanceService;
import com.youyisi.admin.domain.distance.Distance;
import com.youyisi.admin.domain.distance.DistanceRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-02
 */
@Service
public class DistanceServiceImpl implements DistanceService {

	@Autowired
	private DistanceRepository repository;

	@Override
	public Distance get(Long id) {
		return repository.get(id);
	}

	@Override
	public Distance save(Distance entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Distance entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Distance entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Distance> queryPage(Page<Distance> page) {
		return repository.queryPage(page);
	}

	@Override
	public Double countDistance(Long beginTime, Long endTime, Integer clientType, Integer result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (beginTime != null && endTime != null) {
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
		}
		if (clientType != null) {
			map.put("clientType", clientType);
		}
		if (result != null) {
			map.put("result", result);
		}
		return repository.countDistance(map);
	}

	@Override
	public Double countDistanceByUserId(Long userId, Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (date != null) {
			map.put("date", date);
		}

		return repository.countDistanceByUserId(map);
	}

	@Override
	public Integer countDistanceNum(Long userId, Integer beginDistance, Integer endDistance) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (beginDistance != null) {
			map.put("beginDistance", beginDistance);
		}
		if (endDistance != null) {
			map.put("endDistance", endDistance);
		}
		return repository.countDistanceNum(map);
	}
}
