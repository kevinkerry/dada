package com.youyisi.admin.infrastructure.persist.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.UserLotteryWin;
import com.youyisi.admin.domain.user.UserLotteryWinRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-24
 */
@Repository
public class MybatisUserLotteryWinRepositoryImpl extends MybatisOperations<Long, UserLotteryWin>
		implements UserLotteryWinRepository {

	@Override
	public Integer getLevelNumber(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getLevelNumber"), map);
	}

	@Override
	public UserLotteryWin getUserLotteryWinByUserIdAndLotteryId(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getUserLotteryWinByUserIdAndLotteryId"), map);
	}

	@Override
	public List<UserLotteryWin> getUserLotteryWinList(Map<String, Object> map) {

		return getSqlSession().selectList(getNamespace().concat(".getUserLotteryWinList"), map);
	}
}
