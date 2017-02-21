package com.youyisi.admin.application.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.user.UserLotteryWinService;
import com.youyisi.admin.domain.user.UserLotteryWin;
import com.youyisi.admin.domain.user.UserLotteryWinRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-24
 */
@Service
public class UserLotteryWinServiceImpl implements UserLotteryWinService {

	@Autowired
	private UserLotteryWinRepository repository;

	@Override
	public UserLotteryWin get(Long id) {
		return repository.get(id);
	}

	@Override
	public UserLotteryWin save(UserLotteryWin entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(UserLotteryWin entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserLotteryWin entity) {
		return repository.update(entity);
	}

	@Override
	public Page<UserLotteryWin> queryPage(Page<UserLotteryWin> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer getLevelNumber(Long lotteryId, Integer level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lotteryId", lotteryId);
		map.put("level", level);
		return repository.getLevelNumber(map);
	}

	@Override
	public UserLotteryWin getUserLotteryWinByUserIdAndLotteryId(Long userId, Long lotteryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("lotteryId", lotteryId);
		return repository.getUserLotteryWinByUserIdAndLotteryId(map);
	}

	@Override
	public List<UserLotteryWin> getUserLotteryWinList(Long beginTime, Long endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return repository.getUserLotteryWinList(map);
	}
}
