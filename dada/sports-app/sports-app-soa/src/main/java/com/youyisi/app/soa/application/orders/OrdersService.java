package com.youyisi.app.soa.application.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.orders.OrdersRepository;
import com.youyisi.app.soa.remote.orders.OrdersServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.orders.Orders;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class OrdersService implements OrdersServiceRemote {

	@Autowired
	private OrdersRepository repository;

	@Override
	public Orders get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Orders save(Orders entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Orders entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Orders entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Orders> queryPage(Page<Orders> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Orders getByOrderNumber(String orderNumber) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByOrderNumber(orderNumber);
	}
}

