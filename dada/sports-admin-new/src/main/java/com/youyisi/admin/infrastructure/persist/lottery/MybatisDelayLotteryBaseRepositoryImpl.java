package com.youyisi.admin.infrastructure.persist.lottery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.admin.domain.lottery.DelayLotteryBaseRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-24
 */
@Repository
public class MybatisDelayLotteryBaseRepositoryImpl extends MybatisOperations<Long, DelayLotteryBase>
		implements DelayLotteryBaseRepository {

	@Override
	public List<DelayLotteryBase> getDelayLotteryBaseList() {
		return getSqlSession().selectList(getNamespace().concat(".getDelayLotteryBaseList"));
	}

	@Override
	public Integer deleteAll() {
		return getSqlSession().delete(getNamespace().concat(".deleteAll"));
	}

	@Override
	public DelayLotteryBase getDelayLotteryBaseByLotteryNum(String lotteryNum) {

		return getSqlSession().selectOne(getNamespace().concat(".getDelayLotteryBaseByLotteryNum"), lotteryNum);
	}
}
