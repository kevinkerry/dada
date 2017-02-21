package com.youyisi.app.soa.infrastructure.persist.lottery.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.lottery.LotteryRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.LotteryWithMore;
import com.youyisi.sports.domain.lottery.LotteryWithUserWinMore;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisLotteryRepositoryImpl extends MybatisOperations<Long, Lottery> implements LotteryRepository {

	@Override
	public LotteryWithMore getByDate(Long date) {
		
		return getSqlSession().selectOne(getNamespace().concat(".getByDate"), date);
	}

	@Override
	public Page<LotteryWithUserWinMore> queryPageForHistory(
			Page<LotteryWithUserWinMore> page) {
		List<LotteryWithUserWinMore> results = getSqlSession().selectList(getNamespace().concat(".queryPageForHistory"), page);
		page.setResult(results);
		return page;
	}
}

