package com.youyisi.app.soa.infrastructure.persist.lottery;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.lottery.UserLotteryWin;
import com.youyisi.sports.domain.lottery.UserLotteryWinMore;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface UserLotteryWinRepository extends MybatisRepository<Long, UserLotteryWin> {
	Integer getWinLevel(Long lotteryId, Long userId);

	UserLotteryWinMore getWin(Long currentTime, Long userId);

	void updateUserLotteryWinMore(UserLotteryWinMore userLotteryWinMore);

	UserLotteryWinMore getUserLotteryWinMore(Long id);
}

