package com.youyisi.admin.application.annual.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.annual.AnnualYieldService;
import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.admin.domain.annual.AnnualYieldRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Service
public class AnnualYieldServiceImpl implements AnnualYieldService {

	@Autowired
	private AnnualYieldRepository repository;

	@Override
	public AnnualYield get(Long id) {
		return repository.get(id);
	}

	@Override
	public AnnualYield save(AnnualYield entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(AnnualYield entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(AnnualYield entity) {
		return repository.update(entity);
	}
	@Override
	public Page<AnnualYield> queryPage(Page<AnnualYield> page) {
		return repository.queryPage(page);
	}

	@Override
	public AnnualYield getByUserAndDate(Long userId, Long date) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("date", date);
		return repository.getByUserAndDate(map);
	}
}

