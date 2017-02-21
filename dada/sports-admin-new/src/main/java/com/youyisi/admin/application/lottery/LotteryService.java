package com.youyisi.admin.application.lottery;

import com.youyisi.admin.domain.lottery.Lottery;
import com.youyisi.admin.domain.lottery.LotteryTemplate;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryService {

	Lottery save(Lottery entity);

	Lottery get(Long id);

	Integer delete(Lottery entity);

	Integer update(Lottery entity);

	Page<Lottery> queryPage(Page<Lottery> page);

	Integer runLottery(LotteryTemplate lotteryTemplate);

	Lottery getLotteryById(Long id);

}
