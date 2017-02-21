package com.youyisi.admin.application.lottery.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.lottery.LotteryWinBaseService;
import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.admin.domain.lottery.LotteryWinBaseRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class LotteryWinBaseServiceImpl implements LotteryWinBaseService {

	@Autowired
	private LotteryWinBaseRepository repository;

	@Override
	public LotteryWinBase get(Long id) {
		return repository.get(id);
	}

	@Override
	public LotteryWinBase save(LotteryWinBase entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(LotteryWinBase entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(LotteryWinBase entity) {
		return repository.update(entity);
	}

	@Override
	public Page<LotteryWinBase> queryPage(Page<LotteryWinBase> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<LotteryWinBase> getLotteryWinBaseList(Long lotteryBaseId) {

		return repository.getLotteryWinBaseList(lotteryBaseId);
	}

	@Override
	public Integer deleteByLotteryBaseId(Long LotteryBaseId) {

		return repository.deleteByLotteryBaseId(LotteryBaseId);
	}
}
