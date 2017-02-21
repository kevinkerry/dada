package com.youyisi.admin.application.wallet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.wallet.WalletService;
import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.admin.domain.wallet.WalletRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository repository;

	@Override
	public Wallet get(Long id) {
		return repository.get(id);
	}

	@Override
	public Wallet save(Wallet entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(Wallet entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Wallet entity) {
		return repository.update(entity);
	}
	@Override
	public Page<Wallet> queryPage(Page<Wallet> page) {
		return repository.queryPage(page);
	}

	@Override
	public Wallet getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return repository.getByUserId(userId);
	}
}

