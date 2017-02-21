package com.youyisi.admin.application.user;

import java.util.List;

import com.youyisi.admin.domain.user.UserLottery;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-22
 */
public interface UserLotteryService {

	UserLottery save(UserLottery entity);

	UserLottery get(Long id);

	Integer delete(UserLottery entity);

	Integer update(UserLottery entity);

	Page<UserLottery> queryPage(Page<UserLottery> page);

	List<UserLottery> getUserLotteryList(Long lotteryId);

}
