package com.youyisi.admin.application.gold.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.gold.GoldBeanDetailService;
import com.youyisi.admin.domain.gold.GoldBeanDetail;
import com.youyisi.admin.domain.gold.GoldBeanDetailRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanDetailServiceImpl implements GoldBeanDetailService {

	@Autowired
	private GoldBeanDetailRepository repository;

	@Override
	public GoldBeanDetail get(Long id) {
		return repository.get(id);
	}

	@Override
	public GoldBeanDetail save(GoldBeanDetail entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBeanDetail entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBeanDetail entity) {
		return repository.update(entity);
	}
	@Override
	public Page<GoldBeanDetail> queryPage(Page<GoldBeanDetail> page) {
		return repository.queryPage(page);
	}
}

