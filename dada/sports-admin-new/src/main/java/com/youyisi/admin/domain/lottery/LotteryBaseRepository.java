package com.youyisi.admin.domain.lottery;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface LotteryBaseRepository extends MybatisRepository<Long, LotteryBase> {

	LotteryBase getLotteryBase();
}
