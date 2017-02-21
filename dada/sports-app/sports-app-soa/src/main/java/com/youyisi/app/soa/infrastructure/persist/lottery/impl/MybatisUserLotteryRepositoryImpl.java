package com.youyisi.app.soa.infrastructure.persist.lottery.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.lottery.UserLottery;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisUserLotteryRepositoryImpl extends MybatisOperations<Long, UserLottery> implements UserLotteryRepository {

	@Override
	public Integer getUserLotteryCount(Long lotteryId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("lotteryId", lotteryId);
		return getSqlSession().selectOne(getNamespace().concat(".getUserLotteryCount"), map);
	}
}

