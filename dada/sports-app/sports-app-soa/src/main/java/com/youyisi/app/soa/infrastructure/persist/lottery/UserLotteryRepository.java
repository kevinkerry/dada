package com.youyisi.app.soa.infrastructure.persist.lottery;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.lottery.UserLottery;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface UserLotteryRepository extends MybatisRepository<Long, UserLottery> {

	Integer getUserLotteryCount(Long lotteryId, Long userId);
}

