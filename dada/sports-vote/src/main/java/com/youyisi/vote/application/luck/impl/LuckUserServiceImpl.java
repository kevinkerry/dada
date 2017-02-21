package com.youyisi.vote.application.luck.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.lang.Page;
import com.youyisi.vote.application.luck.LuckUserService;
import com.youyisi.vote.domain.luck.LuckUser;
import com.youyisi.vote.domain.luck.LuckUserRepository;

/**
 * @author shuye
 * @time 2015-09-15
 */
@Service
public class LuckUserServiceImpl implements LuckUserService {

	@Autowired
	private LuckUserRepository repository;

	@Override
	public LuckUser get(Long id) {
		return repository.get(id);
	}

	@Override
	public LuckUser save(LuckUser entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(LuckUser entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(LuckUser entity) {
		return repository.update(entity);
	}
	@Override
	public Page<LuckUser> queryPage(Page<LuckUser> page) {
		return repository.queryPage(page);
	}

	@Override
	public LuckUser getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return repository.getByUserId(userId);
	}
}

