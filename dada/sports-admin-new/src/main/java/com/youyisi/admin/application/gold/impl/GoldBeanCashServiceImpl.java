package com.youyisi.admin.application.gold.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.gold.GoldBeanCashService;
import com.youyisi.admin.domain.gold.GoldBeanCash;
import com.youyisi.admin.domain.gold.GoldBeanCashRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanCashServiceImpl implements GoldBeanCashService {

	@Autowired
	private GoldBeanCashRepository repository;

	@Override
	public GoldBeanCash get(Long id) {
		return repository.get(id);
	}

	@Override
	public GoldBeanCash save(GoldBeanCash entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBeanCash entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBeanCash entity) {
		return repository.update(entity);
	}
	@Override
	public Page<GoldBeanCash> queryPage(Page<GoldBeanCash> page) {
		return repository.queryPage(page);
	}
}

