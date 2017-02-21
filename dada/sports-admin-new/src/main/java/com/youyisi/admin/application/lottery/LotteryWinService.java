package com.youyisi.admin.application.lottery;

import java.util.List;

import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryWinService {

	LotteryWin save(LotteryWin entity);

	LotteryWin get(Long id);

	Integer delete(LotteryWin entity);

	Integer update(LotteryWin entity);

	Page<LotteryWin> queryPage(Page<LotteryWin> page);

	List<LotteryWin> getLotteryWinList(Long lotteryId);

	/**
	 * 总奖金
	 * 
	 * @param lotteryId
	 * @return Double
	 */
	Double sumGoldBean(Long lotteryId);

	/***
	 * 已发放的奖金
	 * 
	 * @param lotteryId
	 * @return Double
	 */
	Double sumGrantGoldBean(Long lotteryId);

}
