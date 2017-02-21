package com.youyisi.app.soa.remote.lottery;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.LotteryWithMore;
import com.youyisi.sports.domain.lottery.LotteryWithUserWinMore;
import com.youyisi.sports.domain.user.User;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryServiceRemote extends BaseServiceInterface<Long, Lottery> {

	LotteryWithMore getByDate(Long dateForDay, User user)throws SoaException;

	Page<LotteryWithUserWinMore> queryPageForHistory(Page<LotteryWithUserWinMore> page)throws SoaException;

}

