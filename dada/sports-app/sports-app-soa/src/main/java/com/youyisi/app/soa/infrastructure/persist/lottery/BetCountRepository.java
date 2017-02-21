package com.youyisi.app.soa.infrastructure.persist.lottery;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.lottery.BetCount;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface BetCountRepository extends MybatisRepository<Long, BetCount> {

	List<BetCount> getByLotteryId(Long lotteryId);
}

