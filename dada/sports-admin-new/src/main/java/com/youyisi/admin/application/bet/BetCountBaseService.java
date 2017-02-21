package com.youyisi.admin.application.bet;

import java.util.List;

import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface BetCountBaseService {

	BetCountBase save(BetCountBase entity);

	BetCountBase get(Long id);

	Integer delete(BetCountBase entity);

	Integer update(BetCountBase entity);

	Page<BetCountBase> queryPage(Page<BetCountBase> page);

	List<BetCountBase> getBetCountBaseList(Long lotteryBaseId);

	Integer deleteByLotteryBaseId(Long lotteryBaseId);

}
