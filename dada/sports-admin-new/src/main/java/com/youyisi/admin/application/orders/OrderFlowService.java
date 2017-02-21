package com.youyisi.admin.application.orders;

import com.youyisi.admin.domain.orders.OrderFlow;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-20
 */
public interface OrderFlowService {

	OrderFlow save(OrderFlow entity);

	OrderFlow get(Long id);

	Integer delete(OrderFlow entity);

	Integer update(OrderFlow entity);

	Page<OrderFlow> queryPage(Page<OrderFlow> page);

}

