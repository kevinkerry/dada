package com.youyisi.admin.application.orders;

import com.youyisi.admin.domain.orders.Orders;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-20
 */
public interface OrdersService {

	Orders save(Orders entity);

	Orders get(Long id);

	Integer delete(Orders entity);

	Integer update(Orders entity);

	Page<Orders> queryPage(Page<Orders> page);

	Double countPay(Long userId);

}
