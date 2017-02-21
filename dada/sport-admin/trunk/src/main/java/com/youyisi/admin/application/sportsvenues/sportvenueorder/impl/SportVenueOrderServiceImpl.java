package com.youyisi.admin.application.sportsvenues.sportvenueorder.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportsvenues.sportvenueorder.SportVenueOrderService;
import com.youyisi.admin.domain.sportsvenues.sportvenueorder.SportVenueOrder;
import com.youyisi.admin.domain.sportsvenues.sportvenueorder.SportVenueOrderRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Service
public class SportVenueOrderServiceImpl implements SportVenueOrderService {

	@Autowired
	private SportVenueOrderRepository repository;

	@Override
	public SportVenueOrder get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportVenueOrder save(SportVenueOrder entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(SportVenueOrder entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SportVenueOrder entity) {
		return repository.update(entity);
	}
	@Override
	public Page<SportVenueOrder> queryPage(Page<SportVenueOrder> page) {
		return repository.queryPage(page);
	}
}

