package com.youyisi.admin.application.adminuser.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.adminuser.RoleService;
import com.youyisi.admin.domain.adminuser.Role;
import com.youyisi.admin.domain.adminuser.RoleRepository;
import com.youyisi.lang.Page;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository repository;

	@Override
	public Role get(Integer id) {
		return repository.get(id);
	}

	@Override
	public Role save(Role role) {
		return repository.save(role);
	}

	@Override
	public int update(Role role) {
		return repository.update(role);
	}

	@Override
	public int delete(Role role) {
		return repository.delete(role);
	}

	@Override
	public List<Role> query(Role role) {
		return repository.query(role);
	}

	@Override
	public Page<Role> queryPage(Page<Role> page) {
		return repository.queryPage(page);
	}

    @Override
    public boolean isExistRole(Role role) {
        return repository.isExistRole(role);
    }

}
