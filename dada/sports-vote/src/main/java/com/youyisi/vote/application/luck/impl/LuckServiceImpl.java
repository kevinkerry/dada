package com.youyisi.vote.application.luck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.lang.Page;
import com.youyisi.vote.application.luck.LuckService;
import com.youyisi.vote.domain.luck.Luck;
import com.youyisi.vote.domain.luck.LuckRepository;

/**
 * @author shuye
 * @time 2015-09-15
 */
@Service
public class LuckServiceImpl implements LuckService {

	@Autowired
	private LuckRepository repository;

	@Override
	public Luck get(Long id) {
		return repository.get(id);
	}

	@Override
	public Luck save(Luck entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(Luck entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Luck entity) {
		return repository.update(entity);
	}
	@Override
	public Page<Luck> queryPage(Page<Luck> page) {
		return repository.queryPage(page);
	}

	@Override
	public List<Luck> getLuck() {
		// TODO Auto-generated method stub
		return repository.getLuck();
	}
}

