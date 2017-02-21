package com.youyisi.admin.application.bet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.bet.BetCountBaseService;
import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.admin.domain.bet.BetCountBaseRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class BetCountBaseServiceImpl implements BetCountBaseService {

	@Autowired
	private BetCountBaseRepository repository;

	@Override
	public BetCountBase get(Long id) {
		return repository.get(id);
	}

	@Override
	public BetCountBase save(BetCountBase entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(BetCountBase entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(BetCountBase entity) {
		return repository.update(entity);
	}

	@Override
	public Page<BetCountBase> queryPage(Page<BetCountBase> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<BetCountBase> getBetCountBaseList(Long lotteryBaseId) {

		return repository.getBetCountBaseList(lotteryBaseId);
	}

	@Override
	public Integer deleteByLotteryBaseId(Long lotteryBaseId) {

		return repository.deleteByLotteryBaseId(lotteryBaseId);
	}
}
