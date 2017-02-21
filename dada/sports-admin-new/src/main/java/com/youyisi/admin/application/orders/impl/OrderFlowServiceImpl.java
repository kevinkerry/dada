package com.youyisi.admin.application.orders.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.orders.OrderFlowService;
import com.youyisi.admin.domain.orders.OrderFlow;
import com.youyisi.admin.domain.orders.OrderFlowRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-20
 */
@Service
public class OrderFlowServiceImpl implements OrderFlowService {

	@Autowired
	private OrderFlowRepository repository;

	@Override
	public OrderFlow get(Long id) {
		return repository.get(id);
	}

	@Override
	public OrderFlow save(OrderFlow entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(OrderFlow entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(OrderFlow entity) {
		return repository.update(entity);
	}
	@Override
	public Page<OrderFlow> queryPage(Page<OrderFlow> page) {
		return repository.queryPage(page);
	}
}

