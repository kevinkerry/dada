package com.youyisi.admin.application.thigh.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.hugthigh.impl.HugThighServiceImpl;
import com.youyisi.admin.application.thigh.ThighService;
import com.youyisi.admin.domain.thigh.Thigh;
import com.youyisi.admin.domain.thigh.ThighRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Service
public class ThighServiceImpl implements ThighService {

	@Autowired
	private ThighRepository repository;

	@Autowired
	private HugThighServiceImpl hugThighServiceImpl;

	@Override
	public Thigh get(Long id) {
		return repository.get(id);
	}

	@Override
	public Thigh save(Thigh entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Thigh entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Thigh entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Thigh> queryPage(Page<Thigh> page) {
		Page<Thigh> queryPage = repository.queryPage(page);
		// List<Thigh> result = queryPage.getResult();
		// for (Thigh thigh : result) {
		//
		// }
		return queryPage;
	}

	@Override
	public Integer countThigh(Long beginTime, Long endTime, Integer clientType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("clientType", clientType);
		return repository.countThigh(map);
	}

	@Override
	public Integer countThighByActivityId(Long activityId, Integer status, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		if (status != null) {
			map.put("status", status);
		}
		if (type != null) {
			map.put("type", type);
		}
		return repository.countThighByActivityId(map);
	}

	@Override
	public Integer countThighByUserId(Long userId) {

		return repository.countThighByUserId(userId);
	}

	@Override
	public Page<Thigh> queryPageByUserId(Page<Thigh> page) {

		return repository.queryPageByUserId(page);
	}

	@Override
	public Integer countJoin(Long userId) {
		Integer countThighByUserId = countThighByUserId(userId);
		if (countThighByUserId == null) {
			countThighByUserId = 0;
		}
		Integer countHugThighByUserId = hugThighServiceImpl.countHugThighByUserId(userId);
		if (countHugThighByUserId == null) {
			countHugThighByUserId = 0;
		}
		return countThighByUserId + countHugThighByUserId;
	}
}
