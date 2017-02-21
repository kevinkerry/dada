package com.youyisi.app.soa.remote.lottery;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.lottery.UserLottery;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface UserLotteryServiceRemote extends BaseServiceInterface<Long, UserLottery> {

	int add(UserLottery userLottery)throws SoaException;

	Integer getUserLotteryCount(Long lotteryId, Long userId);

}

