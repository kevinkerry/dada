package com.youyisi.admin.application.snatch;

import com.youyisi.admin.domain.snatch.SnatchWin;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface SnatchWinService {

	SnatchWin save(SnatchWin entity);

	SnatchWin get(Long id);

	Integer delete(SnatchWin entity);

	Integer update(SnatchWin entity);

	Page<SnatchWin> queryPage(Page<SnatchWin> page);

	Double getSnatchWinByActivityIdAndUserId(Long activityId, Long userId);

}
