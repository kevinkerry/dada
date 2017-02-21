package com.youyisi.app.soa.remote.lottery;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.lottery.UserLotteryWin;
import com.youyisi.sports.domain.lottery.UserLotteryWinMore;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface UserLotteryWinServiceRemote extends BaseServiceInterface<Long, UserLotteryWin> {

	UserLotteryWinMore getWin(Long userId)throws SoaException;

	void getLotteryWinCoupon(UserLotteryWinMore userLotteryWinMore)throws SoaException;

	UserLotteryWinMore getUserLotteryWinMore(Long id)throws SoaException;

}

