package com.youyisi.app.soa.infrastructure.persist.lottery.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.lottery.BetCountRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.lottery.BetCount;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisBetCountRepositoryImpl extends MybatisOperations<Long, BetCount> implements BetCountRepository {

	@Override
	public List<BetCount> getByLotteryId(Long lotteryId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getByLotteryId"), lotteryId);
	}
}

