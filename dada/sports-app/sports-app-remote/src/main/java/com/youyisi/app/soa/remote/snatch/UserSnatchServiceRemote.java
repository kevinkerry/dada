package com.youyisi.app.soa.remote.snatch;

import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.snatch.UserSnatch;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface UserSnatchServiceRemote extends BaseServiceInterface<Long, UserSnatch> {

	Integer getCountByActivityId(Long activityId);

	Integer getCountByActivityIdAndUserId(Long activityId, Long userId);

	int pay(UserSnatch us);

	void otherpay(Orders order);

}

