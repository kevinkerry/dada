package com.youyisi.admin.application.alipay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.alipay.AlipayService;
import com.youyisi.admin.domain.alipay.Alipay;
import com.youyisi.admin.domain.alipay.AlipayRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Service
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	private AlipayRepository repository;

	@Override
	public Alipay get(Long id) {
		return repository.get(id);
	}

	@Override
	public Alipay save(Alipay entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Alipay entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Alipay entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Alipay> queryPage(Page<Alipay> page) {
		return repository.queryPage(page);
	}

	@Override
	public Alipay getByUserId(Long userId) {

		return repository.getByUserId(userId);
	}

}
