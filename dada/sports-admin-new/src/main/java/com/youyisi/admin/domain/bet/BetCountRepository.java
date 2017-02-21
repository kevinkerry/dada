package com.youyisi.admin.domain.bet;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface BetCountRepository extends MybatisRepository<Long, BetCount> {

	Integer sumCount(Long lotteryId);

	Integer sumCountByUser(Map<String, Object> map);
}
