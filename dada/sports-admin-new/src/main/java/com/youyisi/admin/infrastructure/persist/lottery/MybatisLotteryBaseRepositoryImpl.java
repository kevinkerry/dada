package com.youyisi.admin.infrastructure.persist.lottery;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.lottery.LotteryBase;
import com.youyisi.admin.domain.lottery.LotteryBaseRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisLotteryBaseRepositoryImpl extends MybatisOperations<Long, LotteryBase>
		implements LotteryBaseRepository {

	@Override
	public LotteryBase getLotteryBase() {

		return getSqlSession().selectOne(getNamespace().concat(".getLotteryBase"));
	}
}
