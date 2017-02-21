package com.youyisi.admin.application.snatch;

import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface SnatchActivityService {

	SnatchActivity save(SnatchActivity entity);

	SnatchActivity get(Long id);

	Integer delete(SnatchActivity entity);

	Integer update(SnatchActivity entity);

	Page<SnatchActivity> queryPage(Page<SnatchActivity> page);

	Integer delByActivityId(Long activityId);

	SnatchActivity getByActivityId(Long activityId);

	Integer settle(SnatchActivity snatchActivity);

	Integer refund(Long activityId);

}
