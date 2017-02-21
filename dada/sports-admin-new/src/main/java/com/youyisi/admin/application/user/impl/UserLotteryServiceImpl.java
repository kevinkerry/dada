package com.youyisi.admin.application.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.user.UserLotteryService;
import com.youyisi.admin.domain.user.UserLottery;
import com.youyisi.admin.domain.user.UserLotteryRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-22
 */
@Service
public class UserLotteryServiceImpl implements UserLotteryService {

	@Autowired
	private UserLotteryRepository repository;

	@Override
	public UserLottery get(Long id) {
		return repository.get(id);
	}

	@Override
	public UserLottery save(UserLottery entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(UserLottery entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserLottery entity) {
		return repository.update(entity);
	}

	@Override
	public Page<UserLottery> queryPage(Page<UserLottery> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<UserLottery> getUserLotteryList(Long lotteryId) {

		return repository.getUserLotteryList(lotteryId);
	}

}
