package com.youyisi.admin.infrastructure.persist.lottery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.admin.domain.lottery.LotteryWinBaseRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisLotteryWinBaseRepositoryImpl extends MybatisOperations<Long, LotteryWinBase>
		implements LotteryWinBaseRepository {

	@Override
	public List<LotteryWinBase> getLotteryWinBaseList(Long lotteryBaseId) {

		return getSqlSession().selectList(getNamespace().concat(".getLotteryWinBaseList"), lotteryBaseId);
	}

	@Override
	public Integer deleteByLotteryBaseId(Long lotteryBaseId) {

		return getSqlSession().delete(getNamespace().concat(".deleteByLotteryBaseId"), lotteryBaseId);
	}
}
