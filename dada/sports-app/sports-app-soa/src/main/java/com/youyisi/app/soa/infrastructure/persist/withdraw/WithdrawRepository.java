package com.youyisi.app.soa.infrastructure.persist.withdraw;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.withdraw.Withdraw;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface WithdrawRepository extends MybatisRepository<Long, Withdraw> {

	Double currentWithdraw(Long userId);
}

