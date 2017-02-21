package com.youyisi.admin.application.hugthigh;

import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
public interface HugThighActivityService {

	HugThighActivity save(HugThighActivity entity);

	HugThighActivity get(Long id);

	Integer delete(HugThighActivity entity);

	Integer update(HugThighActivity entity);

	Page<HugThighActivity> queryPage(Page<HugThighActivity> page);

	Integer delByActivityId(Long activityId);

	HugThighActivity getByActivityId(Long activityId);

}
