package com.youyisi.admin.application.adminuser.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.adminuser.AdminUserService;
import com.youyisi.admin.domain.adminuser.AdminUser;
import com.youyisi.admin.domain.adminuser.AdminUserRepository;
import com.youyisi.lang.Page;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserRepository repository;

	@Override
	public AdminUser get(Integer id) {
		return repository.get(id);
	}

	@Override
	public AdminUser getByUsername(String username) {
		return repository.getByUsername(username);
	}

	@Override
	public AdminUser save(AdminUser user) {
		return repository.save(user);
	}

	@Override
	public int delete(AdminUser user) {
		return repository.delete(user);
	}

	@Override
	public int update(AdminUser user) {
		return repository.update(user);
	}

	@Override
	public void lockUser(AdminUser user) {
		repository.LockUser(user);
	}

	@Override
	public List<AdminUser> getSuperAdmin() {
		return repository.getSuperAdmin();
	}

	@Override
	public List<AdminUser> query(AdminUser user) {
		return repository.query(user);
	}

	@Override
	public Page<AdminUser> queryPage(Page<AdminUser> page) {
		return repository.queryPage(page);
	}

	@Override
	public boolean notExistUser(AdminUser user) {
		return repository.notExistUser(user);
	}

}
