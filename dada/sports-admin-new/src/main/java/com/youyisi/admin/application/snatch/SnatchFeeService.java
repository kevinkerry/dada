package com.youyisi.admin.application.snatch;

import java.util.List;

import com.youyisi.admin.domain.snatch.SnatchFee;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface SnatchFeeService {

	SnatchFee save(SnatchFee entity);

	SnatchFee get(Long id);

	Integer delete(SnatchFee entity);

	Integer update(SnatchFee entity);

	Page<SnatchFee> queryPage(Page<SnatchFee> page);

	Integer delByActivityId(Long activityId);

	List<SnatchFee> getSnatchFeeByActivityId(Long activityId);

}
