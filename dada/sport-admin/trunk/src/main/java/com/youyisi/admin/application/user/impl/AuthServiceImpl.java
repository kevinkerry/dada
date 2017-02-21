package com.youyisi.admin.application.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.user.AuthService;
import com.youyisi.admin.domain.user.Auth;
import com.youyisi.admin.domain.user.AuthRepository;
import com.youyisi.lang.Page;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthRepository repository;

	@Override
	public Auth get(Integer id) {
		return repository.get(id);
	}

	@Override
	public Auth save(Auth auth) {
		return repository.save(auth);
	}

	@Override
	public int delete(Auth auth) {
		return repository.delete(auth);
	}

	@Override
	public int update(Auth auth) {
		return repository.update(auth);
	}

	@Override
	public List<Auth> query(Auth auth) {
		return repository.query(auth);
	}

	@Override
	public Page<Auth> queryPage(Page<Auth> page) {
		return repository.queryPage(page);
	}

    @Override
    public boolean isExistAuth(Auth auth) {
        return repository.isExistAuth(auth);
    }

}
