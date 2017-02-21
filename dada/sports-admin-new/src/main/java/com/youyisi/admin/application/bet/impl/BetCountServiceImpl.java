package com.youyisi.admin.application.bet.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.bet.BetCountService;
import com.youyisi.admin.domain.bet.BetCount;
import com.youyisi.admin.domain.bet.BetCountRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class BetCountServiceImpl implements BetCountService {

	@Autowired
	private BetCountRepository repository;

	@Override
	public BetCount get(Long id) {
		return repository.get(id);
	}

	@Override
	public BetCount save(BetCount entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(BetCount entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(BetCount entity) {
		return repository.update(entity);
	}

	@Override
	public Page<BetCount> queryPage(Page<BetCount> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer sumCount(Long lotteryId) {

		return repository.sumCount(lotteryId);
	}

	@Override
	public Integer sumCountByUser(Long lotteryId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lotteryId", lotteryId);
		map.put("userId", userId);
		return repository.sumCountByUser(map);
	}
}
