package com.youyisi.admin.application.experience.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.experience.ExperienceAccountService;
import com.youyisi.admin.domain.experience.ExperienceAccount;
import com.youyisi.admin.domain.experience.ExperienceAccountRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Service
public class ExperienceAccountServiceImpl implements ExperienceAccountService {

	@Autowired
	private ExperienceAccountRepository repository;

	@Override
	public ExperienceAccount get(Long id) {
		return repository.get(id);
	}

	@Override
	public ExperienceAccount save(ExperienceAccount entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(ExperienceAccount entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(ExperienceAccount entity) {
		return repository.update(entity);
	}
	@Override
	public Page<ExperienceAccount> queryPage(Page<ExperienceAccount> page) {
		return repository.queryPage(page);
	}

	@Override
	public ExperienceAccount getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return repository.getByUserId(userId);
	}
}

