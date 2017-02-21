package com.youyisi.admin.domain.alipay;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-21
 */
public interface AlipayRepository extends MybatisRepository<Long, Alipay> {

	Alipay getByUserId(Long userId);
}
