package com.youyisi.admin.infrastructure.persist.lottery;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.lottery.Lottery;
import com.youyisi.admin.domain.lottery.LotteryRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisLotteryRepositoryImpl extends MybatisOperations<Long, Lottery> implements LotteryRepository {
}

