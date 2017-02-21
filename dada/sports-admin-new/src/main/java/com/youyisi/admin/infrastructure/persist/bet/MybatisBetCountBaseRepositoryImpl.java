package com.youyisi.admin.infrastructure.persist.bet;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.admin.domain.bet.BetCountBaseRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisBetCountBaseRepositoryImpl extends MybatisOperations<Long, BetCountBase>
		implements BetCountBaseRepository {

	@Override
	public List<BetCountBase> getBetCountBaseList(Long lotteryBaseId) {

		return getSqlSession().selectList(getNamespace().concat(".getBetCountBaseList"), lotteryBaseId);
	}

	@Override
	public Integer deleteByLotteryBaseId(Long lotteryBaseId) {

		return getSqlSession().delete(getNamespace().concat(".deleteByLotteryBaseId"), lotteryBaseId);
	}
}
