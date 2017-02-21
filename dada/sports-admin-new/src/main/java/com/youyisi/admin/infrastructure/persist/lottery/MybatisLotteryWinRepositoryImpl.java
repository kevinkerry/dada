package com.youyisi.admin.infrastructure.persist.lottery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.admin.domain.lottery.LotteryWinRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisLotteryWinRepositoryImpl extends MybatisOperations<Long, LotteryWin>
		implements LotteryWinRepository {

	@Override
	public List<LotteryWin> getLotteryWinList(Long lotteryId) {

		return getSqlSession().selectList(getNamespace().concat(".getLotteryWinList"), lotteryId);
	}

	@Override
	public Double sumGoldBean(Long lotteryId) {
		return getSqlSession().selectOne(getNamespace().concat(".sumGoldBean"), lotteryId);
	}

	@Override
	public Double sumGrantGoldBean(Long lotteryId) {
		return getSqlSession().selectOne(getNamespace().concat(".sumGrantGoldBean"), lotteryId);
	}

}
