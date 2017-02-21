package com.youyisi.app.soa.remote.snatch;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.snatch.SnatchWin;
import com.youyisi.sports.domain.snatch.SnatchWinWithMore;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface SnatchWinServiceRemote extends BaseServiceInterface<Long, SnatchWin> {

	List<SnatchWinWithMore> winResult(Long activityId)throws SoaException;

	SnatchWin getByUserId(Long userId)throws SoaException;

}

