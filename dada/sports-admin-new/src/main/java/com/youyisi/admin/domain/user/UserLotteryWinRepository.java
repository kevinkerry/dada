package com.youyisi.admin.domain.user;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-24
 */
public interface UserLotteryWinRepository extends MybatisRepository<Long, UserLotteryWin> {

	Integer getLevelNumber(Map<String, Object> map);

	UserLotteryWin getUserLotteryWinByUserIdAndLotteryId(Map<String, Object> map);

	List<UserLotteryWin> getUserLotteryWinList(Map<String, Object> map);
}
