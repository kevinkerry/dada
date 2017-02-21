package com.youyisi.app.soa.infrastructure.persist.alipay;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.alipay.Alipay;

/**
 * @author shuye
 * @time 2016-05-19
 */
public interface AlipayRepository extends MybatisRepository<Long, Alipay> {

	Alipay getByUserId(Long userId);

	Alipay getByAlipay(String alipay);
}

