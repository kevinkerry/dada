package com.youyisi.app.soa.remote.lottery;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.lottery.BetCount;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface BetCountServiceRemote extends BaseServiceInterface<Long, BetCount> {

	List<BetCount> getByLotteryId(Long lotteryId)throws SoaException;

}

