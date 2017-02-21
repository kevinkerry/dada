package com.youyisi.app.soa.infrastructure.persist.lottery.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryWinRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.lottery.UserLotteryWin;
import com.youyisi.sports.domain.lottery.UserLotteryWinMore;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisUserLotteryWinRepositoryImpl extends MybatisOperations<Long, UserLotteryWin> implements UserLotteryWinRepository {
	@Override
	public Integer getWinLevel(Long lotteryId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("lotteryId", lotteryId);
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getWinLevel"), map);
	}
	
	
	@Override
	public UserLotteryWinMore getWin(Long time, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("time", time);
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getWin"), map);
	}


	@Override
	public void updateUserLotteryWinMore(UserLotteryWinMore userLotteryWinMore) {
		// TODO Auto-generated method stub
		getSqlSession().update(getNamespace().concat(".updateUserLotteryWinMore"), userLotteryWinMore);
	}


	@Override
	public UserLotteryWinMore getUserLotteryWinMore(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getUserLotteryWinMore"), id);
	}
}

