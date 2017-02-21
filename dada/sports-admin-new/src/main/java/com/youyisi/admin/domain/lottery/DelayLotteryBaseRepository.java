package com.youyisi.admin.domain.lottery;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-24
 */
public interface DelayLotteryBaseRepository extends MybatisRepository<Long, DelayLotteryBase> {

	List<DelayLotteryBase> getDelayLotteryBaseList();

	Integer deleteAll();

	DelayLotteryBase getDelayLotteryBaseByLotteryNum(String lotteryNum);
}
