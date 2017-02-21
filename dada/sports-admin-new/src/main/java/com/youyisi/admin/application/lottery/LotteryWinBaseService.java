package com.youyisi.admin.application.lottery;

import java.util.List;

import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryWinBaseService {

	LotteryWinBase save(LotteryWinBase entity);

	LotteryWinBase get(Long id);

	Integer delete(LotteryWinBase entity);

	Integer update(LotteryWinBase entity);

	Page<LotteryWinBase> queryPage(Page<LotteryWinBase> page);

	List<LotteryWinBase> getLotteryWinBaseList(Long lotteryBaseId);

	Integer deleteByLotteryBaseId(Long LotteryBaseId);

}
