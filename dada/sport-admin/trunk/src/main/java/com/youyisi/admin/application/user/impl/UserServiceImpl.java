package com.youyisi.admin.application.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserRepository;
import com.youyisi.lang.Page;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User get(Integer id) {
		return repository.get(id);
	}

	@Override
	public User getByUsername(String username) {
		return repository.getByUsername(username);
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public int delete(User user) {
		return repository.delete(user);
	}

	@Override
	public int update(User user) {
		return repository.update(user);
	}

	@Override
	public void lockUser(User user) {
		repository.LockUser(user);
	}

	@Override
	public List<User> getSuperAdmin() {
		return repository.getSuperAdmin();
	}

	@Override
	public List<User> query(User user) {
		return repository.query(user);
	}

	@Override
	public Page<User> queryPage(Page<User> page) {
		return repository.queryPage(page);
	}

	@Override
	public boolean notExistUser(User user) {
		// TODO Auto-generated method stub
		return repository.notExistUser(user);
	}

}
