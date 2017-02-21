package com.youyisi.admin.application.indiana.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.indiana.IndianaService;
import com.youyisi.admin.domain.indiana.Indiana;
import com.youyisi.admin.domain.indiana.IndianaRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Service
public class IndianaServiceImpl implements IndianaService {

	@Autowired
	private IndianaRepository repository;

	@Override
	public Indiana get(Long id) {
		return repository.get(id);
	}

	@Override
	public Indiana save(Indiana indiana) {
		return repository.save(indiana);
}

	@Override
	public Integer delete(Indiana indiana) {
		return repository.delete(indiana);
	}

	@Override
	public Integer update(Indiana indiana) {
		return repository.update(indiana);
	}
	@Override
	public Page<Indiana> queryPage(Page<Indiana> page) {
		return repository.queryPage(page);
	}
}

