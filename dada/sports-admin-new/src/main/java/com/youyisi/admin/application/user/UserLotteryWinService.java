package com.youyisi.admin.application.user;

import java.util.List;

import com.youyisi.admin.domain.user.UserLotteryWin;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-24
 */
public interface UserLotteryWinService {

	UserLotteryWin save(UserLotteryWin entity);

	UserLotteryWin get(Long id);

	Integer delete(UserLotteryWin entity);

	Integer update(UserLotteryWin entity);

	Page<UserLotteryWin> queryPage(Page<UserLotteryWin> page);

	Integer getLevelNumber(Long lotteryId, Integer level);

	UserLotteryWin getUserLotteryWinByUserIdAndLotteryId(Long userId, Long lotteryId);

	List<UserLotteryWin> getUserLotteryWinList(Long beginTime, Long endTime);

}
