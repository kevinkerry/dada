package com.youyisi.admin.domain.lottery;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryWinRepository extends MybatisRepository<Long, LotteryWin> {

	List<LotteryWin> getLotteryWinList(Long lotteryId);

	Double sumGoldBean(Long lotteryId);

	Double sumGrantGoldBean(Long lotteryId);

}
