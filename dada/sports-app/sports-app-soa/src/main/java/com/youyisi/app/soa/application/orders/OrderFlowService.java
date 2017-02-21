package com.youyisi.app.soa.application.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.orders.OrderFlowServiceRemote;
import com.youyisi.sports.domain.orders.OrderFlow;
import com.youyisi.app.soa.infrastructure.persist.orders.OrderFlowRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
public class OrderFlowService implements OrderFlowServiceRemote {

	@Autowired
	private OrderFlowRepository repository;

	@Override
	public OrderFlow get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public OrderFlow save(OrderFlow entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(OrderFlow entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(OrderFlow entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<OrderFlow> queryPage(Page<OrderFlow> page) throws SoaException{
		return repository.queryPage(page);
	}
}

