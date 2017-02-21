package com.youyisi.admin.application.orders.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.orders.OrdersService;
import com.youyisi.admin.domain.orders.Orders;
import com.youyisi.admin.domain.orders.OrdersRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-20
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository repository;

	@Override
	public Orders get(Long id) {
		return repository.get(id);
	}

	@Override
	public Orders save(Orders entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Orders entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Orders entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Orders> queryPage(Page<Orders> page) {
		return repository.queryPage(page);
	}

	@Override
	public Double countPay(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("payStatus", 1);
		return repository.countPay(map);
	}
}
