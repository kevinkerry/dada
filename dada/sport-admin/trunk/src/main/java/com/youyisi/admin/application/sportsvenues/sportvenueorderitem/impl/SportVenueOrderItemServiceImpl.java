package com.youyisi.admin.application.sportsvenues.sportvenueorderitem.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.sportsvenues.sportvenueorderitem.SportVenueOrderItemService;
import com.youyisi.admin.domain.sportsvenues.sportvenueorderitem.SportVenueOrderItem;
import com.youyisi.admin.domain.sportsvenues.sportvenueorderitem.SportVenueOrderItemRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Service
public class SportVenueOrderItemServiceImpl implements SportVenueOrderItemService {

	@Autowired
	private SportVenueOrderItemRepository repository;

	@Override
	public SportVenueOrderItem get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportVenueOrderItem save(SportVenueOrderItem entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(SportVenueOrderItem entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SportVenueOrderItem entity) {
		return repository.update(entity);
	}
	@Override
	public Page<SportVenueOrderItem> queryPage(Page<SportVenueOrderItem> page) {
		return repository.queryPage(page);
	}
}

