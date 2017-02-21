package com.youyisi.admin.application.gold.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.gold.GoldBeanService;
import com.youyisi.admin.domain.gold.GoldBean;
import com.youyisi.admin.domain.gold.GoldBeanRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanServiceImpl implements GoldBeanService {

	@Autowired
	private GoldBeanRepository repository;

	@Override
	public GoldBean get(Long id) {
		return repository.get(id);
	}

	@Override
	public GoldBean save(GoldBean entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBean entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBean entity) {
		return repository.update(entity);
	}
	@Override
	public Page<GoldBean> queryPage(Page<GoldBean> page) {
		return repository.queryPage(page);
	}
}

