package com.youyisi.app.soa.remote.orders;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.orders.Orders;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface OrdersServiceRemote extends BaseServiceInterface<Long, Orders> {

	Orders getByOrderNumber(String orderNumber) throws SoaException;

}

