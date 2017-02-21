package com.youyisi.app.soa.remote.goldbean;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.goldbean.UserGoldBean;
import com.youyisi.sports.domain.orders.Orders;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface UserGoldBeanServiceRemote extends BaseServiceInterface<Long, UserGoldBean> {

	int pay(Long id)throws SoaException;

	void otherpay(Orders order);

}

