package com.youyisi.admin.domain.bet;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface BetCountBaseRepository extends MybatisRepository<Long, BetCountBase> {

	List<BetCountBase> getBetCountBaseList(Long lotteryBaseId);

	Integer deleteByLotteryBaseId(Long lotteryBaseId);
}
