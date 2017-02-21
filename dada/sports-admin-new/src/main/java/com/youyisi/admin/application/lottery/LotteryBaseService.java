package com.youyisi.admin.application.lottery;

import com.youyisi.admin.domain.lottery.LotteryBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryBaseService {

	LotteryBase save(LotteryBase entity);

	LotteryBase get(Long id);

	Integer delete(LotteryBase entity);

	Integer update(LotteryBase entity);

	Page<LotteryBase> queryPage(Page<LotteryBase> page);

	LotteryBase getLotteryBase();

	Integer addLotteryBase(LotteryBase lotteryBase);

}
