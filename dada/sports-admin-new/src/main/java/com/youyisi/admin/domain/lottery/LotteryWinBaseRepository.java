package com.youyisi.admin.domain.lottery;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryWinBaseRepository extends MybatisRepository<Long, LotteryWinBase> {

	List<LotteryWinBase> getLotteryWinBaseList(Long lotteryBaseId);

	Integer deleteByLotteryBaseId(Long lotteryBaseId);
}
