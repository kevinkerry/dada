package com.youyisi.admin.infrastructure.persist.bet;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.bet.BetCount;
import com.youyisi.admin.domain.bet.BetCountRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisBetCountRepositoryImpl extends MybatisOperations<Long, BetCount> implements BetCountRepository {

	@Override
	public Integer sumCount(Long lotteryId) {

		return getSqlSession().selectOne(getNamespace().concat(".sumCount"), lotteryId);
	}

	@Override
	public Integer sumCountByUser(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumCountByUser"), map);
	}
}
