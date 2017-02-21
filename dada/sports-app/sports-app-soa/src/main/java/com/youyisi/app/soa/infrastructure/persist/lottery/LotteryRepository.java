package com.youyisi.app.soa.infrastructure.persist.lottery;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.LotteryWithMore;
import com.youyisi.sports.domain.lottery.LotteryWithUserWinMore;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryRepository extends MybatisRepository<Long, Lottery> {

	LotteryWithMore getByDate(Long dateForDay);

	Page<LotteryWithUserWinMore> queryPageForHistory(
			Page<LotteryWithUserWinMore> page);
}

