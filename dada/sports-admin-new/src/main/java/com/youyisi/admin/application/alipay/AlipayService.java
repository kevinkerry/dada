package com.youyisi.admin.application.alipay;

import com.youyisi.admin.domain.alipay.Alipay;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-21
 */
public interface AlipayService {

	Alipay save(Alipay entity);

	Alipay get(Long id);

	Integer delete(Alipay entity);

	Integer update(Alipay entity);

	Page<Alipay> queryPage(Page<Alipay> page);

	Alipay getByUserId(Long userId);

}

