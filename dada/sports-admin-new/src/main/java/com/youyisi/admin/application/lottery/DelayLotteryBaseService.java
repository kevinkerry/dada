package com.youyisi.admin.application.lottery;

import java.util.List;

import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-24
 */
public interface DelayLotteryBaseService {

	DelayLotteryBase save(DelayLotteryBase entity);

	DelayLotteryBase get(Long id);

	Integer delete(DelayLotteryBase entity);

	Integer update(DelayLotteryBase entity);

	Page<DelayLotteryBase> queryPage(Page<DelayLotteryBase> page);

	List<DelayLotteryBase> getDelayLotteryBaseList();

	Integer deleteAll();

	DelayLotteryBase getDelayLotteryBaseByLotteryNum(String lotteryNum);

}
