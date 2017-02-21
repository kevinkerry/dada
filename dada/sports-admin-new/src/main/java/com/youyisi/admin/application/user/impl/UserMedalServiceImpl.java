package com.youyisi.admin.application.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.user.UserMedalService;
import com.youyisi.admin.domain.user.UserMedal;
import com.youyisi.admin.domain.user.UserMedalRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-08
 */
@Service
public class UserMedalServiceImpl implements UserMedalService {

	@Autowired
	private UserMedalRepository repository;

	@Override
	public UserMedal get(Long id) {
		return repository.get(id);
	}

	@Override
	public UserMedal save(UserMedal entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(UserMedal entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserMedal entity) {
		return repository.update(entity);
	}
	@Override
	public Page<UserMedal> queryPage(Page<UserMedal> page) {
		return repository.queryPage(page);
	}
}

