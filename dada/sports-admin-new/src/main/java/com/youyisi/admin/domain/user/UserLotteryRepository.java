package com.youyisi.admin.domain.user;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-22
 */
public interface UserLotteryRepository extends MybatisRepository<Long, UserLottery> {

	List<UserLottery> getUserLotteryList(Long lotteryId);
}
