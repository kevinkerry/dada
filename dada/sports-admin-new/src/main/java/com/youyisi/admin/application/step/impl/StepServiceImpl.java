package com.youyisi.admin.application.step.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.annual.impl.AnnualYieldServiceImpl;
import com.youyisi.admin.application.step.StepService;
import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.admin.domain.step.Step;
import com.youyisi.admin.domain.step.StepRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-02
 */
@Service
public class StepServiceImpl implements StepService {

	@Autowired
	private StepRepository repository;

	@Autowired
	private AnnualYieldServiceImpl annualYieldServiceImpl;

	@Override
	public Step get(Long id) {
		return repository.get(id);
	}

	@Override
	public Step save(Step entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Step entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Step entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Step> queryPage(Page<Step> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer countStep(Long beginTime, Long endTime, Integer clientType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (beginTime != null && endTime != null) {
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
		}
		if (clientType != null) {
			map.put("clientType", clientType);
		}
		return repository.countStep(map);
	}

	@Override
	public Integer countStepByUserId(Long userId, Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (date != null) {
			map.put("date", date);
		}
		return repository.countStepByUserId(map);
	}

	@Override
	public Integer countStepNum(Long userId, Integer beginStep, Integer endStep) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (beginStep != null) {
			map.put("beginStep", beginStep);

		}
		if (endStep != null) {
			map.put("endStep", endStep);
		}
		if (userId != null) {
			map.put("userId", userId);
		}
		return repository.countStepNum(map);
	}

	@Override
	public Integer freeze(Long stepId) {
		Integer row = 0;
		Step step = repository.get(stepId);
		step.setStatus(-1);
		row = repository.update(step);
		if (row > 0) {
			AnnualYield byUserAndDate = annualYieldServiceImpl.getByUserAndDate(step.getUserId(), step.getDate());
			if (byUserAndDate != null) {
				byUserAndDate.setAnnualYield(0.0);
				byUserAndDate.setActivityAnnualYield(null);
				row += annualYieldServiceImpl.update(byUserAndDate);
			}
		}
		return row;
	}

	@Override
	public Step sumStep(Long userId, Long beginTime, Long endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return repository.sumStep(map);
	}
}
