package com.youyisi.admin.application.lottery.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.lottery.LotteryWinService;
import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.admin.domain.lottery.LotteryWinRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class LotteryWinServiceImpl implements LotteryWinService {

	@Autowired
	private LotteryWinRepository repository;

	@Override
	public LotteryWin get(Long id) {
		return repository.get(id);
	}

	@Override
	public LotteryWin save(LotteryWin entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(LotteryWin entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(LotteryWin entity) {
		return repository.update(entity);
	}

	@Override
	public Page<LotteryWin> queryPage(Page<LotteryWin> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<LotteryWin> getLotteryWinList(Long lotteryId) {

		return repository.getLotteryWinList(lotteryId);
	}

	@Override
	public Double sumGoldBean(Long lotteryId) {

		return repository.sumGoldBean(lotteryId);
	}

	@Override
	public Double sumGrantGoldBean(Long lotteryId) {
		return repository.sumGrantGoldBean(lotteryId);
	}

}
