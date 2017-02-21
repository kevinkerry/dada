package com.youyisi.admin.application.gold.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.gold.GoldBeanRechargeService;
import com.youyisi.admin.domain.gold.GoldBeanRecharge;
import com.youyisi.admin.domain.gold.GoldBeanRechargeRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-26
 */
@Service
public class GoldBeanRechargeServiceImpl implements GoldBeanRechargeService {

	@Autowired
	private GoldBeanRechargeRepository repository;

	@Override
	public GoldBeanRecharge get(Long id) {
		return repository.get(id);
	}

	@Override
	public GoldBeanRecharge save(GoldBeanRecharge entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(GoldBeanRecharge entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBeanRecharge entity) {
		return repository.update(entity);
	}

	@Override
	public Page<GoldBeanRecharge> queryPage(Page<GoldBeanRecharge> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<GoldBeanRecharge> getGoldBeanRechargeList() {

		return repository.getGoldBeanRechargeList();
	}

	@Override
	public Integer saveGoldBeanRecharge(GoldBeanRecharge goldBeanRecharge) {
		int row = 0;
		deleteAll();
		List<GoldBeanRecharge> goldBeanRechargeList = goldBeanRecharge.getGoldBeanRechargeList();
		for (GoldBeanRecharge gb : goldBeanRechargeList) {
			save(gb);
			row += 1;
		}
		return row;
	}

	@Override
	public Integer deleteAll() {

		return repository.deleteAll();
	}
}
