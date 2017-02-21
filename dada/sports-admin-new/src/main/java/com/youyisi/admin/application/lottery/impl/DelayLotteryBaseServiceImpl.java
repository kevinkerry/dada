package com.youyisi.admin.application.lottery.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.lottery.DelayLotteryBaseService;
import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.admin.domain.lottery.DelayLotteryBaseRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-24
 */
@Service
public class DelayLotteryBaseServiceImpl implements DelayLotteryBaseService {

	@Autowired
	private DelayLotteryBaseRepository repository;

	@Override
	public DelayLotteryBase get(Long id) {
		return repository.get(id);
	}

	@Override
	public DelayLotteryBase save(DelayLotteryBase entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(DelayLotteryBase entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(DelayLotteryBase entity) {
		return repository.update(entity);
	}

	@Override
	public Page<DelayLotteryBase> queryPage(Page<DelayLotteryBase> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<DelayLotteryBase> getDelayLotteryBaseList() {

		return repository.getDelayLotteryBaseList();
	}

	@Override
	public Integer deleteAll() {

		return repository.deleteAll();
	}

	@Override
	public DelayLotteryBase getDelayLotteryBaseByLotteryNum(String lotteryNum) {

		return repository.getDelayLotteryBaseByLotteryNum(lotteryNum);
	}
}
