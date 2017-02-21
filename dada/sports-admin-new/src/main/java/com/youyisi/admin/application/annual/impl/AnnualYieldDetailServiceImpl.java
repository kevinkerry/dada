package com.youyisi.admin.application.annual.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.annual.AnnualYieldDetailService;
import com.youyisi.admin.domain.annual.AnnualYieldDetail;
import com.youyisi.admin.domain.annual.AnnualYieldDetailRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Service
public class AnnualYieldDetailServiceImpl implements AnnualYieldDetailService {

	@Autowired
	private AnnualYieldDetailRepository repository;

	@Override
	public AnnualYieldDetail get(Long id) {
		return repository.get(id);
	}

	@Override
	public AnnualYieldDetail save(AnnualYieldDetail entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(AnnualYieldDetail entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(AnnualYieldDetail entity) {
		return repository.update(entity);
	}
	@Override
	public Page<AnnualYieldDetail> queryPage(Page<AnnualYieldDetail> page) {
		return repository.queryPage(page);
	}
}

