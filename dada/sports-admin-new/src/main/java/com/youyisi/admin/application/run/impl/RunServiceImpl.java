package com.youyisi.admin.application.run.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.hugthigh.impl.HugThighActivityServiceImpl;
import com.youyisi.admin.application.run.RunService;
import com.youyisi.admin.application.step.impl.StepServiceImpl;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.admin.domain.run.Run;
import com.youyisi.admin.domain.run.RunRepository;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-27
 */
@Service
public class RunServiceImpl implements RunService {

	@Autowired
	private RunRepository repository;

	@Autowired
	private HugThighActivityServiceImpl hugThighActivityServiceImpl;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Autowired
	private StepServiceImpl stepServiceImpl;

	@Override
	public Run get(Long id) {
		return repository.get(id);
	}

	@Override
	public Run save(Run entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Run entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Run entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Run> queryPage(Page<Run> page) {
		return repository.queryPage(page);
	}

	@Override
	public Page<Run> queryPageByUserId(Page<Run> page) {
		Page<Run> queryPage = repository.queryPage(page);
		List<Run> result = queryPage.getResult();
		Integer countStep;
		for (Run run : result) {
			countStep = stepServiceImpl.countStepByUserId(run.getUserId(), run.getDate());
			if (countStep == null) {
				countStep = 0;
			}
			run.setStep(countStep);
		}
		return queryPage;
	}

	@Override
	public Integer countRunByTotalTime(Long userId, Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (date != null) {
			map.put("date", date);
		}
		return repository.countRunByTotalTime(map);
	}

	@Override
	public Integer countRunByType(Long userId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (type != null) {
			map.put("type", type);
		}
		return repository.countRunByType(map);
	}

	@Override
	public Run getMaxRun(Long userId, Long date, Double avspeed, Double maxspeed) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		map.put("avspeed", avspeed);
		map.put("maxspeed", maxspeed);
		return repository.getMaxRun(map);
	}

	@Override
	public Run getThighRun(Long userId, Long activityId) {
		Activity activity = activityServiceImpl.get(activityId);
		Run run = null;
		if (activity != null) {
			HugThighActivity byActivityId = hugThighActivityServiceImpl.getByActivityId(activityId);
			if (byActivityId != null) {
				Long calculateDate = DateUtil.calculateDate(activity.getDate(), -1);
				run = getMaxRun(userId, calculateDate, byActivityId.getAvspeed(), byActivityId.getMaxspeed());
			}
		}
		return run;
	}

	@Override
	public Run sumDistance(Long userId, Integer type, Long beginTime, Long endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return repository.sumDistance(map);
	}
}
