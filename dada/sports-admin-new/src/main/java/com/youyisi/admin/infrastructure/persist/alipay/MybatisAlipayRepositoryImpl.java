package com.youyisi.admin.infrastructure.persist.alipay;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.alipay.Alipay;
import com.youyisi.admin.domain.alipay.AlipayRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Repository
public class MybatisAlipayRepositoryImpl extends MybatisOperations<Long, Alipay> implements AlipayRepository {

	@Override
	public Alipay getByUserId(Long userId) {
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}
}
