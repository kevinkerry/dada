package com.youyisi.admin.infrastructure.persist.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.UserLottery;
import com.youyisi.admin.domain.user.UserLotteryRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-22
 */
@Repository
public class MybatisUserLotteryRepositoryImpl extends MybatisOperations<Long, UserLottery>
		implements UserLotteryRepository {

	@Override
	public List<UserLottery> getUserLotteryList(Long lotteryId) {

		return getSqlSession().selectList(getNamespace().concat(".getUserLotteryList"), lotteryId);
	}
}
