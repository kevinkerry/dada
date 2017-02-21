package com.youyisi.admin.application.bet;

import com.youyisi.admin.domain.bet.BetCount;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface BetCountService {

	BetCount save(BetCount entity);

	BetCount get(Long id);

	Integer delete(BetCount entity);

	Integer update(BetCount entity);

	Page<BetCount> queryPage(Page<BetCount> page);

	Integer sumCount(Long lotteryId);

	Integer sumCountByUser(Long lotteryId, Long userId);

}
