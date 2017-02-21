package com.youyisi.app.soa.infrastructure.persist.lottery.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.lottery.LotteryWinRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.lottery.LotteryWin;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisLotteryWinRepositoryImpl extends MybatisOperations<Long, LotteryWin> implements LotteryWinRepository {

	
}

