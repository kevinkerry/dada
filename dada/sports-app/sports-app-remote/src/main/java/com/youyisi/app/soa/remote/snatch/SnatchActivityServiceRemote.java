package com.youyisi.app.soa.remote.snatch;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchActivityWithSnatchWinWithMore;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface SnatchActivityServiceRemote extends BaseServiceInterface<Long, SnatchActivity> {

	Page<SnatchActivityWithSnatchWinWithMore> queryPageForHistory(Page<SnatchActivityWithSnatchWinWithMore> page)throws SoaException;

	Double getUserDistance(ActivityWithSnatchActivity activity, Long userId);

}

